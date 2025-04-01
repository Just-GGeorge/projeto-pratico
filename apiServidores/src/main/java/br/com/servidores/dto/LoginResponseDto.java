package br.com.servidores.dto;


public class LoginResponseDto {
    private String username;
    private String token;
    private String expireTime;

    public LoginResponseDto(String username, String token, String expireTime) {
        this.username = username;
        this.token = token;
        this.expireTime = expireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
