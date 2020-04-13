package com.demo.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.demo.common.Globals;
import com.demo.util.DateUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



@Controller
public class ImageUploadController{
	
	/**
	 * 个人头像图片上传
	 * @param
	 * @param request
	 * @return
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping("/imageUpload")
	public void fmUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String errorMsg = null;
		try {
			out = response.getWriter();
			String relationId = request.getParameter("relationId");
			String imageType = request.getParameter("imageType");
			String fileType = request.getParameter("fileType");//图片后缀
	
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			commonsMultipartResolver.setDefaultEncoding("utf-8");
			
			if(commonsMultipartResolver.isMultipart(request)){
				MultipartHttpServletRequest multipartrequest = commonsMultipartResolver
				.resolveMultipart(request);
				for(Iterator it = multipartrequest.getFileNames();it.hasNext();){
					String key = (String) it.next();
					MultipartFile file = multipartrequest.getFile(key);
					
					if(file.getSize()>0 && file.getSize()<=5*1024*1024 ||(fileType.equals("video")&&file.getSize() > 0&&file.getSize()<=100*1024*1024)){
						String fileName = file.getOriginalFilename().split("\\.")[file.getOriginalFilename().split("\\.").length-1];
						if(judgeFileFormat(fileName, fileType).equals("sucess")){
							File upload =  new File(Globals.IMAGE_URL);
							if(!upload.exists()){
								upload.mkdirs();
							}
							if("image".equals(fileType) || "doc".equals(fileType)){
								String realName = DateUtils.dateToString(new Date(),"yyyyMMddHHmmss") + "."+fileName;
								File uppath  = new File(Globals.IMAGE_URL+realName);
								FileCopyUtils.copy(file.getBytes(),uppath);
								errorMsg  = Globals.SUCCESS+","+Globals.IMAGE_HOST+"/"+realName;
							}
						}else {
							if(fileType.equals("image")){
								errorMsg = Globals.SYSTEM_FILE_FORMAT_ERROR+",只支持jpg,jpeg,png";
							}if(fileType.equals("excel")){
								errorMsg = Globals.SYSTEM_FILE_FORMAT_ERROR+",只支持xlsx,xls";
							}if(fileType.equals("video")){
								errorMsg = Globals.SYSTEM_FILE_FORMAT_ERROR+",只支持rmvb,mp4,wmv,mp3";
							}if(fileType.equals("doc")){
								errorMsg = Globals.SYSTEM_FILE_FORMAT_ERROR+",只支持doc,docx,pdf";
							}if(fileType.equals("txt")){
								errorMsg = Globals.SYSTEM_FILE_FORMAT_ERROR+",只支持txt";
							}
						}
					}else if(file.getSize()>5*1024*1024){
						errorMsg = "照片大小不能超过5MB";
						
					}else {
						errorMsg = Globals.SYSTEM_UPLOAD_FILE;
					}
				}
			}
		} catch (Exception e) {
			errorMsg = Globals.SYSTEM_BUSY;
			response.getWriter().write(errorMsg);
			//Log.error("图片上传");
		}finally{
			response.getWriter().write(errorMsg);
			if(out != null)
				out.close();
			
		}
	}
	
	
	//判断上传的文件类型
	public String judgeFileFormat(String fileName,String fileType){
		String code = "sucess";
		if(fileType.equals("image")){
			if(fileName.indexOf("jpg") >= 0 || fileName.indexOf("JPG") >= 0  || fileName.indexOf("jpeg") >= 0 || fileName.indexOf("JPEG") >= 0 || fileName.indexOf("png") >= 0 || fileName.indexOf("PNG") >= 0 || fileName.indexOf("gif") >= 0 || fileName.indexOf("GIF") >= 0){
				code = "sucess";
			}else{
				code = "error";
			}
		}
		if(fileType.equals("doc")){
			if(fileName.indexOf("doc") >= 0 || fileName.indexOf("docx") >= 0 || fileName.indexOf("pdf") >= 0 || fileName.indexOf("xls") >= 0 || fileName.indexOf("xlsx") >= 0){
				code = "sucess";
			}else{
				code = "error";
			}
		}		
		if(fileType.equals("excel")){
			if(fileName.indexOf("xls") >= 0 || fileName.indexOf("xlsx") >= 0){
				code = "sucess";
			}else{
				code = "error";
			}
		}	
		if(fileType.equals("video")){
			if(fileName.indexOf("wmv") >= 0 || fileName.indexOf("rmvb") >= 0|| fileName.indexOf("mp4") >= 0 ||fileName.indexOf("mp3")>=0){
				code = "sucess";
			}else{
				code = "error";
			}
		}
		if(fileType.equals("txt")){
			if(fileName.indexOf("txt")>=0){
				code = "sucess";
			}else{
				code = "error";
			}
		}
		return code;
	}
	//图片压缩
	public String compressPic(String oldFile,String newFile,int width, int height, boolean gp){
		String msg="no";
		  int newWidth; int newHeight;
		  File file = null; // 文件对象
		  FileOutputStream out;
		  file = new File(oldFile);
          if (!file.exists()) {
                  return "";
          }
      	try {
		  Image img = ImageIO.read(file);
		if (gp== true) {
            // 为等比缩放计算输出的图片宽度及高度
            double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
            double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
            // 根据缩放比率大的进行缩放控制
            double rate = rate1 > rate2 ? rate1 : rate2;
            newWidth = (int) (((double) img.getWidth(null)) / rate);
            newHeight = (int) (((double) img.getHeight(null)) / rate);
    } else {
            newWidth = img.getWidth(null); // 输出的图片宽度
            newHeight = img.getHeight(null); // 输出的图片高度
    }
    BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);

    /*
    * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
    * 优先级比速度高 生成的图片质量比较好 但速度慢
    */
    tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_REPLICATE ), 0, 0, null);
   
		out = new FileOutputStream(newFile);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	    encoder.encode(tag);
	    out.close();
	    msg="ok";
	} catch (IOException e) {
		e.printStackTrace();
	}
		return msg;
	}
	public  BufferedImage  getNewImage(MultipartFile oldImage,double width,double height) throws IOException{  
		 /*srcURl 原图地址；deskURL 缩略图地址；comBase 压缩基数；scale 压缩限制(宽/高)比例*/  
        ByteArrayInputStream bais = new ByteArrayInputStream(oldImage.getBytes());   
        MemoryCacheImageInputStream mciis = new MemoryCacheImageInputStream(bais);        
        Image src = ImageIO.read(mciis);  
        double srcHeight = src.getHeight(null);  
        double srcWidth = src.getWidth(null);  
        double deskHeight = 0;//缩略图高  
        double deskWidth  = 0;//缩略图宽  
        if (srcWidth>srcHeight) {  
              
            if (srcWidth>width) {  
                if (width/height>srcWidth/srcHeight) {  
                    deskHeight = height;  
                    deskWidth = srcWidth/(srcHeight/height);  
                }  
                else {  
                    deskHeight = width/(srcWidth/srcHeight);  
                    deskWidth = width;  
                }  
            }  
            else {  
                  
                if (srcHeight>height) {  
                    deskHeight = height;  
                    deskWidth = srcWidth/(srcHeight/height);  
                }else {  
                    deskHeight=srcHeight;  
                    deskWidth=srcWidth;  
                }                  
            }  
        }  
        else if(srcHeight>srcWidth)  
        {  
            if (srcHeight>(height)) {  
                if ((height)/width>srcHeight/srcWidth) {  
                    deskHeight = srcHeight/(srcWidth/width);  
                    deskWidth = width;  
                }else {  
                    deskHeight = height;  
                    deskWidth = (height)/(srcHeight/srcWidth);  
                }  
            }  
            else {  
                if (srcWidth>width) {  
                    deskHeight = srcHeight/(srcWidth/width);  
                    deskWidth = width;  
                }else {  
                    deskHeight=srcHeight;  
                    deskWidth=srcWidth;  
                }  
  
            }  
              
        }  
        else if (srcWidth==srcHeight) {  
              
            if (width>=(height)&&srcHeight>(height)) {  
                deskWidth=(height);  
                deskHeight=(height);  
            }  
            else if (width<=(height)&&srcWidth>width) {  
                deskWidth=width;  
                deskHeight=width;  
            }  
            else  if (width==(height)&&srcWidth<width) {  
                deskWidth=srcWidth;  
                deskHeight=srcHeight;  
            }  
            else {  
                deskHeight=srcHeight;  
                deskWidth=srcWidth;  
            }  
        }  
        BufferedImage tag = new BufferedImage((int)deskWidth,(int)deskHeight,  
            BufferedImage.TYPE_INT_RGB);  
        tag.getGraphics().drawImage(src.getScaledInstance((int)deskWidth, (int)deskHeight, Image.SCALE_AREA_AVERAGING), 0, 0, null); //绘制缩小后的图  
        return tag;  
	}

}
