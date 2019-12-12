package coffee.inventory.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import coffee.inventory.entity.Account;
import coffee.inventory.service.AccountService;
import coffee.inventory.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final static String TOKEN_HEADER = "authorization";

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String authToken = request.getHeader(TOKEN_HEADER);
        if (jwtService.validateTokenLogin(authToken)) {
            String username = jwtService.getUsernameFromToken(authToken);
            Account account = accountService.loadAccountByUsername(username);
            if (account != null) {
                UserDetails userDetails = new User(username, account.getPassword(), true, true,
                        true, true, account.grantedAuthorities());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(req, res);
    }
}
