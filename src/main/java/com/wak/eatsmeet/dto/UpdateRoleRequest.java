package com.wak.eatsmeet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateRoleRequest {
    @NotBlank(message = "role is required")
    private String role;

    public UpdateRoleRequest(String role) {
        this.role = role;
    }

    public UpdateRoleRequest() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

