package vn.edu.hcmuaf.fit.webthitracnghiem.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Account;
import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Role;
import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Status;
import vn.edu.hcmuaf.fit.webthitracnghiem.repository.AccountRepository;
import vn.edu.hcmuaf.fit.webthitracnghiem.repository.RoleRepository;
import vn.edu.hcmuaf.fit.webthitracnghiem.repository.StatusRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StatusRepository statusRepository;

	public Account getAccountByUserName(String username) {
		return accountRepository.findByUsername(username);
	}
	
	public Account getAccountByUserNameAndEmail(String username,String email) {
		return accountRepository.findByUsernameAndEmail(username,email);
	}

	public Optional<Account> getAccountById(int id) {
		return accountRepository.findById(id);
	}

	public Account login(String username, String password) {
		return accountRepository.findByUsernameAndPassword(username, hashPassword(password));
	}

	public Account getAccountActivatedByUserName(String username) {
		Status activated = getStatusByName("Activated");
		if(activated == null) {
			activated = new Status(0, "Activated");
		}
		return accountRepository.findOneByUsernameAndStatus(username, activated);
	}

	public Account getAccountByEmail(String email) {

		return accountRepository.findByEmail(email);
	}

	public Account getAccountNotActivatedByEmailAndPassword(String email, String password) {
		Status notActivated = getStatusByName("Not Activated");
		if(notActivated == null) {
			notActivated = new Status(0, "Not Activated");
		}
		return accountRepository.findByEmailAndPasswordAndStatus(email, password, notActivated);
	}

	public void saveAccount(Account a) {
		accountRepository.save(a);
	}

	public Account activeAccount(String email, String hashPass) {
		Account a = getAccountNotActivatedByEmailAndPassword(email, hashPass);
		if (a == null) {
			return null;
		}
		Status activated = getStatusByName("Activated");
		if(activated == null) {
			activated = new Status(0, "Activated");
		}
		a.setStatus(activated);
		saveAccount(a);
		return a;

	}

	public Role getRoleByName(String name) {
		return roleRepository.findByroleName(name);
	}

	public Status getStatusByName(String name) {
		return statusRepository.findBysName(name);
	}

	public boolean checkPassword(Account a, String password) {
		return a.getPassword().equals(hashPassword(password));

	}

	public void changePassword(Account a, String newPass) {
		a.setPassword(hashPassword(newPass));
		saveAccount(a);

	}

	public String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			BigInteger number = new BigInteger(1, byteData);
			return number.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
