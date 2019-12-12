package coffee.inventory.service.implement;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Account;
import coffee.inventory.repository.AccountRepository;
import coffee.inventory.service.AccountService;
import coffee.inventory.service.JwtService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    public AccountServiceImpl(AccountRepository accountRepository, JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseModel createAccount(Account account) {
        ResponseModel response = new ResponseModel();
        Account result = accountRepository.saveAndFlush(account);
        response.addData(result);

        return response;
    }

    @Override
    public Account loadAccountByUsername(String username) {

        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public boolean checkLogin(Account a) {
        Account account = accountRepository.findAccountByUsername(a.getUsername());
        if (account == null) {
            return false;
        }

        return BCrypt.checkpw(a.getPassword(), account.getPassword());
    }

    @Override
    public ResponseModel login(Account a) {
        ResponseModel response = new ResponseModel();
        String result;
        String httpStatus;
        
        try {
            if (checkLogin(a)) {
                Account account = accountRepository.findAccountByUsername(a.getUsername());
                result = jwtService.generateTokenLogin(account.getUsername(), account.getEmployee().getId());
                httpStatus = "OK";
            } else {
                result = "Wrong username and password";
                httpStatus = "Bad Request";
            }
        } catch (Exception e) {
            result = "Server Error";
            httpStatus = "Internal server error";
        }
        response.addData(result);

        return response;
    }


}
