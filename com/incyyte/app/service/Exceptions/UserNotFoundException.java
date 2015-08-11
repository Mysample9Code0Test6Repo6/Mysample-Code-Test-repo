package com.incyyte.app.service.Exceptions;

import com.incyyte.app.domain.User;


public class UserNotFoundException extends RuntimeException {

    private User user;
    
    public UserNotFoundException(User user) {
        this.user = user;
    }

    public UserNotFoundException() {
        // TODO Auto-generated constructor stub
    }

    public User getUser() {
        return user;
    }

}
