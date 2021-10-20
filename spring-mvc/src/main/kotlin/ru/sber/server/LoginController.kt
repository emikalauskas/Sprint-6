package ru.sber.server

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {
    val login = "login"
    val password = "password"

    @GetMapping("/")
    fun redirectTLoginPage(): String {
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun toLoginPage(): String {
        return "login"
    }

    @PostMapping("/auth")
    fun authUser(@RequestParam login: String, @RequestParam password: String, response: HttpServletResponse, model: Model): String {
        return if (login == this.login && password == this.password) {
            val cookie = Cookie("user", "admin")
            cookie.maxAge = 60
            response.addCookie(cookie)
            return "redirect:/app"
        } else {
            return "redirect:/login"
        }
    }
}