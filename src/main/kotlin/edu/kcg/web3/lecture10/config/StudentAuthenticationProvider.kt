package edu.kcg.web3.lecture10.config

import edu.kcg.web3.lecture10.entity.Student
import edu.kcg.web3.lecture10.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class StudentAuthenticationProvider(
    @Autowired private val studentRepository: StudentRepository,
    @Autowired private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val password = authentication.credentials.toString()
        val customer = studentRepository.findByEmail(email)

        if (student?.password?.isNotBlank() == true && passwordEncoder.matches(password, student.password)) {
            val authorities = mutableListOf<SimpleGrantedAuthority>()
            authorities.add(SimpleGrantedAuthority("ADMIN"))
            return UsernamePasswordAuthenticationToken(email, password, authorities)
        } else {
            throw BadCredentialsException("Bad credentials. Please try again.")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

}