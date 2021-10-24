package ru.sber.server

import org.springframework.stereotype.Component
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@WebServlet("/login")
class LoginServlet : HttpServlet() {
    private val login = "login"
    private val password = "password"

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.getRequestDispatcher("/login-page.html").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val login = req.getParameter("login")
        val password = req.getParameter("password")
        if (login == this.login && password == this.password) {
            val cookie = Cookie("user", "admin")
            cookie.maxAge = 60
            resp.addCookie(cookie)
            resp.sendRedirect("/app/list")
        } else {
            resp.sendRedirect("/login")
        }
    }
}