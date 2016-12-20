package com.test.first_maven.base.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Tools {
	private static Logger log = LogManager.getFormatterLogger(Tools.class);

    /**
     * 读取资源文件,并处理中文乱码
     * 
     * @param filename 文件全路径
     *            
     * @return 无
     * 
     * @author shenhua
     */
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
    
    /**
     * 等待
     * 
     * @param second 秒
     *            
     * @return 无
     * 
     * @author shenhua
     */
    public static void wait(int second) {
        try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    /**
     * 执行exe,上传文件
     * 
     * @param filePath 文件所在目录
     *            
     * @return true：成功；false：失败
     * 
     * @author shenhua
     */
    public static Boolean selectFile(String filePath) {
    	Process proc;
    	try {
    		proc = Runtime.getRuntime().exec("au3/selectFile.exe " + filePath);
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			if ((line = br.readLine()).equals("success")) {
				log.info("selectFile result: "+line);
				return true;
			} else {
				log.info("selectFile result: fail");
				return false;
			}
				  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    /**
     * 执行exe,上传文件（带重试）
     * 
     * @param filePath 文件所在目录
     *            
     * @return true：成功；false：失败
     * 
     * @author shenhua
     */
    public static Boolean selectFileWithRetry(String filePath, WebElement ele) {
    	Boolean flag = false;
    	for (int i=0;i<3;i++) {
    		ele.click();
    		flag = selectFile(filePath);
    		if (flag.equals(true)) {
    			break;
    		}
    	}  	
		return flag;
    }
    /**
     * 关闭chrome
     * 
     * @param 无
     *            
     * @return 无
     * 
     * @author shenhua
     */
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
    /**
     * 删除单个文件
     * 
     * @param sPath 被删除文件的路径+文件名
     *            
     * @return 单个文件删除成功返回true，否则返回false
     * 
     * @author shenhua     
     */
    public static boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
 
    /**
     * 删除目录（文件夹）以及目录下的文件
     * 
     * @param sPath 被删除目录的文件路径
     *         
     * @return 目录删除成功返回true，否则返回false
     * 
     * @author shenhua
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
        	log.info("File path " + sPath + " not exist!");
            return false;
        }
        Boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
        	log.info("delete " + sPath + " failed!");
        	return false;
        }            
        // 删除当前目录
        if (dirFile.delete()) {
        	log.info("delete " + sPath + " success!");
            return true;
        } else {
        	log.info("delete " + sPath + " failed!");
            return false;
        }
    }
}
