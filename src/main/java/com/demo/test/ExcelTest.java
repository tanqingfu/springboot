package com.demo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.*;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/** 
 * @author tqf
 * @version 创建时间：2019-5-21 上午9:59:32 
 * 类说明:  阿里开源（EasyExcel）---导出EXCEL文件
 */
public class ExcelTest {
	
	public static void main(String[] args) throws IOException {
		writeExcelOneSheetOnceWrite();
		//writeExcelMoreSheetMoreWrite();
	}
	
    /**
     * 针对较少的记录数(20W以内大概)可以调用该方法一次性查出然后写入到EXCEL的一个SHEET中
     * 注意： 一次性查询出来的记录数量不宜过大，不会内存溢出即可。
     *
     * @throws IOException
     */
    public static void writeExcelOneSheetOnceWrite() throws IOException {
    	long a= System.currentTimeMillis();//获取当前系统时间(毫秒)
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 生成EXCEL并指定输出路径
    	//在本地新建一个文件夹 用于保存文件
    	File file1=new File("D:\\测试生成Excel文件");  
        if(!file1.exists()){//如果文件夹不存在  
            file1.mkdir();//创建文件夹  
        }
        OutputStream out = new FileOutputStream(""+file1+"\\withoutHead220.xls");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS);
        
        
        
        for (int j = 0; j < 2; j++) {
        	 // 设置SHEET
            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("测试sheet"+j);
            Table table;
            // 设置标题
            if(j == 0){
            	 table = new Table(1);
                 List<List<String>> titles = new ArrayList<List<String>>();
                 titles.add(Arrays.asList("用户ID"));
                 titles.add(Arrays.asList("名称"));
                 titles.add(Arrays.asList("年龄"));
                 titles.add(Arrays.asList("生日"));
                 table.setHead(titles);
            }else{
            	 table = new Table(2);
                 List<List<String>> titles = new ArrayList<List<String>>();
                 titles.add(Arrays.asList("用户ID1"));
                 titles.add(Arrays.asList("名称1"));
                 titles.add(Arrays.asList("年龄1"));
                 titles.add(Arrays.asList("生日1"));
                 titles.add(Arrays.asList("多个字段"));
                 table.setHead(titles);
            }
     
            // 查询数据导出即可 比如说一次性总共查询出1000条数据
            List<List<String>> userList = new ArrayList<List<String>>();
            for (int i = 0; i < 1000; i++) {
                userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), format.format(new Date())));
            }
            writer.write0(userList, sheet, table);
		}
        writer.finish();
        out.close();
        System.out.println(System.currentTimeMillis()-a+"毫秒");
    }
    
    /**
     * 针对几百万的记录数可以调用该方法分多批次查出然后写入到EXCEL的多个SHEET中
     * 注意：
     * perSheetRowCount % pageSize要能整除  为了简洁，非整除这块不做处理
     * 每次查询出来的记录数量不宜过大，根据内存大小设置合理的每次查询记录数，不会内存溢出即可。
     *
     * @throws IOException
     */
    public static void writeExcelMoreSheetMoreWrite() throws IOException {
 
        // 生成EXCEL并指定输出路径
        OutputStream out = new FileOutputStream("F:\\temp\\withoutHead3.xls");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS);
        
        File file = new File("F:\\测试生成Excel文件");
        if(!file.exists()){
        	file.mkdir();
        }
        // 设置SHEET名称
        String sheetName = "测试SHEET";
 
        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);
 
        // 模拟分批查询：总记录数250条，每个SHEET存100条，每次查询20条  则生成3个SHEET，前俩个SHEET查询次数为5， 最后一个SHEET查询次数为3 最后一次写的记录数是10
        // 注：该版本为了较少数据判断的复杂度，暂时perSheetRowCount要能够整除pageSize， 不去做过多处理  合理分配查询数据量大小不会内存溢出即可。
        Integer totalRowCount = 250;
        Integer perSheetRowCount = 100;
        Integer pageSize = 20;
        Integer sheetCount = totalRowCount % perSheetRowCount == 0 ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);
        Integer previousSheetWriteCount = perSheetRowCount / pageSize;
        Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0 ?
                previousSheetWriteCount :
                (totalRowCount % perSheetRowCount % pageSize == 0 ? totalRowCount % perSheetRowCount / pageSize : (totalRowCount % perSheetRowCount / pageSize + 1));
 
        for (int i = 0; i < sheetCount; i++) {
 
            // 创建SHEET
            Sheet sheet = new Sheet(i, 0);
            sheet.setSheetName(sheetName + i);
 
            if (i < sheetCount - 1) {
 
                // 前2个SHEET, 每个SHEET查5次 每次查20条 每个SHEET写满100行  2个SHEET合计200行  实用环境：参数： currentPage: j+1 + previousSheetWriteCount*i, pageSize: pageSize
                for (int j = 0; j < previousSheetWriteCount; j++) {
                    List<List<String>> userList = new ArrayList<List<String>>();
                    for (int k = 0; k < 20; k++) {
                        userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));
                    }
                    writer.write0(userList, sheet, table);
                }
 
            } else if (i == sheetCount - 1) {
 
                // 最后一个SHEET 实用环境不需要将最后一次分开，合成一个即可， 参数为： currentPage = i+1;  pageSize = pageSize
                for (int j = 0; j < lastSheetWriteCount; j++) {
 
                    // 前俩次查询 每次查询20条
                    if (j < lastSheetWriteCount - 1) {
 
                        List<List<String>> userList = new ArrayList<List<String>>();
                        for (int k = 0; k < 20; k++) {
                            userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()), new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);
 
                    } else if (j == lastSheetWriteCount - 1) {
 
                        // 最后一次查询 将剩余的10条查询出来
                        List<List<String>> userList = new ArrayList<List<String>>();
                        Integer lastWriteRowCount = totalRowCount - (sheetCount - 1) * perSheetRowCount - (lastSheetWriteCount - 1) * pageSize;
                        for (int k = 0; k < lastWriteRowCount; k++) {
                            userList.add(Arrays.asList("ID_" + Math.random(), "小明1", String.valueOf(Math.random()), new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);
 
                    }
                }
            }
        }
 
        writer.finish();
    }

}
 