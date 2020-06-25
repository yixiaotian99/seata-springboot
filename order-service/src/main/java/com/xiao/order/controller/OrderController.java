package com.xiao.order.controller;

import com.xiao.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderController {

    private final OrderService orderService;

    /**
     * 单机测试生成订单
     * http://localhost:8082/api/order/debit?userId=1001&commodityCode=2001&count=100
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @GetMapping(value = "/debit")
    public void debit(@RequestParam String userId, @RequestParam String commodityCode, @RequestParam Integer count) {
        System.out.println("order XID " + RootContext.getXID());
        orderService.create(userId, commodityCode, count);

        log.info("订单创建, userId:{}, commodityCode:{}, count:{}", userId, commodityCode, count);
    }

}
