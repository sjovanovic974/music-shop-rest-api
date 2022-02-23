package sasa.jovanovic.musicshop.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/orders/**", "/checkout/purchase")
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();


        http.cors();
        // 401 response in response body
        Okta.configureResourceServer401ResponseBody(http);
    }
}
