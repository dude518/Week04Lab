/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_service;

/**
 *
 * @author 578291
 */
public class UserService {
    private String username;
    private String password;

    public User login(String username, String pass)
    {
        User user = null;
        if(username != null && !username.equals("") && pass != null && !pass.equals(""))
        {
            if(username.equals("adam") || username.equals("betty"))
            {
                if(pass.equals("password"))
                {
                    user = new User();
                    user.setUsername(username);
                }
            }
        }
        return user;
    }
    
}
