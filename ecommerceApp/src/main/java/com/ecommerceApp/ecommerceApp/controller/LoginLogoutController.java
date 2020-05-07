package com.ecommerceApp.ecommerceApp.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class LoginLogoutController {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    MessageSource messageSource;
////////////done
@ApiOperation(value = "Api to logout ",authorizations = {@Authorization(value = "Bearer")})

@GetMapping(value = "/doLogout",produces = "application/json")
    public String logout(HttpServletRequest request, Locale locale){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer ", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return messageSource.getMessage("user.logout.message",null,locale);
    }
}
