package vn.hcmuaf.edu.fit.lab6.controler;

import vn.hcmuaf.edu.fit.lab6.beans.Account;
import vn.hcmuaf.edu.fit.lab6.dao.AccountDao;
import vn.hcmuaf.edu.fit.lab6.mail.Mail;
import vn.hcmuaf.edu.fit.lab6.random.RandomPassword;
import vn.hcmuaf.edu.fit.lab6.service.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterControler", value = "/register")
public class RegisterControler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("register.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String user = request.getParameter("Username");
            String pass = request.getParameter("Password");
            String email = request.getParameter("Email");

            String subject = "Account verification";

            Account checkUser = AccountService.getInstance().checkUserExist(user);
            Account checkEmail = AccountService.getInstance().checkEmailExist(email);
            if(checkUser != null){
                request.setAttribute("user", user);
                request.setAttribute("email", email);
                request.setAttribute("success", "");
                request.setAttribute("message", "Username already exists");
                request.getRequestDispatcher("register.jsp").forward(request,response);

            }else if(checkEmail != null) {
                request.setAttribute("user", user);
                request.setAttribute("email", email);
                request.setAttribute("success", "");
                request.setAttribute("message", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);

            }else{
                AccountService.getInstance().register(user,pass,email);
                String hashPass = AccountDao.getInstance().hashPassword(pass);
                String content = "Click here: " + " http://localhost:8080/lab6_war_exploded/VerifyAccountController?key1=" + email + "&key2=" + hashPass + "  to verify your account";
                Mail.sendMail(email,subject,content);
                request.setAttribute("success", "success");
                request.setAttribute("message", "An account verification email has been sent to you. Please check your email and verify your account.");
                request.getRequestDispatcher("register.jsp").forward(request,response);


            }
        }catch (Exception e){

        }

    }
}
