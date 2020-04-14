package com.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.common.Globals;
import com.demo.common.InquiryCondition;
import com.demo.util.DateUtils;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * 报表相关
 * @author tqf
 * @version V1.0, 创建日期：2017-05-04
 */
@Controller
public class ExportReportController extends BaseController {
	
	/**
	 * 导出报表
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/exportReport", method = RequestMethod.POST)
	public void allAcountsData(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String msg = "";
		try {
			String fileName = "";
			//时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//表格每列标题
			String excelTitle = request.getParameter("fileTitle");//"方案ID,用户ID,玩法,方案状态,是否上榜,推荐理由,方案金额,SP值,发单时间";
			String projectId = request.getParameter("projectId");//导出类型
			if(projectId == null || "".equals(projectId)){
				projectId = "0";
			}
			InquiryCondition inquiryCondition = new InquiryCondition();
			//inquiryCondition.setUserId(Integer.parseInt(projectId));
			String [] title = null;
			if(excelTitle != null && !"".equals(excelTitle)){
				title = excelTitle.split(",");
			}
			//获取开始时间
		//	long start = System.currentTimeMillis();
			//输出的excel的路径
			String random = DateUtils.dateToString(new Date(), "yyyyMMddHHmmss");
			String filePath = "";
//			String filePath = request.getParameter("file");
			
//			//工作表的类型
//			String type = request.getParameter("type");
//			if(type != null && !"".equals(type)){
//				filePath = filePath + type + "-" + start + ".xls";
//			}
			//创建Excel工作薄
			WritableWorkbook wwb;
			response.setContentType("application/force-download");
			response.setContentType("application/vnd.ms-excel");
			
			response.setContentType("text/html;charset=UTF-8");
			
			OutputStream os = response.getOutputStream();
			
			wwb = Workbook.createWorkbook(os);
			//添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet("用户方案记录", 0);
			//WritableSheet sheet2 = wwb.createSheet("用户方案记录", 1);
			jxl.write.Label label ;
			
			WritableFont fontTitle = new WritableFont(WritableFont.TIMES, 9, WritableFont.NO_BOLD);  
			WritableCellFormat wc = new WritableCellFormat(fontTitle); 
			//单元格的文字按照单元格的列宽来自动换行显示。
//			wc.setWrap(true);  
			// 设置居中  
			wc.setAlignment(Alignment.CENTRE);  
			// 设置边框线  
			wc.setBorder(Border.ALL, BorderLineStyle.THIN);  
			// 设置单元格的背景颜色  
//			wc.setBackground(jxl.format.Colour.RED);
			for(int i =0;i<title.length;i++){
				//Lable(x,y,z)代表单元格的第x+1列,y+1行，内容z
				//在Label对象的子对象中指明单元格的位置和内容
				label = new jxl.write.Label(i,0,title[i],wc);
				//将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}
			//下面是填充数据
			if(projectId == "0" || "0".equals(projectId)){
				fileName="模板";
			}else{
				fileName = "用户数据记录";
			}
				
				List<String> list = null;
				/*if (projectId != null && !"".equals(projectId)) {
					list = schemeService.allselectByUserId(inquiryCondition);
				}else{
					list = schemeService.allselectByUserId(inquiryCondition);
				}*/
				//ExportReportController controller = new ExportReportController();
				/**
				 * 保存数字到单元格，需要使用jxl.write.Number
				 * 必须使用其完整路径，否则会出现错误
				 */
				sheet = this.addCheckAllAccounts(sheet, list, wc, sdf);
				//充值记录
			
			
			filePath=fileName+random+".xls";
			String agent = request.getHeader("USER-AGENT"); //获取浏览器的信息
			if(agent != null && agent.toLowerCase().indexOf("firefox")>0){
				//火狐浏览器自己会对URL进行一次URL转码所以区别处理
				response.setHeader("Content-Disposition",
						"attachment; filename="+ new String(filePath.getBytes("GB2312"),"ISO-8859-1"));
			}else if(agent.toLowerCase().indexOf("safari")>0){
				//苹果浏览器需要用ISO 而且文件名得用UTF-8
				response.setHeader("Content-Disposition",
						"attachment; filename="+ new String(filePath.getBytes("UTF-8"),"ISO-8859-1"));
			}else{
				//其他的浏览器
				response.setHeader("Content-Disposition",
						"attachment; filename=\""+java.net.URLEncoder.encode(filePath, "UTF-8"));
			}
			//写入数据
			wwb.write();
			wwb.close();
				
//			long end = System.currentTimeMillis();   
//			System.out.println("----完成该操作共用的时间是:"+(end-start)/1000);  
			msg = "报表所在的位置为："+filePath;
		} catch (Exception e) {
			//Log.error("导出报表", e);
			msg = Globals.ERROR;
		}
		response.getWriter().write(msg);
	}
	
	/**
	 * 查看所有账户添加到excel表格中
	 * @param sheet
	 * @param
	 * @param wc
	 * @param sdf
	 * @return
	 * @throws Exception
	 */
	private  WritableSheet addCheckAllAccounts(WritableSheet sheet ,List<String> list,WritableCellFormat wc,SimpleDateFormat sdf) throws Exception{
		jxl.write.Label label ;
		if(list != null){
			CellView cellView = new CellView();  
		    cellView.setAutosize(true); //设置自动大小
			/*for(int i =0 ; i<list.size();i++){
				SchemeVo schemeVo = list.get(i);
				//sheet.setColumnView(i, cellView);//根据内容自动设置列宽  
				//Lable(x,y,z)代表单元格的第x+1列,y+1行，内容z
				//在Label对象的子对象中指明单元格的位置和内容
				//方案ID,用户ID,玩法,方案状态,是否上榜,推荐理由,方案金额,SP值,发单时间
				label = new jxl.write.Label(0,i+1,schemeVo.getSchemeId()+"",wc);
				sheet.addCell(label);
				label = new jxl.write.Label(1,i+1,schemeVo.getUserId()+"",wc);
				sheet.addCell(label);
				int dgType = schemeVo.getDgType();
				String dg = "";
				if(dgType == 1){
					dg="胜平负";
				}else if(dgType == 2){
					dg = "让球胜平负";
				}else{
					dg = "无";
				}
				label = new jxl.write.Label(2,i+1,dg,wc);
				sheet.addCell(label);
				int status = schemeVo.getStatus();
				String zt = "";
				if(status == 0){
					zt="销售中";
				}else if(status == 1){
					zt="走盘";
				}else if(status == 2){
					zt="命中";
				}else if(status == 3){
					zt ="未中";
				}
				label = new jxl.write.Label(3,i+1,zt+"",wc);
				sheet.addCell(label);
				int bdType = schemeVo.getBdType();
				String bd="";
				if(bdType == 1){
					bd="大师榜";
				}else if(bdType == 2){
					bd = "风云榜";
				}else{
					bd = "未上榜";
				}
				label = new jxl.write.Label(4,i+1,bd+"",wc);
				sheet.addCell(label);
				label = new jxl.write.Label(5,i+1,schemeVo.getReason()+"",wc);
				sheet.addCell(label);
				label = new jxl.write.Label(6,i+1,schemeVo.getCoin()+"",wc);				
				sheet.addCell(label);
				label = new jxl.write.Label(7,i+1,schemeVo.getSp()+"",wc);
				sheet.addCell(label);
				String  time =  DateUtils.dateToString(schemeVo.getAddTime(), 
						"yyyy-MM-dd HH:mm:ss");//sdf.format(schemeVo.getAddTime());
				label = new jxl.write.Label(8,i+1,time+"",wc);
				sheet.addCell(label);
				//单元格多个值 下拉选项
				label = new jxl.write.Label(9,i+1,"请选择",wc);
				List<String> angerlist = new ArrayList<String>(); 
				  angerlist.add("销售中"); 
				  angerlist.add("走盘");
				  angerlist.add("已中"); 
				  angerlist.add("未中");
				  WritableCellFeatures ws = new WritableCellFeatures();
				  ws.setDataValidationList(angerlist);
				  label.setCellFeatures(ws);
				  sheet.addCell(label);
				  
				
				//将定义好的单元格添加到工作表中
			}*/
		}
		return sheet ;
	}
}
