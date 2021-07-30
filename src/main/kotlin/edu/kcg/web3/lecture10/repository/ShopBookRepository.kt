package edu.kcg.web3.lecture10.repository

import edu.kcg.web3.lecture10.entity.Books
import edu.kcg.web3.lecture10.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ShopBookRepository : JpaRepository<Books, Long?>

