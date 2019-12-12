package coffee.inventory.controller;

import org.springframework.web.bind.annotation.*;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Account;
import coffee.inventory.service.AccountService;

@RestController
@RequestMapping("/api/v1.0/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseModel login(@RequestBody Account account) {

        return accountService.login(account);
    }

}
