package banking.configuration;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import banking.services.UserService;

@Component
public class BankingAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
 
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
 
        if (roles.contains("ROLE_EMPLOYEE")) {
            httpServletResponse.sendRedirect("/banking/employee/main");
        } else {
            httpServletResponse.sendRedirect("/banking/customer/main");
        }
    }
}
