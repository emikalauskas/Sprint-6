package ru.sber.server

import org.springframework.core.annotation.Order
import java.io.IOException
import javax.servlet.DispatcherType
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/app/*"], dispatcherTypes = [DispatcherType.REQUEST])
@Order(1)
class MethodLoggingFilter : HttpFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        for(cookie in request.cookies)
            if (cookie.name == "user") {
                chain.doFilter(request, response)
                return
            }
        response.sendRedirect("/login")
    }
}