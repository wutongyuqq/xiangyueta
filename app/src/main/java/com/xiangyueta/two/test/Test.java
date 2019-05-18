package com.xiangyueta.two.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Test {
	
	public static void main(String[] args) {
		Properties pro = new Properties();
		InputStream is = Test.class.getClass().getResourceAsStream("config.properties");
		try {
			pro.load(is);
			System.out.println(pro.get("ip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
