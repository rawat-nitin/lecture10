package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.entity.Student
import edu.kcg.web3.lecture10.repository.StudentRepository
import org.apache.logging.log4j.util.LambdaUtil.getAll
import org.aspectj.weaver.UnresolvedType.insert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBean.getAll
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestController (private  val studentRepository: StudentRepository){

    @RequestMapping("/find", method = arrayOf(RequestMethod.GET))
    fun showAllStudent(): List<Student>{
        return studentRepository.findAll().onEach {
            it.password = ""
        }
    }


}