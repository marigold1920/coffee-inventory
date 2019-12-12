package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coffee.inventory.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsername(String username);
}
