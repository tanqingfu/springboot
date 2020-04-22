package com.demo.meta.vo;


import com.demo.util.I18nUtil;
import lombok.Getter;

@Getter
public class ResultVo<T> {

    private  String  code;

    private String msg;

    private T data;

    private String  createTime ;

    private  ResultVo(String code){
        this.code = code;
        setCode(code);
    }

    public void setCode(String code) {
        String message = null;
        try {
            message =I18nUtil.getMessage(code);
        }catch (Exception e){
            message = code;
        }
        this.code = code;
        this.msg = message;
    }

    /**
     * 默认成功返回
     * @param <T>
     * @return
     */
    public static<T> ResultVo<T> OK(){
        return new ResultVo<T>("SUCCESS");
    }

    /**
     * 返回只带code的信息
     * @param code
     * @param <T>
     * @return
     */
    public static<T> ResultVo<T> faild(String code){
        return new ResultVo<T>(code);
    }


}

