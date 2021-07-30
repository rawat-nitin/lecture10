package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.entity.Student
import edu.kcg.web3.lecture10.repository.StudentRepository
import org.apache.logging.log4j.util.LambdaUtil.getAll
import org.aspectj.weaver.UnresolvedType.insert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBean.getAll
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Table

@RestController
@RequestMapping("/rest")
class RestController (private  val studentRepository: StudentRepository){

    @GetMapping("/find")
    fun showAllStudent(): List<Student>{
        return studentRepository.findAll().onEach {
            it.password = ""
        }
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long?): ResponseEntity<Student> {
        val student = studentRepository.getById(id ?: -1)
        return if (student != null) {
            ResponseEntity<Student>(student, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/{}")
    fun deleteStudentById(@PathVariable id: Long?): HttpEntity<*>{
        studentRepository.getById(id ?: -1)
            ?.let { studentRepository.delete(it) }
        return ResponseEntity.EMPTY
    }

}

