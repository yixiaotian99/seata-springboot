package com.xiao.account.controller;

import com.xiao.account.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AccountController {

    private final AccountService accountService;

    /**
     * 单机测试账户扣减 http://localhost:8081/?userId=1001&orderMoney=100
     *
     * @param userId
     * @param orderMoney
     */
    @GetMapping
    public void debit(@RequestParam String userId, @RequestParam BigDecimal orderMoney) {
        System.out.println("account XID " + RootContext.getXID());
        accountService.debit(userId, orderMoney);

        log.info("用户扣减, userId:{}, orderMoney:{}", userId, orderMoney);
    }

}
