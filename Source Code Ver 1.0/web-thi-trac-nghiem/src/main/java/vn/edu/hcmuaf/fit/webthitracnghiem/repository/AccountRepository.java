package vn.edu.hcmuaf.fit.webthitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Account;
import vn.edu.hcmuaf.fit.webthitracnghiem.entity.Status;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUsername(String username);

	Account findOneByUsernameAndStatus(String username, Status activated);

	Account findByEmailAndPasswordAndStatus(String email, String password, Status s);

	Account findByEmail(String email);

	Account findByUsernameAndPassword(String username, String password);

	Account findByUsernameAndEmail(String username, String email);
}
