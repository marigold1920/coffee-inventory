package coffee.inventory.service;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Account;

public interface AccountService {

    ResponseModel createAccount(Account account);

    Account loadAccountByUsername(String username);

    boolean checkLogin(Account account);

    ResponseModel login(Account account);
}
