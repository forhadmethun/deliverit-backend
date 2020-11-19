package com.deliverit.config.jwt;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deliverit.service.JwtUserDetailsServiceImpl;
import com.deliverit.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(Utility.HEADER_UAUTHORIZATION);

        Optional<String> jwtToken = getJwtToken(requestTokenHeader);
        jwtToken
                .map(token -> getUsername(token))
                .map(userName -> {
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        validateUserToken(userName.get(), jwtToken.get(), request);
                    }
                    return Optional.empty();
                })
                .orElseGet(() -> {
                    return Optional.empty();
                });

        chain.doFilter(request, response);
    }

    private Optional<String> getJwtToken(String requestTokenHeader){
        if (requestTokenHeader != null && requestTokenHeader.startsWith(Utility.HEADER_TOKEN_PREFIX)) {
            return Optional.of(requestTokenHeader.substring(Utility.HEADER_TOKEN_PREFIX_LENGTH));
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            return Optional.empty();
        }
    }

    private Optional<String> getUsername(String jwtToken){
        try {
            return Optional.of(jwtTokenUtil.getUsernameFromToken(jwtToken));
        } catch (IllegalArgumentException e) {
            log.error("Unable to get JWT Token: Error: ", e.toString());
        } catch (ExpiredJwtException e) {
            log.error("JWT Token has expired: Error: ", e.toString());
        }
        return Optional.empty();
    }

    private void validateUserToken(String userName, String jwtToken, HttpServletRequest request){
        var userDetails = jwtUserDetailsService.loadUserByUsername(userName);
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

}
