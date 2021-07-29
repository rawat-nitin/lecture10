package edu.kcg.web3.lecture10.repository

import edu.kcg.web3.lecture10.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Lecture, Long?>