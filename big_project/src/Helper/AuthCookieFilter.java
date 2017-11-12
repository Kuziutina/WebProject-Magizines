package Helper;

import Objects.User;
import Repositories.UserRepo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuthCookieFilter")
public class AuthCookieFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        UserRepo userRepo = new UserRepo();

        Cookie[] cookies = request.getCookies();     // request is an instance of type

        boolean foundCookie = false;
        String userId = "";

        for(int i = 0; i < cookies.length && !foundCookie; i++)
        {
            Cookie c = cookies[i];
            if (c.getName().equals("userid"))
            {
                userId= c.getValue();
                foundCookie = true;
            }
        }

        if (foundCookie) {
            User user = userRepo.getUserByCoockie(userId);
            if (user != null) {
                request.getSession().setAttribute("current_user", user);
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
