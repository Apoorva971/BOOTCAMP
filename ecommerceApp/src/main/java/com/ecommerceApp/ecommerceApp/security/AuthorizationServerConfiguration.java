package com.ecommerceApp.ecommerceApp.security;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    public AuthorizationServerConfiguration() {
        super();
    }

    @Bean
    @Primary
    DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(jwtTokenEnhancer());//
        return defaultTokenServices;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        enhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer(),accessTokenConverter()));

        endpoints.tokenStore(tokenStore()).userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                 .tokenEnhancer(customTokenEnhancer());
    }
    @Bean
    public TokenStore tokenStore() {
//       return new InMemoryTokenStore();
        return new JdbcTokenStore(dataSource);
    }

    ////////////////////////////////////////////////////////////
@Component
public class CustomTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        final Map<String, Object> additionalInfo = new HashMap<>();

        Users user = (Users) authentication.getPrincipal();
        additionalInfo.put("UserName", user.getFirstName());
        additionalInfo.put("role", user.getRoles().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(accessToken, authentication);
    }
}

///////////////////////////////////////////////////////////////////
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("live-test")
                .secret(passwordEncoder.encode("abcde"))
                .authorizedGrantTypes("password","refresh_token")
                .additionalInformation("UserName")
                .additionalInformation("role")
                .refreshTokenValiditySeconds(30 * 24 * 3600)
                .scopes("app")
                .accessTokenValiditySeconds(7*24*60);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer)
            throws Exception {
        authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
    }
/////////////////////////////////////////////////////////////////////////////
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter=  new JwtAccessTokenConverter();
        converter.setSigningKey("my_signing_key");

        return converter;
    }

    @Bean
    public CustomTokenConverter customTokenEnhancer() {
        return new CustomTokenConverter();
    }
//////////////////////////////////////////////////////////////////////////////////////
//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean public TokenEnhancer customTokenEnhancer() {
//        return new CustomTokenEnhancer();
//    }

}
