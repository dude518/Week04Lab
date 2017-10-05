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
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String username;
        String action = request.getParameter("action");
        String sessionUser = (String) session.getAttribute("user");
        
        for(Cookie c : cookies)
        {
            if(c.getName().equals("usernameCookie"))
            {
                username = c.getValue();
                request.setAttribute("username", username);
                request.setAttribute("remember", "checked");
            }
        }
        if(action != null && action.equals("loggedout"))
        {
            session.removeAttribute("user");
            request.setAttribute("incorrect", "You have successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }
        
        if(sessionUser != null)
        {
            response.sendRedirect("home");
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";
        String rememberMe = request.getParameter("rememberme");
        
        if(username == null || username.equals("") || password == null || password.equals(""))
        {
            message = "Both feilds need to be filled out.";
        }
        else
        {
            UserService user = new UserService();
            if(user.login(username, password) != null)
            {
                request.setAttribute("user", user);
                if(rememberMe != null)
                {
                    Cookie c = new Cookie("usernameCookie", username);
                    c.setMaxAge(3600);
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
                session.setAttribute("user", username);
                response.sendRedirect("home");
                return;
            }
            else
            {
                message = "Username or password are incorrect.";
            }
        }
        
        request.setAttribute("username", username);
        request.setAttribute("incorrect", message);
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

}
