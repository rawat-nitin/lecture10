package edu.kcg.web3.lecture10.repository

import edu.kcg.web3.lecture10.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Student, Long?> {

    @Query("SELECT c FROM customer c WHERE c.email LIKE :email")
    fun findByEmail(@Param("email") email: String): Student?

}