package vn.edu.hcmuaf.fit.webthitracnghiem.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Account;

@Controller
public class AdminController {

	@RequestMapping(value = "admin/home", method = RequestMethod.GET)
	public String index(HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a == null) {
			return "redirect:/login";
		} else if (a.getIsAdmin().getRoleId() != 1) {
			return "redirect:index";
		}
		return "adminhome";
	}
}
