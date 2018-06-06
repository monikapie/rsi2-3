package rsirest2.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;

@Component
public class DummyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("MyHeader", "Suprise");
        response.setHeader("Cache-Control", "ups");
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String headerName = headers.nextElement();
            System.out.println("Request: " + headerName + ": " +request.getHeader(headerName));
        }
        System.out.println("Request: " + request.getHeaderNames().nextElement());
        System.out.println("Response: " + response.getHeaderNames());

        String userPass = request.getHeader("authorization").substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(userPass);
        System.out.println("Decoded username and password " + new String(decodedBytes));
        filterChain.doFilter(request, response);
    }
}
