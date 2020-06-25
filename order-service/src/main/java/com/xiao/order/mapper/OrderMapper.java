package com.xiao.order.mapper;

import com.xiao.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    int insert(@Param("order") Order record);

}