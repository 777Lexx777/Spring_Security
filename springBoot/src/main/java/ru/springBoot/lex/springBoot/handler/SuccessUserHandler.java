package ru.springBoot.lex.springBoot.handler;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

//@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles =
                AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if ((roles.contains("developers:read") && (roles.contains("developers:write")))) {
            httpServletResponse.sendRedirect("/start/admin");
        }  else if (roles.contains("developers:read")) {
            httpServletResponse.sendRedirect("/start/info");
            System.out.println("developers:read");
        } else {
            httpServletResponse.sendRedirect("/");
            System.out.println("developers:null");
        }
    }
}