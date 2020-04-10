package com.demo.service;

import com.demo.common.InquiryCondition;
import com.demo.meta.user;

import java.util.List;

public interface userService {
    public int countALL(InquiryCondition InquiryCondition);
    public List<user> selectAll(InquiryCondition InquiryCondition);
}
