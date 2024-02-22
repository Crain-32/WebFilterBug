package dev.crain.webfilterbug;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@SpringBootApplication
public class WebfilterBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfilterBugApplication.class, args);
    }

    @WebFilter("/not/simple")
    @Component
    public static class SimpleFilterOne extends OncePerRequestFilter {

        public static boolean CALLED = false;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            CALLED = true;
            filterChain.doFilter(request, response);
        }
    }

    @WebFilter(value = "/not/simple")
    @Component
    public static class SimpleFilterTwo extends OncePerRequestFilter {

        public static boolean CALLED = false;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            CALLED = true;
            filterChain.doFilter(request, response);
        }
    }

    @WebFilter(urlPatterns = "/not/simple")
    @Component
    public static class SimpleFilterThree extends OncePerRequestFilter {

        public static boolean CALLED = false;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            CALLED = true;
            filterChain.doFilter(request, response);
        }
    }

    @RestController
    @RequestMapping("/rest/simple")
    public static class SimpleController {


        @GetMapping
        public String getString() {
            return "Hello World";
        }
    }
}
