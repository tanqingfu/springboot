package com.demo.common;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class JsonData implements Serializable {

	private static final long serialVersionUID = -3672715358223097562L;

	private String status;
	
	private String errMsg;
	
	private int totalCount;
	
	@JSONField(name="data")
	private Object data;
	
	private PropertyFilter filter;
	
	static{
		SerializeConfig mapping = SerializeConfig.getGlobalInstance();
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		//mapping.put(Money.class, BigDecimalCodec.instance);
	}
	
    public JsonData(){}
    
    public JsonData(String status, String errMsg){
    	this.status = status;
    	this.errMsg = errMsg;
    }

    public JsonData(String status, String errMsg, Object obj){
    	this.status = status;
    	this.errMsg = errMsg;
    	this.data = obj;
    }
    public JsonData(String status, String errMsg, Object obj, int totalCount){
    	this.totalCount = totalCount;
    	this.status = status;
    	this.errMsg = errMsg;
    	this.data = obj;
    }
    
    public JsonData(String status, String errMsg, Object obj, PropertyFilter filter){
    	this.status = status;
    	this.errMsg = errMsg;
    	this.data = obj;
    	this.filter = filter;
    }
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
    
    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setFilter(PropertyFilter filter) {
		this.filter = filter;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String toJsonString(){
    	return JSON.toJSONString(this, filter);
    }
	
	public static final <T> T parseObject(String text, Class<T> clazz){
		return JSON.parseObject(text, clazz);
	}
}
