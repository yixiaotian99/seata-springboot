package com.xiao.storage.controller;

import com.xiao.storage.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping("/api/storage")
@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class StorageController {


    private final StorageService storageService;

    /**
     * 单机测试扣减库存
     * http://localhost:8083/api/storage/deduct?commodityCode=2001&count=5
     * @param commodityCode
     * @param count
     * @throws SQLException
     */
    @GetMapping(value = "/deduct")
    public void deduct(@RequestParam String commodityCode, @RequestParam Integer count) throws SQLException {
        System.out.println("storage XID " + RootContext.getXID());
        storageService.deduct(commodityCode, count);

        log.info("库存扣减, commodityCode:{}, count:{}", commodityCode, count);
    }

}