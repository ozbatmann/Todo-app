package app.todo.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsConfig implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token, Access-Control-Allow-Headers, Access-Control-Max-Age, Access-Control-Allow-Methods, Access-Control-Allow-Origin, Access-Control-Allow-Credentials, origin, x-requested-with, content-type");

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

}
