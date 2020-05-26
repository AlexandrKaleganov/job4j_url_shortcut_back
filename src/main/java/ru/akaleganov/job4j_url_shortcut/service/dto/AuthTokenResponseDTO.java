package ru.akaleganov.job4j_url_shortcut.service.dto;

import ru.akaleganov.job4j_url_shortcut.domain.User;

import java.util.List;

/**
 * ответ когда пользователь авторизован
 *
 */
public class AuthTokenResponseDTO {
    /**
     * токен
     */
    private String jwtToken;
    /**
     * имя пользователя
     */
    private String username;
    private List<String> roles;
    public AuthTokenResponseDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public AuthTokenResponseDTO(String jwtToken, User users) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
