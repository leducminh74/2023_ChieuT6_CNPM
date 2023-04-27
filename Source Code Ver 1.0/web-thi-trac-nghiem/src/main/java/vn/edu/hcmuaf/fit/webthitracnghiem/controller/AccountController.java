package vn.edu.hcmuaf.fit.webthitracnghiem.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Account;
import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Role;
import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Status;
import vn.edu.hcmuaf.fit.webthitracnghiem.mail.Mail;
import vn.edu.hcmuaf.fit.webthitracnghiem.random.RandomNumber;
import vn.edu.hcmuaf.fit.webthitracnghiem.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "")
	public String homePage(Model m, HttpSession session) {
		String view = "";
		Account acc = (Account) session.getAttribute("acc");
		if (acc == null) {
			m.addAttribute("account", new Account());
			view = "redirect:/login";
		} else if (acc.getIsAdmin().getRoleId() == 1) {
			view = "redirect:/admin/home";
		} else {
			view = "redirect:/index";
		}

		return view;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String viewLogin(Model m, HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a == null) {
			m.addAttribute("account", new Account());
			return "login";
		}
		if (a.getIsAdmin().getRoleId() == 1) {
			return "redirect:/admin/home";
		}

		return "redirect:/index";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model m, HttpSession session, @ModelAttribute("account") Account account) {
		String view = "";
		Account acc = accountService.login(account.getUsername(), account.getPassword());

		if (acc == null) {
			m.addAttribute("user", account.getUsername());
			m.addAttribute("success", "");
			m.addAttribute("message", "Username or Password is incorrect");
			m.addAttribute("account", new Account());
			view = "login";
		} else if (acc.getStatus().getsId() == 0) {
			m.addAttribute("user", account.getUsername());
			m.addAttribute("success", "");
			m.addAttribute("message",
					"Tài khoản của bạn chưa được kích hoạt. Vui lòng kiểm tra email và kích hoạt tài khoản");
			m.addAttribute("account", new Account());
			view = "login";
		} else if (acc.getStatus().getsId() == 2) {
			m.addAttribute("user", account.getUsername());
			m.addAttribute("success", "");
			m.addAttribute("message", "Tài khoản của bạn đã bị khóa vui lòng liên hệ cửa hàng để giải quyết");
			m.addAttribute("account", new Account());
			view = "login";
		} else {
			session.setAttribute("acc", acc);
			if (acc.getIsAdmin().getRoleId() == 1) {
				view = "redirect:/admin/home";
			} else {
				view = "redirect:/index";
			}
		}
		return view;
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String viewRegister(Model m, HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a == null) {
			m.addAttribute("account", new Account());
			return "register";
		}

		return "redirect:/index";

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(Model m, HttpSession session, @ModelAttribute("account") Account account) {
		String view = "";
		String subject = "Account verification";
		Account existingAccount = accountService.getAccountByUserName(account.getUsername());
		Account existingEmail = accountService.getAccountByEmail(account.getEmail());
		if (existingAccount != null) {
			m.addAttribute("username", account.getUsername());
			m.addAttribute("email", account.getEmail());
			m.addAttribute("success", "");
			m.addAttribute("message", "Tài khoản đã tồn tại");
			m.addAttribute("account", new Account());
			view = "register";
		} else if (existingEmail != null) {
			m.addAttribute("username", account.getUsername());
			m.addAttribute("email", account.getEmail());
			m.addAttribute("success", "");
			m.addAttribute("message", "Email đã tồn tại");
			m.addAttribute("account", new Account());
			view = "register";
		} else {
			Role userRole = accountService.getRoleByName("user");
			Status notActiveStatus = accountService.getStatusByName("Not Activated");
			Account a = new Account(account.getUsername(), accountService.hashPassword(account.getPassword()),
					account.getEmail(), userRole, notActiveStatus);
			accountService.saveAccount(a);
			String content = "Click here: " + " http://localhost:8080/verify-account?key1=" + account.getEmail()
					+ "&key2=" + accountService.hashPassword(account.getPassword()) + "  to verify your account";
			Mail.sendMail(account.getEmail(), subject, content);
			m.addAttribute("success", "success");
			m.addAttribute("message",
					"An account verification email has been sent to you. Please check your email and verify your account.");
			m.addAttribute("account", new Account());
			view = "register";
		}

		return view;
	}

	@RequestMapping(value = "verify-account", method = RequestMethod.GET)
	public String verifyAccount(Model m, @RequestParam String key1, @RequestParam String key2) {
		Account a = accountService.activeAccount(key1, key2);

		if (a != null) {
			m.addAttribute("message", "Tài khoản của bạn đã được kích hoạt!");
			m.addAttribute("user", a.getUsername());
			m.addAttribute("success", "success");
			m.addAttribute("account", new Account());
			return "login";
		} else {
			m.addAttribute("message", "Kích hoạt tài khoản thất bại");
			m.addAttribute("success", "");
			m.addAttribute("account", new Account());
			return "register";
		}

	}

	@RequestMapping(value = "change-password", method = RequestMethod.GET)
	public String vewChangePassword(Model m, HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a == null) {
			return "redirect:/login";
		}
		return "changepassword";
	}

	@RequestMapping(value = "change-password", method = RequestMethod.POST)
	public String changePassword(Model m, HttpSession session, @RequestParam String currentPassword,
			@RequestParam String newPassword) {

		Account a = (Account) session.getAttribute("acc");
		boolean checkSuccess = accountService.checkPassword(a, currentPassword);

		if (a == null) {
			return "redirect:/login";
		}
		if (checkSuccess) {
			accountService.changePassword(a, newPassword);
			m.addAttribute("message", "Đổi mật khẩu thành công!");
			m.addAttribute("success", "success");
		} else {
			m.addAttribute("message", "Mật khẩu hiện tại không chính xác!");
			m.addAttribute("success", "");
		}
		return "changepassword";
	}

	@RequestMapping(value = "forgot-password", method = RequestMethod.GET)
	public String viewForgotPassword(Model m, HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a == null) {
			return "forgotpassword";
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "forgot-password", method = RequestMethod.POST)
	public String forgotPassword(Model m, HttpSession session, @RequestParam String username,
			@RequestParam String email) {
		String subject = "Forgot Password";
		Account a = (Account) session.getAttribute("acc");
		if (a != null) {
			return "redirect:/index";
		}

		Account acc = accountService.getAccountByUserNameAndEmail(username, email);

		if (acc == null) {
			m.addAttribute("message", "Username và Email không trùng khớp. Vui lòng kiểm tra lại.");
			m.addAttribute("username", username);
			m.addAttribute("email", email);
			m.addAttribute("success", "");
		} else {
			String verifiCode = RandomNumber.randomAlphaNumeric();
			String content = "Your Verification Code is " + verifiCode;
			Mail.sendMail(email, subject, content);
			m.addAttribute("message", "Mã xác thực đã được gửi đến email của bạn. Vui lòng kiểm tra email!");
			m.addAttribute("username", username);
			m.addAttribute("email", email);
			m.addAttribute("success", "success");
			session.setAttribute("verifiCode", verifiCode);
			m.addAttribute("sendVerifiCode", true);
		}
		return "forgotpassword";
	}

	@RequestMapping(value = "checkVerifiCode", method = RequestMethod.GET)
	public String redirectForgotPass(Model m, HttpSession session) {
		Account a = (Account) session.getAttribute("acc");
		if (a != null) {
			return "redirect:/index";
		}
		return "redirect:/forgot-password";

	}

	@RequestMapping(value = "checkVerifiCode", method = RequestMethod.POST)
	public String checkCode(Model m, HttpSession session, @RequestParam String username, @RequestParam String email,
			@RequestParam String verifiCode) {
		String subject = "Forgot Password";
		Account a = (Account) session.getAttribute("acc");
		if (a != null) {
			return "redirect:/index";
		}

		String verifiCodeOld = (String) session.getAttribute("verifiCode");
		Account acc = accountService.getAccountByUserNameAndEmail(username, email);
		boolean checkCode = verifiCode.equals(verifiCodeOld);
		if (checkCode) {
			String newPass = RandomNumber.randomAlphaNumeric();
			acc.setPassword(newPass);
			accountService.saveAccount(acc);
			String content = "Your password is " + newPass;
			Mail.sendMail(email, subject, content);
			m.addAttribute("message", "Mật khẩu đã được gửi đến email của bạn. Vui lòng kiểm tra email!");
			m.addAttribute("user", username);
			m.addAttribute("success", "success");
			session.removeAttribute("verifiCode");
			m.addAttribute("account", new Account());
			return "login";
		} else {
			m.addAttribute("message", "Mã xác thực không chính xác. Vui lòng kiểm tra lại");
			m.addAttribute("success", "");
			m.addAttribute("username", username);
			m.addAttribute("email", email);
			m.addAttribute("sendVerifiCode", true);
			return "forgotpassword";
		}

	}

	@RequestMapping(value = "/logout")
	public String logout(Model m, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		m.addAttribute("user", new Account());
		session.removeAttribute("acc");
		return "redirect:/login";
	}

}
