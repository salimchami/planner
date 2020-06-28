package io.edukativ.myskoolin.config;

import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.front.security.jwt.JWTConfigurer;
import io.edukativ.myskoolin.front.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
        .and()
            .headers()
            .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
        .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
        .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
        .and()
            .frameOptions()
            .deny()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()

            .antMatchers("/public/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/clients/**").hasAuthority(AuthoritiesConstants.ADMINISTRATION)
            .antMatchers("/api/clients/current").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION)
            .antMatchers("/api/clients/**").hasAnyAuthority(AuthoritiesConstants.SCHOOLME_ADMIN)
            .antMatchers("/api/grades/**").hasAuthority(AuthoritiesConstants.ADMINISTRATION)
            .antMatchers("/api/subjects/**").hasAuthority(AuthoritiesConstants.ADMINISTRATION)
            .antMatchers("/api/navigations/**").hasAnyAuthority(AuthoritiesConstants.allAuthenticatedAuthorities().toArray(new String[0]))
            .antMatchers("/api/clientConf/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOLME_ADMIN)
            .antMatchers("/api/students/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE, AuthoritiesConstants.TEACHERS)
            .antMatchers("/api/school-classes/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE, AuthoritiesConstants.TEACHERS)
            .antMatchers("/api/school-rooms/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE, AuthoritiesConstants.TEACHERS)
            .antMatchers("/api/teachers/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE, AuthoritiesConstants.TEACHERS)
            .antMatchers("/api/timetables/**").hasAnyAuthority(AuthoritiesConstants.ADMINISTRATION, AuthoritiesConstants.SCHOOL_LIFE, AuthoritiesConstants.SCHOOLME_ADMIN)


            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.SCHOOLME_ADMIN)
            .antMatchers("/websocket/**").permitAll()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.SCHOOLME_ADMIN)
        .and()
            .httpBasic()
        .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
