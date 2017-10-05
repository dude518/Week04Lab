/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylogin;

import business_service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 578291
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";
        String rememberMe = request.getParameter("rememberme");
        
        if(username != null && !username.equals("") && password != null && !password.equals(""))
        {
            message = "Both feilds need to be filled out.";
        }
        else
        {
            UserService user = new UserService();
            user.login(username, password);
            if(user != null)
            {
                request.setAttribute("user", user);
                if(rememberMe != null)
                {
                    Cookie c = new Cookie("usernameCookie", username);
                    c.setMaxAge(60);
                    c.setPath("/");
                    response.addCookie(c);
                }
                else
                {
                    Cookie[] cookies = request.getCookies();
                    for(Cookie c : cookies)
                    {
                        if(c.getName().equals("usernameCookie"))
                        {
                            c.setMaxAge(0);
                            c.setPath("/");
                            response.addCookie(c);
                        }
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/home");
            }
            else
            {
                message = "Username or password are incorrect.";
            }
        }
        
        request.setAttribute("username", username);
        request.setAttribute("incorrect", message);
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
