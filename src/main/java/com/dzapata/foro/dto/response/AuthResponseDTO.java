package com.dzapata.foro.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private long expireIn;
}
