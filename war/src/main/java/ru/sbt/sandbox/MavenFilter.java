package ru.sbt.sandbox;

import javax.servlet.*;
import java.io.IOException;

public class MavenFilter implements Filter {
    private String filtered;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filtered = filterConfig.getInitParameter("filteredName");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        String name = servletRequest.getParameter("userName");
        if (filtered != null && filtered.equalsIgnoreCase(name)) {
            servletResponse.getWriter().write("Access denided!");
            System.out.println("Filtered");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

