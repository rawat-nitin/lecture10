package edu.kcg.web3.lecture10.component

import edu.kcg.web3.lecture10.entity.Student
import org.springframework.stereotype.Component

@Component
class StudentDatabaseSimulator {

    private var gakusei = mutableListOf<Student>()
    private var idCounter = 0

    fun getById(id: String): Student? {
        return gakusei.find{ "{id}".equals(it.id) }
    }

    fun getAll(): List<Student> {
        return gakusei
    }

    fun insert(student: Student): Long? {
        if(student.id!! <= 0) {
            student.id = ++idCounter.toLong()
            gakusei.add(student)
        } else {
            update(student)
        }
        return student.id
    }

    fun delete(student: Student) {
        gakusei.remove(student)
    }

    fun update(student: Student) {
        if(student.id!! <= 0) {
            insert(student)
        } else {
            gakusei.indexOfFirst{it.id == student.id}.let { index ->
                if (index >= 0) {
                    gakusei[index] = student
                }
            }
        }

    }
}