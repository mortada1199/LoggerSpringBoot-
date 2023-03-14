package com.testlog.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
public class SecurityConfig   {
private  final RsaKeyProperties rsaKeys;

public SecurityConfig(RsaKeyProperties rsaKeys) {
    this.rsaKeys = rsaKeys;
}


@Bean
public PasswordEncoder  passwordEncoder(){
    return new BCryptPasswordEncoder();
}


// @Bean
// public UserDetailsService  userDetailService(){
//     UserDetails normalUser = User
//     .withUsername("user")
//     .password(passwordEncoder().encode("password"))
//     .roles("NORMAL")
//     .build();
//     UserDetails adminUser = User
//     .withUsername("admin")
//     .password(passwordEncoder().encode("password"))
//     .roles("ADMIN")
//     .build();
//     return new InMemoryUserDetailsManager(normalUser,adminUser);
// }


    
@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{


//         httpSecurity.csrf().disable()
//         .authorizeHttpRequests()
//         // .requestMatchers("/home/normal")
//         // .hasRole("NORMAL")
//         // .requestMatchers("/home/admin")
//         // .hasRole("ADMIN")
//         .requestMatchers("/api/user/login")
//         .permitAll()
//         .anyRequest()
//         .authenticated()
//         .and()
//         .formLogin();

    
// return httpSecurity.build();

        return  httpSecurity.csrf(csrf->csrf.disable())
                .authorizeRequests(auth ->auth.anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt )
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder()
    {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder()
    {
        JWK jwk =new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
        return  new NimbusJwtEncoder(jwks);
    }

}

