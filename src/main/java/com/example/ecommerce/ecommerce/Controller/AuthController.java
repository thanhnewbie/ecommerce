package com.example.ecommerce.ecommerce.Controller;

import com.example.ecommerce.ecommerce.DAO.User;
import com.example.ecommerce.ecommerce.DTO.Login.AuthenticationRequest;
import com.example.ecommerce.ecommerce.DTO.Login.AuthenticationResponse;
import com.example.ecommerce.ecommerce.DTO.Login.ChangePasswordDTO;
import com.example.ecommerce.ecommerce.DTO.Login.SignUpDTO;
import com.example.ecommerce.ecommerce.Exception.AppException;
import com.example.ecommerce.ecommerce.Exception.ResourceNotFoundException;
import com.example.ecommerce.ecommerce.Security.JWTUtil;
import com.example.ecommerce.ecommerce.Security.MyUserDetailService;
import com.example.ecommerce.ecommerce.Service.MailSenderService;
import com.example.ecommerce.ecommerce.Service.UserService;
import com.example.ecommerce.ecommerce.base.BaseController;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController<Object> {
    @Autowired
    UserService userService;
    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    MailSenderService mailSenderService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){
        User oldUser = userService.findBySDT(signUpDTO.getSDT());
        if(oldUser != null){
            throw new AppException("Phone number has already exist");
        }

        User newUser = userService.create(signUpDTO);
        UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return this.resSuccess(new AuthenticationResponse(token, newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch(Exception ex){
            throw new AppException("username or password was wrong");
        }
        UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        User user = userService.findByUserName(authenticationRequest.getUsername());
        return this.resSuccess(new AuthenticationResponse(token, user));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDTO changePasswordDTO, HttpServletRequest request){
        String requestUser = (String)request.getAttribute("user");
        User user = userService.findByUserName(requestUser);
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            throw new AppException("password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        User updatedUser = userService.saveUser(user);
        return this.resSuccess(updatedUser);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request) throws MessagingException {
        String requestUser = (String)request.getAttribute("user");
        User user = userService.findByUserName(requestUser);
        if(user == null) throw new ResourceNotFoundException("user not found");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setPassword(passwordEncoder.encode(uuid));
        String subject = "reset password";
        String context = "here is password reset: " + uuid;
        mailSenderService.send(subject, context, user.getEmail());
        return this.resSuccess("success");
    }

}
