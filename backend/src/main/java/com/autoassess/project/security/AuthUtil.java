package com.autoassess.project.security;

//AuthUtil- It is just a shortcut helper to get the logged-in user.
//SecurityContext is a Spring object stored per request thread, and it holds the authenticated user info after login.
//JWT Filter runs->token validated->Authentication object created->SecurityContextHolder.setAuthentication()

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getCurrentUserEmail(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}

