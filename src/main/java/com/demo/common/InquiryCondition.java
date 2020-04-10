package com.demo.common;

import com.demo.util.DateUtils;
import com.demo.util.PageUtils;

import java.io.Serializable;
import java.util.Date;


public class InquiryCondition implements Serializable {
	
	

	

	private static final long serialVersionUID = -2548990423432482220L;
	
	private String cndtn1;
	private String cndtn2;
	private String cndtn3;
	private String cndtn4;
	private String cndtn5;
	private String cndtn6;
	private String cndtn7;
	private String cndtn8;
	private String cndtn9;
	private String cndtn10;
	private String cndtn11;
	private String cndtn12;
	private String cndtn13;
	private String cndtn14;
	private String cndtn15;
	private String startTime;
	private String endTime;
	private PageUtils page;
	
	public InquiryCondition(){
		page = new PageUtils();
	}
	public String getCndtn1() {
		return cndtn1;
	}

	public String getCndtn2() {
		return cndtn2;
	}

	public String getCndtn3() {
		return cndtn3;
	}

	public String getCndtn4() {
		return cndtn4;
	}

	public String getCndtn5() {
		return cndtn5;
	}

	public String getCndtn6() {
		return cndtn6;
	}

	public String getCndtn7() {
		return cndtn7;
	}

	public PageUtils getPage() {
		return page;
	}

	public void setCndtn1(String cndtn1) {
		this.cndtn1 = cndtn1;
	}

	public void setCndtn2(String cndtn2) {
		this.cndtn2 = cndtn2;
	}

	public void setCndtn3(String cndtn3) {
		this.cndtn3 = cndtn3;
	}

	public void setCndtn4(String cndtn4) {
		this.cndtn4 = cndtn4;
	}

	public void setCndtn5(String cndtn5) {
		this.cndtn5 = cndtn5;
	}

	public void setCndtn6(String cndtn6) {
		this.cndtn6 = cndtn6;
	}

	public void setCndtn7(String cndtn7) {
		this.cndtn7 = cndtn7;
	}

	public void setPage(PageUtils page) {
		this.page = page;
	}

	public String getCndtn8() {
		return cndtn8;
	}

	public String getCndtn9() {
		return cndtn9;
	}

	public String getCndtn10() {
		return cndtn10;
	}

	public String getCndtn11() {
		return cndtn11;
	}

	public String getCndtn12() {
		return cndtn12;
	}

	public String getCndtn13() {
		return cndtn13;
	}

	public String getCndtn14() {
		return cndtn14;
	}

	public String getCndtn15() {
		return cndtn15;
	}

	public void setCndtn8(String cndtn8) {
		this.cndtn8 = cndtn8;
	}

	public void setCndtn9(String cndtn9) {
		this.cndtn9 = cndtn9;
	}

	public void setCndtn10(String cndtn10) {
		this.cndtn10 = cndtn10;
	}

	public void setCndtn11(String cndtn11) {
		this.cndtn11 = cndtn11;
	}

	public void setCndtn12(String cndtn12) {
		this.cndtn12 = cndtn12;
	}

	public void setCndtn13(String cndtn13) {
		this.cndtn13 = cndtn13;
	}

	public void setCndtn14(String cndtn14) {
		this.cndtn14 = cndtn14;
	}

	public void setCndtn15(String cndtn15) {
		this.cndtn15 = cndtn15;
	}
	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @Description: 有根据时间进行查询时重置开始、结束时间
	 * @Param InquiryCondition
	 */
	public void resetTime(){
		if ((startTime != null && !startTime.equals("")) || (endTime != null && !endTime.equals(""))) {
			resetStartTime();
			resetEndTime();
		}
	}
	
	/**
	 * @Description: 开始时间
	 * @Param String startDate
	 * @return String startDate
	 */
	private void resetStartTime() {
		if (startTime == null || startTime.equals("")) {
			startTime = DateUtils.dateToString(new Date(1), "yyyy-MM-dd");
		} 
		if(startTime.length() < 11){
			startTime += " 00:00:00";
		}
	}

	/**
	 * @Description: 结束时间
	 * @Param Date endDate
	 * @return String endDate
	 */
	private void resetEndTime() {
		if (endTime == null || endTime.equals("")) {
			endTime =  DateUtils.dateToString(new Date(), "yyyy-MM-dd");
		}
		if(endTime.length() < 11){
			endTime += " 23:59:59";
		}
	}
	
}
