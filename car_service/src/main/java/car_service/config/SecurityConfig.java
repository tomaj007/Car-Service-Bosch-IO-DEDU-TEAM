package car_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/customerView/**").hasAnyAuthority("ADMIN", "EMPLOYEE", "ADMIN_AUTO_SERVICE")
                .antMatchers("/carView/edit/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE")
                .antMatchers("/carView/delete/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE")
                .antMatchers("/carView/create/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE", "EMPLOYEE")
                .antMatchers("/employeeView/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE")
                .antMatchers("/historyView/edit/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE", "EMPLOYEE")
                .antMatchers("/historyView/create/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE", "EMPLOYEE")
                .antMatchers("/historyView/create-history/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE", "EMPLOYEE")
                .antMatchers("/historyView/delete/**").hasAnyAuthority("ADMIN", "ADMIN_AUTO_SERVICE", "EMPLOYEE")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .permitAll();
    }

}