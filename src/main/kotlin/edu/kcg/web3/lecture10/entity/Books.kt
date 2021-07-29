package edu.kcg.web3.lecture10.entity

import java.time.Instant
import javax.persistence.*


@Entity(name = "shop_Books")
class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop_books")
    val id: Long? = null

    @Column(name = "book_price", nullable = false)
    var bookPrice: Long = 0

    @Column(name = "created", nullable = false)
    val created: Instant = Instant.now()

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_student")
    var student: Student? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "shopbooks_lecture",
        joinColumns = [JoinColumn(name = "id_shop_books")],
        inverseJoinColumns = [JoinColumn(name = "id_lecture")]
    )
    val lectures: MutableSet<Lecture> = mutableSetOf()

}