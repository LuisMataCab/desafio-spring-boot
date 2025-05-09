package cl.nuevaspa.test.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFiltroAutenticacion extends OncePerRequestFilter {
    private final JwtTokenConfig jwtTokenConfig;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        final String username;

        if(token == null){
            filterChain.doFilter(request,response);

            return;
        }

        username = jwtTokenConfig.getUsernameFromToken(token);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtTokenConfig.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken tokenAutorizado = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                tokenAutorizado.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(tokenAutorizado);
            }
        }

        filterChain.doFilter(request,response);
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        final String cabeceraAutentica = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(cabeceraAutentica) && cabeceraAutentica.startsWith("Bearer ")){
            return cabeceraAutentica.substring(7);
        }

        return null;
    }
}
