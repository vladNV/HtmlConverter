package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

@WebFilter(urlPatterns = {"/*"}, servletNames = "PantheraOncaServlet")
public class AuthorizedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private static final HashMap<String, Integer> pages = new HashMap<>();

    static {
        pages.put("/site/sender", 2);
        pages.put("/site/convert", 2);
        pages.put("/site/openExcel", 2);
        pages.put("/site/mail", 2);
        pages.put("/rest/upload", 2);
        pages.put("/site/login", 1);
        pages.put("/site/sign", 1);
        pages.put("/site/logout", 0);
        pages.put("/", 0);
        pages.put("/bootstrap-3.3.7-dist/js/bootstrap.min.js",0);
        pages.put("/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css",0);
        pages.put("/css/styles.css", 0);
        pages.put("/bootstrap-3.3.7-dist/css/bootstrap.min.css", 0);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        int group = session.getAttribute("login") == null ? 1 : 2;
        String page = req.getRequestURI();
        if (pages.get(page) != null) {
            if (pages.get(page) != 0 && pages.get(page) != group) {
                resp.sendError(403);
                return;
            }
        } else {
            resp.sendError(400);
            return;
        }
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
