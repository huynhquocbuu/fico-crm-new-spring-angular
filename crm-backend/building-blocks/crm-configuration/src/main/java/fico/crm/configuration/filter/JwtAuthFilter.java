package fico.crm.configuration.filter;

import fico.crm.configuration.security.CustomUserDetails;
import fico.crm.configuration.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    public JwtAuthFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //log.info("==========JwtAuthFilter=============");
        //String uri = request.getRequestURI();
        //String method = request.getMethod();
        //log.info("--------Context Path----------: " + uri);
        try {
            String jwt = parseJwt(request);
            if (!jwt.isEmpty() && jwtUtil.validateJwtToken(jwt)) {

                //log.info("roles parse from jwt: " + jwtUtil.getRolesFromJwtToken(jwt));
                CustomUserDetails userDetails = new CustomUserDetails(
                        jwtUtil.getUserNameFromJwtToken(jwt),
                        jwtUtil.getRolesFromJwtToken(jwt));

                //Authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        "no-password",
                        userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//??
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e);
        }
        //log.info("token ok!!!");
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return "";
    }
}
