package com.test.first_maven.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tools {
	private static Logger log = LogManager.getFormatterLogger(Tools.class);
	// 读取资源文件,并处理中文乱码
    public static Properties readPropertiesFileObj(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            properties.load(bf);
            inputStream.close(); // 关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
	// 等待
    public static void wait(int second) {
        try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    //执行exe
    public static void selectFile(String fielPath) {
    	Process proc;
    	try {
    		proc = Runtime.getRuntime().exec("au3/selectFile.exe " + fielPath);
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			if ((line = br.readLine()).equals("success")) {
				log.info("selectFile result: "+line);
			} else {
				log.info("selectFile result: fail");
			}
				  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //杀浏览器进程
    public static void killChrome() {
    	try {
    		log.info("start kill chrome");
    		Runtime.getRuntime().exec("taskkill /im chromedriver.exe /F");
    		Runtime.getRuntime().exec("taskkill /im chrome.exe /F");
    		log.info("kill chrome over");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
