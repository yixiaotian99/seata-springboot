package com.xiao.business.controller;

import com.xiao.business.service.BusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/api/business")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BusinessController {

    private final BusinessService businessService;

    /**
     * 购买下单，模拟全局事务提交
     * http://localhost:8084/api/business/purchase/commit
     *
     * @return
     */
    @RequestMapping("/purchase/commit")
    public Boolean purchaseCommit(HttpServletRequest request) {
        businessService.purchase("1001", "2001", 1);

        log.info("事务提交, userId:{}, commodityCode:{}, orderCount:{}", 1001, 2001, 1);
        return true;
    }

    /**
     * 购买下单，模拟全局事务回滚
     * http://localhost:8084/api/business/purchase/rollback
     *
     * @return
     */
    @RequestMapping("/purchase/rollback")
    public Boolean purchaseRollback() {
        try {
            businessService.purchase("1002", "2001", 1);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("事务回滚, userId:{}, commodityCode:{}, orderCount:{}", 1002, 2001, 1);
            return false;
        }

        return true;
    }
}