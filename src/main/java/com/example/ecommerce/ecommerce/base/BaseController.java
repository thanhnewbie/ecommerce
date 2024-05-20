package com.example.ecommerce.ecommerce.base;

import com.example.ecommerce.ecommerce.DTO.ResponeDTO;
import com.example.ecommerce.ecommerce.constants.Common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController<T> {
    public ResponseEntity<?> resSuccess(T data){
        Map<String, T> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeDTO<>(HttpStatus.OK.value(), Common.RESPONSE_MESSAGE_SUCCESS, map)
        );
    }

    public ResponseEntity<?> resListSuccess(List<T> data){
        Map<String, List<T>> map = new HashMap<>();
        map.put("data", data);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeDTO<>(HttpStatus.OK.value(), Common.RESPONSE_MESSAGE_SUCCESS, map)
        );
    }
}
