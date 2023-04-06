package vn.hcmuaf.edu.fit.lab6.controler;

import vn.hcmuaf.edu.fit.lab6.beans.Account;
import vn.hcmuaf.edu.fit.lab6.service.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePasswordControler", value = "/changepassword")
public class ChangePasswordControler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("changepassword.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String currentPass = request.getParameter("Current_password");
            String newPass = request.getParameter("New_password");

            Account a  = (Account) session.getAttribute("acc");
            boolean checkSuccess = AccountService.getInstance().checkPasswordCorrect(a,currentPass);
            if(checkSuccess){
                AccountService.getInstance().changePassword(a,newPass);
                response.sendRedirect("index");
            }else{
                request.setAttribute("message","Current Password is incorrect!");
                request.getRequestDispatcher("changepassword.jsp").forward(request,response);
            }

        }catch (Exception e){

        }

    }
}
