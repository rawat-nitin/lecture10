package edu.kcg.web3.lecture10.controller

import edu.kcg.web3.lecture10.entity.Lecture
import edu.kcg.web3.lecture10.entity.Books
import edu.kcg.web3.lecture10.repository.LectureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/lectures")
class LectureController(
    @Autowired private val lectureRepository: LectureRepository
) {

    @RequestMapping
    fun showAllLectures(model: Model): String {
        model["title"] = "Lectures page"
        model["lectures"] = lectureRepository.findAll().sortedBy { it.id }
        return "lectures"
    }

    @RequestMapping("/new")
    fun newProduct(model: Model, @RequestParam name: String, @RequestParam price: String): String {
        Lecture().also {
            it.subjectname = name
            it.period = name.toLongOrNull() ?: 0
            lectureRepository.save(it)
        }
        return showAllLectures(model)
    }

    @RequestMapping("/{id}/Books")
    fun productDetail(model: Model, @PathVariable id: String): String {
        model["title"] = "Lectures page"
        model["books"] = lectureRepository.findByIdOrNull(id.toLongOrNull())?.bookOrders ?: emptySet<Books>()
        return "lectures-books"
    }

}
