package com.example.WebShop.Filter;

import com.example.WebShop.Service.Impl.UserDetailsServiceImpl;
import com.example.WebShop.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetalService;
    private final JwtUtil jwtUtil;
    private  UserDetails userDetails;
        @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String authHeader=request.getHeader("Authorization");
            String token=null;
            String username=null;
            if(authHeader!=null && authHeader.startsWith("Bearer "))
            {
                token= authHeader.substring(7);
                System.out.println(token);
                username= jwtUtil.extractUsername(token);
                System.out.println(username);

            }
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                 this.userDetails = userDetalService.loadUserByUsername(username);
                //UserEntity user = userDetalService.loadUserByUsername(username);
//                System.out.println(userDetails+"jwt");
//
                System.out.println( userDetails.getAuthorities());
                if(jwtUtil.ValidateToken(token, userDetails))
                {
                    System.out.println("email:"+ userDetails.getUsername());
                    System.out.println("role:"+ userDetails.getAuthorities());
                    System.out.println("pass:"+ userDetails.getPassword());
                    //UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,new HashMap<>());
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
                    System.out.println("authToken"+authToken);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
            }
            filterChain.doFilter(request,response);
        }
}
