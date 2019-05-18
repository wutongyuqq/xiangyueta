package com.xiangyueta.two.util;

import java.util.HashMap;

import org.jivesoftware.smack.XMPPConnection;

//管理连接
public class ConnectionManager {

	private static HashMap<String, XMPPConnection> conns = new HashMap<String, XMPPConnection>();

	// 上线
	public static void add(String account, XMPPConnection conn) {
		conns.put(account, conn);
	}

	// 下线
	public static void remove(String account) {
		if (conns.containsKey(account)) {
			conns.remove(account);
		}
	}
	
	public static XMPPConnection get(String account)
	{
		return conns.get(account);
	}

}
