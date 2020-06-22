package org.sid.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class  JWTAuthotizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-control-Request-Method, Access-control-Request-Headers, authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            String jwt = request.getHeader(SecurityParams.JWT_HEADER_NAME);
            if (jwt == null || !jwt.startsWith(SecurityParams.HEADER_PREFIXE)) {
                filterChain.doFilter(request, response);
                return;
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(jwt.substring(SecurityParams.HEADER_PREFIXE.length()));
            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(rn -> {
                authorities.add(new SimpleGrantedAuthority(rn));
            });
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);

        }
    }
}
