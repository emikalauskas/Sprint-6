package ru.sber.server

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class AppController {
    val addressBook = AddressBook()

    @GetMapping("/")
    fun redirectToLoginPage(): String {
        return "redirect:/login"
    }

    @GetMapping("app/list")
    fun getList(model: Model): String {
        model.addAttribute("notes", addressBook.list)
        return "app-list-page"
    }

    @PostMapping("app/add")
    fun addToList(@RequestParam("name")name: String, @RequestParam("address")address: String, model: Model): String {
        addressBook.list[addressBook.list.size] = Note(name, address)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }

    @GetMapping("app/{id}/view")
    fun getListById(@PathVariable("id")id: Int, model: Model): String {
        model.addAttribute("notes", addressBook.list[id])
        return "redirect:/app/list"
    }

    @PostMapping("app/delete")
    fun deleteFromList(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/delete").forward(request, response)
    }

    @PostMapping("app/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int, model: Model): String {
        addressBook.list.remove(id)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }

    @PostMapping("app/edit")
    fun editNote(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/edit").forward(request, response)
    }

    @PostMapping("app/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestParam("name")name: String, @RequestParam("address")address: String, model: Model): String {
        addressBook.list[id] = Note(name, address)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }
}