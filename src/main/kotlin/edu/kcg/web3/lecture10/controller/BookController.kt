package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.entity.Books
import edu.kcg.web3.lecture10.repository.StudentRepository
import edu.kcg.web3.lecture10.repository.LectureRepository
import edu.kcg.web3.lecture10.repository.ShopBookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/books")
class BookController(
    @Autowired private val shopBookRepository: ShopBookRepository,
    @Autowired private val lectureRepository: LectureRepository,
    @Autowired private val studentRepository: StudentRepository
) {

    @RequestMapping
    fun showAllBooks(model: Model): String {
        model["title"] = "Lecture Display Page"
        model["id"] = 0
        model["books"] = shopBookRepository.findAll().sortedBy { it.id }
        return "books"
    }

    @RequestMapping("/new/{id}")
    fun newLecture(model: Model, @PathVariable id: String): String {
        val shopBook = Books()


        val email = SecurityContextHolder.getContext().authentication.principal as String
        val student = studentRepository.findByEmail(email) ?: throw RuntimeException()
        shopBook.student = student

        var BookPrice = 0L
        val lectures = lectureRepository.findAll()
        lectures.randomOrNull()?.let {
            shopBook.lectures.add(it)
            BookPrice += it.period
        }
        lectures.randomOrNull()?.let {
            shopBook.lectures.add(it)
            BookPrice += it.period
        }
        shopBook.bookPrice = BookPrice

        shopBookRepository.save(shopBook)
        model["id"] = id
        model["title"] = "Showing Books"
        if(id == "0"){
         return showAllBooks(model)
        }
        else{
            model["books"] = lectureRepository.findByIdOrNull(id.toLongOrNull())?.shopBooks ?: emptySet<Books>()
            return "books"
        }

    }

}
