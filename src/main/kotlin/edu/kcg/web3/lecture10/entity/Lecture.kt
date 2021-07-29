package edu.kcg.web3.lecture10.entity

import javax.persistence.*


@Entity(name = "lecture")
class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_lecture")
    val id: Long? = null

    @Column(name = "subjectname", nullable = false)
    var subjectname: String = ""

    @Column(name = "period", nullable = false)
    var period: Long = 0

    @ManyToMany(mappedBy = "lectures")
    val shopBooks: Set<Books> = setOf()

}