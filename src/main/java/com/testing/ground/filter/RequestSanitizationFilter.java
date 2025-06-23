package com.testing.ground.filter;

import com.testing.ground.request.misc.SanitizedHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class RequestSanitizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Only sanitize JSON POST/PUT/PATCH
        if ("application/json".equalsIgnoreCase(request.getContentType())
                && List.of("POST", "PUT", "PATCH").contains(request.getMethod())) {

            SanitizedHttpServletRequest sanitizedRequest = new SanitizedHttpServletRequest(request);
            filterChain.doFilter(sanitizedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

