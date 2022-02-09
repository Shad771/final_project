package com.alevel.web.dto.response;

import com.alevel.persistence.entity.user.User;

public class UserResponseDto extends ResponseDto{

    private String email;

    public UserResponseDto(User user){
        if(user != null) {
            this.email = user.getEmail();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
