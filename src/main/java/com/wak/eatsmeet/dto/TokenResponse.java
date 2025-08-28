package com.wak.eatsmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenResponse(String activeToken, String refreshToken) {
        this.accessToken = activeToken;
        this.refreshToken = refreshToken;
    }
}
