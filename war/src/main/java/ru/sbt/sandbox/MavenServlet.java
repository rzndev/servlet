package ru.sbt.sandbox;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MavenServlet extends HttpServlet {
    public static final String NAME_ATTRIBUTE = "Name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("Sample Maven Servlet<br/>");
        String name = (String) req.getSession().getAttribute(NAME_ATTRIBUTE);
        if (name != null) {
            writer.write("Hello, " + name);
        } else {
            writer.write(
                    "<form method=\"post\">User Name: <input type=\"text\" name=\"userName\"></form>"
            );
        }
        writer.write("</body></html>");
        writer.close();
        System.out.println(">>>>> Served");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(NAME_ATTRIBUTE, req.getParameter("userName"));
        resp.sendRedirect(req.getContextPath() + req.getServletPath());
    }

}
