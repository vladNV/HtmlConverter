package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, servletNames = "PantheraOncaServlet",
           initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {
    private String charset;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (charset != null && !charset.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(charset);
            response.setCharacterEncoding(charset);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        charset = null;
    }
}
