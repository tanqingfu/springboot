package com.demo.test;
import org.apache.poi.ss.usermodel.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/** 
 * @author tqf
 * @version 创建时间：2020-4-7 下午4:10:18 
 * 类说明:读取excel文件内容
 */

public class ReadExcel {
    public static void main(String[] args) {
	readExcel("D:\\测试生成Excel文件\\withoutHead2.xls");
    //readExcel2();
    //getday(2020,1);
	//B17

 }
    public static void readExcel(String path) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	File file = new File(path);
	FileInputStream fis = null;
	Workbook workBook = null;
	if (file.exists()) {
	    try {
		fis = new FileInputStream(file);
		workBook = WorkbookFactory.create(fis);
		int numberOfSheets = workBook.getNumberOfSheets(); //获取有几个sheet
		List<read_excel>list = new ArrayList<>();
		// sheet工作表
		for (int s = 0; s <numberOfSheets ; s++) { //numberOfSheets
		    Sheet sheetAt = workBook.getSheetAt(s);
		    //获取工作表名称
		    String sheetName = sheetAt.getSheetName();
		    System.out.println("工作表名称：" + sheetName);
		    // 获取当前Sheet的总行数
		    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
		    System.out.println("当前表格的总行数:" + rowsOfSheet);
		    // 第一行
		    Row row0 = sheetAt.getRow(0);
		    int physicalNumberOfCells = sheetAt.getRow(0).getPhysicalNumberOfCells();
		    String[] title = new String[physicalNumberOfCells];
		    for (int i = 0; i < physicalNumberOfCells; i++) {
		        title[i] = row0.getCell(i).getStringCellValue();
		        System.out.print(title[i] + "  ");    
		    }
		    System.out.println();
		    for (int r = 1; r < rowsOfSheet; r++) {
		        Row row = sheetAt.getRow(r);//获取的第几行数据
		        int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
                //遍历每一列  
		        read_excel excel = new read_excel();
                for (int c = 0; c < cellCount; c++) {  
                    Cell cell = row.getCell(c);  
                    int cellType = cell.getCellType();  
                    String cellValue = null;  
                    switch(cellType) {  
                        case Cell.CELL_TYPE_STRING: //文本  
                            cellValue = cell.getStringCellValue();  
                            break;  
                        case Cell.CELL_TYPE_NUMERIC: //数字、日期  
                            if(DateUtil.isCellDateFormatted(cell)) {  
                                cellValue = sdf.format(cell.getDateCellValue()); //日期型  
                            }else {
                                cellValue = String.valueOf(cell.getNumericCellValue()); //数字  
                            }  
                            break;  
                        case Cell.CELL_TYPE_BOOLEAN: //布尔型  
                            cellValue = String.valueOf(cell.getBooleanCellValue());  
                            break;  
                        case Cell.CELL_TYPE_BLANK: //空白  
                            cellValue = cell.getStringCellValue();  
                            break;  
                        case Cell.CELL_TYPE_ERROR: //错误  
                            cellValue = "错误";  
                            break;  
                        case Cell.CELL_TYPE_FORMULA: //公式  
                            cellValue = "错误";  
                            break;  
                        default:  
                            cellValue = "错误";  
                    }  
                    if(cellValue == "" || "错误".equals(cellValue)){
                    	System.out.println("第" + r + "行,第"+c+"列[" + title[c] + "]数据错误！");
                    	return;
                    }
                    //下面是被获取到的数据存放在用户实体类 read_excel
                    switch (c) {
					case 0:
						excel.setId(cellValue);
						break;
					case 1:
						excel.setName(cellValue);
						break;
					case 2:
						excel.setAge(Integer.parseInt(cellValue));
						break;
					case 3:
						excel.setTime(cellValue);
						break;

					default:
						break;
					}
                    System.out.print(cellValue + "  ");    
                }  
                list.add(excel); //获取的数据存放在list 最后进行添加到数据库操作
                System.out.println();  
            }  
		    System.out.println();
		    }
	        
	        if (fis != null) {
		    fis.close();
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
        } else {
	    System.out.println("文件不存在!");
        }
    }
    
    /** 
     * 读取Excel测试，兼容 Excel 2003/2007/2010 
     */  
    public static void readExcel2()  
    {  
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
        try {  
            //同时支持Excel 2003、2007  
            File excelFile = new File("D:\\测试生成Excel文件\\withoutHead2.xls"); //创建文件对象  
            FileInputStream is = new FileInputStream(excelFile); //文件流  
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的  
            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量  
            //遍历每个Sheet  
            for (int s = 0; s < sheetCount; s++) {  
                Sheet sheet = workbook.getSheetAt(s);  
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
                //遍历每一行  
                for (int r = 0; r < rowCount; r++) {  
                    Row row = sheet.getRow(r);  
                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  
                    //遍历每一个单元格  
                    for (int c = 0; c < cellCount; c++) {  
                        Cell cell = row.getCell(c);  
                        int cellType = cell.getCellType();  
                        String cellValue = null;
                        
                        //在读取单元格内容前,设置所有单元格中内容都是字符串类型
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        
                        //按照字符串类型读取单元格内数据
                        cellValue = cell.getStringCellValue();
                        
                        /*在这里可以对每个单元格中的值进行二次操作转化*/
                        
                        System.out.print(cellValue + "    ");  
                    }  
                    System.out.println();  
                }  
            }  
      
        }  
        catch (Exception e) {  
            e.printStackTrace();  
        }  
      
         
    }
    
    public void stop(){
    	for (int i = 0;i<10;i++){
            for (int j = 0; j<10; j++)
            {
                System.out.println(j);
            	if (i<10){
                    System.out.println("retuen 跳出循环");
          			return;
                }
            }
        }
    }
    
    public static void getday(int year,int month){
    	Date timeDate = new Date(year, month, 0);
    	System.out.println(year+"年"+month+"月有:"+timeDate.getDate()+"天");
    }
}

 