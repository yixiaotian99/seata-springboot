package com.xiao.storage.service;

import com.xiao.storage.entity.Storage;
import com.xiao.storage.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class StorageService {

    private final StorageMapper storageMapper;


    public void deduct(String commodityCode, int count) {
        Storage storage = storageMapper.findByCommodityCode(commodityCode);
        storage.setCount(storage.getCount() - count);
        storageMapper.updateById(storage);
    }


}
