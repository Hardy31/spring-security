package base.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pasU")
                .roles("USER")
                .build();

        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manag")
                .password("pasM")
                .roles("MANAGER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pasA")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, manager, admin);
    }

    @Bean
    @Order(0)
    public SecurityFilterChain filterChain0(HttpSecurity http) throws Exception {

//        https://alexkosarev.name/2023/05/27/deep-dive-into-spring-security-for-servlet-api-part-1/
//        https://www.youtube.com/watch?v=GJQV4JChWyk


        http
//                .csrf().disable() //для методов изменяющих данные
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)   //отключение сесии
                .authorizeHttpRequests(
                        requests -> requests.requestMatchers("/admin").hasRole("ADMIN"))
                .authorizeHttpRequests(
                        requests -> requests.requestMatchers("/user").hasAnyRole("USER", "ADMIN"))
                .authorizeHttpRequests(
                        requests -> requests.requestMatchers( "/hello").permitAll())
                // включить поддержку формы входа
                .formLogin(withDefaults())
                // включить поддержку Basic-аутентификации
                .httpBasic(withDefaults());
        // собрать цепочку фильтров

        return http.build();
    }

//    @Bean
//    @Order(3)
//    public SecurityFilterChain filterChain3(HttpSecurity http) throws Exception {
////            https://alexkosarev.name/2023/05/27/deep-dive-into-spring-security-for-servlet-api-part-1/
//        http
//                .authorizeHttpRequests(
//                        requests -> requests.requestMatchers("/**").hasRole("ADMIN"))
//                // включить поддержку формы входа
//                .formLogin(withDefaults())
//                // включить поддержку Basic-аутентификации
//                .httpBasic(withDefaults());
//        // собрать цепочку фильтров
//
//        return http.build();
//
//    }
}
