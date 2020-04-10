package com.demo.service.impl;

import com.demo.common.InquiryCondition;
import com.demo.dao.userMapper;
import com.demo.meta.user;
import com.demo.service.userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class userServiceImpl implements userService {

    @Resource userMapper userMapper;
    @Override
    public int countALL(InquiryCondition InquiryCondition) {
        int total = userMapper.countALL(InquiryCondition);
        return total;
    }

    @Override
    public List<user> selectAll(InquiryCondition InquiryCondition) {
        List<user> list = userMapper.selectAll(InquiryCondition);
        return list;
    }
}
