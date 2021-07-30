package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.component.StudentDatabaseSimulator
import edu.kcg.web3.lecture10.entity.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestController(@Autowired private val databaseSimulator: StudentDatabaseSimulator){

    @GetMapping
    fun getAll(): List<Student> {
        if (databaseSimulator.getAll().isEmpty()){
            databaseSimulator.insert(Student())
        }
        return databaseSimulator.getAll()
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id:Int?): ResponseEntity<Student>{
        val student =  databaseSimulator.getById((id ?: -1).toString())
        return if (student != null) {
            ResponseEntity<Student>(student, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

        @PostMapping(consumes = ["application/json"])
        fun insertStudent(@RequestBody student: Student): HttpEntity<*> {
            databaseSimulator.insert(student)
            return ResponseEntity.EMPTY
        }

        @PutMapping(consumes = ["application/json"])
        fun updateStudent(@RequestBody student: Student): HttpEntity<*> {
            databaseSimulator.update(student)
            return ResponseEntity.EMPTY
        }

        @DeleteMapping(consumes = ["application/json"])
        fun deleteStudent(@RequestBody student: Student): HttpEntity<*> {
            databaseSimulator.delete(student)
            return ResponseEntity.EMPTY
        }

        @DeleteMapping("/{id}")
        fun deleteStudentById(@PathVariable id: Int?): HttpEntity<*> {
            databaseSimulator.getById((id ?: -1).toString())
                ?.let { databaseSimulator.delete(it) }
            return ResponseEntity.EMPTY
        }

    }

}