package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.meta.vo.ResultVo;



@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/gettest",method = RequestMethod.GET)
    public ResultVo test(){
        ResultVo resultVo = ResultVo.faild("code-0001");

        return resultVo;
    }


}


