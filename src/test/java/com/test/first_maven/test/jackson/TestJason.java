package com.test.first_maven.test.jackson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class TestJason {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestJason test = new TestJason();
//		System.out.println(test.jsonTest2());
		test.jsonTest();
	}
	public void jsonTest() throws JSONException{  
	    String jsonString="{\"name\":\"小民\","
	    				  + "\"birthday\":\"1996年09月30日\","
	    				  + "\"mail\":\"xiaomin@sina.com\","
	    				  + "\"age\":28,"
	    				  + "\"detail\":{\"name\":\"大大\",\"age\":88},"
	    				  + "\"users\":[{\"loginname\":\"zhangfan\",\"password\":\"userpass\",\"email\":\"10371443@qq.com\"},{\"loginname\":\"zf\",\"password\":\"userpass\",\"email\":\"822393@qq.com\"}]}";   
	    JSONObject json= new JSONObject2(jsonString);
	    System.out.println(json.get("name"));
	    if (null != json.get("ddd")) {
	    	System.out.println(json.get("ddd"));
	    }
	    if (null != json.get("mail")) {
	    	System.out.println(json.get("mail"));
	    }	    
	    System.out.println(json.get("detail"));	    
	    System.out.println(((JSONObject) json.get("detail")).get("name"));
	    JSONArray jsonArray=json.getJSONArray("users"); 
	    for(int i=0;i<jsonArray.length();i++){  
	        JSONObject user=(JSONObject) jsonArray.get(i);  
	        System.out.println((String)user.get("loginname"));  
	        
	    }  
	}  
	
	public String jsonTest2() throws JSONException{  
	    String jsonString="{\"users\":[{\"loginname\":\"zhangfan\",\"password\":\"userpass\",\"email\":\"10371443@qq.com\"},{\"loginname\":\"zf\",\"password\":\"userpass\",\"email\":\"822393@qq.com\"}]}";  
	    JSONObject json= new JSONObject(jsonString);  
	    JSONArray jsonArray=json.getJSONArray("users");  
	    String loginNames="loginname list:";  
	    for(int i=0;i<jsonArray.length();i++){  
	        JSONObject user=(JSONObject) jsonArray.get(i);  
	        String userName=(String) user.get("loginname");  
	        if(i==jsonArray.length()-1){  
	            loginNames+=userName;  
	        }else{  
	            loginNames+=userName+",";  
	        }  
	    }  
	    return loginNames;  
	}  
}
