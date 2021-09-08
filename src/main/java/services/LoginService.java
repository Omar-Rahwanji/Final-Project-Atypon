package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginService {
    private LoginService(){}

    public static boolean validateAdmin(HttpServletRequest request, HttpSession session){
        String password = request.getParameter("password");
        session.setAttribute("password",password);
        return password.equals("0000");
    }
}
