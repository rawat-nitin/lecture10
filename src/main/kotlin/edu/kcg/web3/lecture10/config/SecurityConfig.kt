package edu.kcg.web3.lecture10.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Autowired
    private val authProvider: StudentAuthenticationProvider? = null

    @Autowired
    fun configAuthentication(auth: AuthenticationManagerBuilder) {
        logger.info("Registering AuthenticationProvider")
        auth.authenticationProvider(authProvider)
    }

    override fun configure(http: HttpSecurity) {
        logger.info("Configuring Spring security")
        http.logout() // configuring logout page
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            // ending configuring logout page and continuing
            .and()
            // configuring authorization
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            // ending configuring authorization and continuing
            .and()
            // allowing default login page at /login URL
            // with automatic redirection
            .formLogin()
    }

}
