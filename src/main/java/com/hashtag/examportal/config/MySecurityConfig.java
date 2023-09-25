package com.hashtag.examportal.config;


import com.hashtag.examportal.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@CrossOrigin("*")
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//

    @Bean
     public UserDetailsService userDetailsService(){
    return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsServiceImpl);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Autowired
//    void registerProvider(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Override
//    protected void  configure(HttpSecurity httpSecurity) throws Exception{
////        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity (httpSecurity);
//        httpSecurity.csrf()
//                .disable()
//                .cors()
//                .disable()
//                .authorizeRequests()
////                .antMatchers("/user").permitAll()
//                .antMatchers("/user").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        httpSecurity.authenticationProvider(authenticationProvider());
////        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/user/**").hasRole("NORMAL")
//                .antMatchers("/user/{userId}","/admin/**").hasRole("ADMIN")
////                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated();
                .cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/login").permitAll()

                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("NORMAL", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/category/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/category/**").hasAnyAuthority("NORMAL", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority("ADMIN") ///api/options

                .antMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("NORMAL", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/question/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/question/**").hasAnyAuthority("NORMAL", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/question/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/question/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/options/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/options/**").hasAnyAuthority("NORMAL", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/options/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/options/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/quizResult/**").hasAuthority("NORMAL")
                .antMatchers(HttpMethod.GET, "/api/quizResult/all/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/quizResult/**").hasAnyAuthority("NORMAL", "ADMIN")

                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/","/api/register","api/login","/api/question/image/**","/api/options/image/**");
    }
}
