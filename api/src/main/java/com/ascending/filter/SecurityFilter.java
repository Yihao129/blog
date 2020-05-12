package com.ascending.filter;

import com.ascending.model.ProjectAttribute;
import com.ascending.model.User;
import com.ascending.service.JWTService;
import com.ascending.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter",urlPatterns = {"/*"},dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    @Autowired
    private JWTService jwt;

    @Autowired
    private UserService userService;

    private static final Set<String> IGNORED_PATHS = new HashSet<String>(Arrays.asList("/auth","/auth/signUp"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (userService == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletRequest.getServletContext());
        }

        int status = authorize(servletRequest, servletResponse);
        if(status == HttpServletResponse.SC_ACCEPTED){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).sendError(status);
        }

    }

    @Override
    public void destroy() {

    }


    private int authorize(ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String uri = req.getRequestURI();
        if(IGNORED_PATHS.contains(uri)) return HttpServletResponse.SC_ACCEPTED;

        String method = req.getMethod();
        Claims claims = null;
        try{
            // validate the jwt
            try{
                String token = req.getHeader("Authorization").replaceAll("^(.*?) ","");
                claims = jwt.decodeToken(token);
                User u = userService.getById(Long.valueOf(claims.getId()));
                if(u==null) return HttpServletResponse.SC_UNAUTHORIZED;
                else{
                    session.setAttribute(ProjectAttribute.SESSION_USER_ID,u.getId());
                }

            }catch (Exception e){
                return HttpServletResponse.SC_UNAUTHORIZED;
            }

            int r;
            boolean valid;
            switch (method){
                case "GET"  :
                    valid = valid_from_string((String) claims.get("allowedReadResources"),uri);
                    if(valid) r = HttpServletResponse.SC_ACCEPTED;
                    else r = HttpServletResponse.SC_FORBIDDEN;
                    break;
                case "POST":
                    valid = valid_from_string((String) claims.get("allowedCreateResources"),uri);
                    if(valid) r = HttpServletResponse.SC_ACCEPTED;
                    else r = HttpServletResponse.SC_FORBIDDEN;
                    break;
                case "PUT":
                    valid = valid_from_string((String) claims.get("allowedUpdateResources"),uri);
                    if(valid) r = HttpServletResponse.SC_ACCEPTED;
                    else r = HttpServletResponse.SC_FORBIDDEN;
                    break;
                case "DELETE":
                    valid = valid_from_string((String) claims.get("allowedDeleteResources"),uri);
                    if(valid) r = HttpServletResponse.SC_ACCEPTED;
                    else r = HttpServletResponse.SC_FORBIDDEN;
                    break;
                default:
                    r = HttpServletResponse.SC_FORBIDDEN;
            }
            return r;
        }catch (Exception e){
            return HttpServletResponse.SC_FORBIDDEN;
        }



    }

    // rights like: "/author,/comment"  request like: "/author"
    public boolean valid_from_string(String rights, String request){
        for(String i:rights.split(",")){
            if(request.matches(i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String t = "\\";
        System.out.println("/comments/1006".matches("/comments?(/[a-z0-9]+)?"));

        System.out.println("\\");
    }


}
