package com.example.ecommerce.ecommerce.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OAuthTest {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//		Map<String, Object> userInfo = new HashMap<>();
//		if (principal != null) {
//			userInfo.put("name", principal.getAttribute("login"));
//			// Thêm các thuộc tính khác của người dùng nếu cần
//		}
//		return userInfo;
        System.out.println("da chay vao controller");
        if (principal != null) {
            Map<String, Object> attributes = principal.getAttributes();
            System.out.println("Attributes of the authenticated principal:");
            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("day la id cua user google:");
            System.out.println(attributes.get("id"));

            return attributes;
        } else {
            // Trả về một đối tượng JSON trống hoặc một thông báo lỗi tùy thuộc vào yêu cầu của ứng dụng của bạn
            return null;
        }
    }

}
