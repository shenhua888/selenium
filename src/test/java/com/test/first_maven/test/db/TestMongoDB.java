package com.test.first_maven.test.db;

import org.bson.Document;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMongoDB {
	MongoClient mongoClient;
	MongoDatabase db;
	MongoCursor cur;

	@BeforeTest
	public void beforeTest() {
		// 连接mongoDB
		mongoClient = new MongoClient("localhost", 27017);
		// 获取数据库
		db = mongoClient.getDatabase("test");
		
	}

	@Test
	public void pass1() {
		// 获取数据库中的集合
				MongoCollection users = db.getCollection("test1");
				// 查询结果
				FindIterable f = users.find();
				cur = f.iterator();
				// 取出结果
				while (cur.hasNext()) {
					Document doc = (Document) cur.next();
					System.out
							.println("---------------------------------------------------------------------------------");
					System.out.println(doc.toJson());
					System.out.println(doc.get("_id"));
					doc.clear();
				}
	}

	@AfterTest
	public void afterTest() {
		cur.close();
		mongoClient.close();
	}

	@AfterSuite
	public void afterSuite() {

	}
}
