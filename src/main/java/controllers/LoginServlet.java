package controllers;

import cache_components.Cache;
import cache_components.CacheFactory;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "servlets.LoginServlet", value = "/login.do")
public class LoginServlet extends HttpServlet {
    protected CacheFactory cacheFactory = CacheFactory.getCacheFactory();
    protected Cache[] cachedRecords = {cacheFactory.getCacheByType("Database", 500), cacheFactory.getCacheByType("Database", 500)};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            HttpSession session = request.getSession();
            session.setAttribute("cachedRecords", cachedRecords);
            String userType;
            if(LoginService.validateAdmin(request, session))
                userType="admin";
            else
                userType="regular";
            session.setAttribute("userType", userType);
            request.getRequestDispatcher("/WEB-INF/views/" + userType + "_page.jsp").forward(request, response);
    }
}
