package ru.sber.server

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AppController {
    @GetMapping("/app")
    fun toAppPage(): String {
        return "app"
    }
}