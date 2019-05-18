package com.xiangyueta.two.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import android.os.AsyncTask;


import com.xiangyueta.two.R;
import com.xiangyueta.two.MyApplication;
import com.xiangyueta.two.dao.LoginDao;
import com.xiangyueta.two.test.Test;

public class Util {
	public static String getUrl(){
		String url = "";
		Properties pro = new Properties();
		InputStream is = Test.class.getClass().getResourceAsStream("config.properties");
		try {
			pro.load(is);
			url = (String) pro.get("ip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public static String getIp(){
		return MyApplication.getContext().getString(R.string.internet_ip_debug);
	}
	
	
	public static void login(final LoginDao loginDao) {

		final String username = "123";
		final String password = "123456";
		new AsyncTask<Void, Integer, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					// ①　创建与服务器连接对象XmppTcpConnection
					// 169.254.186.140 5222
					ConnectionConfiguration config = new ConnectionConfiguration(App.IP, 5222, App.IP);
					// Debug
					config.setDebuggerEnabled(true);
					config.setSendPresence(true);
					config.setSecurityMode(SecurityMode.disabled);
					// 文本 未经加密 Authentication
					SASLAuthentication.supportSASLMechanism("PLAIN", 0);
					// 不加密
					XMPPConnection conn = new XMPPTCPConnection(config);
					// ②　调用登录login
					conn.connect();
					conn.login("123", "123456");
					// ③　【实时协作】通知其他账号
					Presence available = new Presence(Type.available);// 上线
					conn.sendPacket(available);
					// 管理
					App.me=username;
					ConnectionManager.add(App.me, conn);
					publishProgress(200);
				} catch (Exception e) {
					e.printStackTrace();
					// 连接移除
					ConnectionManager.remove(username);
					publishProgress(-1);
				}
				return null;
			}

			protected void onProgressUpdate(Integer[] values) {
				switch (values[0]) {
				case 200://
					loginDao.onSuccuss();
					break;
				case -1://
					loginDao.onFail();
					break;

				}

			};
		}.execute();

	}
}
