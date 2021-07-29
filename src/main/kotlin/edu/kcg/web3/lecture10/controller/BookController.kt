package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.entity.Books
import edu.kcg.web3.lecture10.repository.CustomerRepository
import edu.kcg.web3.lecture10.repository.ProductRepository
import edu.kcg.web3.lecture10.repository.ShopOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/orders")
class BookController(
    @Autowired private val shopBookRepository: ShopBookRepository,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val customerRepository: CustomerRepository
) {

    @RequestMapping
    fun showAllBooks(model: Model): String {
        model["title"] = "Lecture Display Page"
        model["books"] = shopBookRepository.findAll().sortedBy { it.id }
        return "books"
    }

    @RequestMapping("/new")
    fun newLecture(model: Model): String {
        val shopBook = Books()

        val email = SecurityContextHolder.getContext().authentication.principal as String
        val customer = customerRepository.findByEmail(email) ?: throw RuntimeException()
        shopBook.student = customer

        var BookPrice = 0L
        val lectures = lectureRepository.findAll()
        lectures.randomOrNull()?.let {
            shopBook.lectures.add(it)
            BookPrice += it.bookPrice
        }
        lectures.randomOrNull()?.let {
            shopBook.lectures.add(it)
            BookPrice += it.bookprice
        }
        shopBook.bookPrice = BookPrice

        shopBookRepository.save(shopBook)
        return showAllBooks(model)
    }

}
