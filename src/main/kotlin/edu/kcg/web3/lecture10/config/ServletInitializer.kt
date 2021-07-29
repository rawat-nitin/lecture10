package edu.kcg.web3.lecture10.config

import edu.kcg.web3.lecture10.Lecture10Application
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(Lecture10Application::class.java)
	}

}
