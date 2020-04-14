package com.demo.dao;

import com.demo.common.InquiryCondition;
import com.demo.meta.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface userMapper {
    public int countALL(InquiryCondition InquiryCondition);
    public List<user> selectAll(InquiryCondition InquiryCondition);
}
