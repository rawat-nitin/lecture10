package edu.kcg.web3.lecture10.entity

import javax.persistence.*


@Entity(name = "student")
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    val id: Long? = null

    @Column(name = "email", nullable = false, unique = true)
    val email: String = ""

    @Column(name = "password", nullable = false)
    var password: String = ""

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val shopBooks: Set<Books> = setOf()

}