package com.learn.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.annotation.Resource;
/**
 * 模拟第三方授权配置
 */
@EnableAuthorizationServer
@Configuration
public class AuthConfig extends AuthorizationServerConfigurerAdapter {


    /**
     * 模仿第三方服务
     */
    @Autowired
    ClientDetailsService clientDetailsService;

    /**
     * 资源校验服务
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }

    /**
     * \
     * 第三方客户端配置 和资源服务访问的配置 不设置都可以访问 提供默认回调地址
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("third01")
                .secret(new BCryptPasswordEncoder().encode("third01"))
                .resourceIds("resouce-01")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .redirectUris("localhost:8082/notify.html");

    }

    /**
     * 配置访问端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authorizationCodeServices(authorizationCodeServices()).tokenServices(tokenServices());
    }


    /**
     * 内存管理
     */
    @Bean
    AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
    /**
     * Token管理规则
     */
    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore());
        services.setAccessTokenValiditySeconds(3600);
        services.setRefreshTokenValiditySeconds(3600*7);
        return services;
    }
    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
