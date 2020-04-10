package com.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class ConfigUtils {

	 Properties properties = new Properties(); 
	 InputStream inputStream  =   this.getClass().getClassLoader().getResourceAsStream("config.properties");
	public  String getProperties(String propertiesName){
		 try {
				properties.load(new InputStreamReader(inputStream, "UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
  		     System.out.println(properties.getProperty(propertiesName));
			 return properties.getProperty(propertiesName);
	}
}
