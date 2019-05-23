package com.xiangyueta.two.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xiangyueta.two.msg.MsgListFragment;

import org.jivesoftware.smackx.xevent.packet.MessageEvent;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.NotificationClickEvent;

public class GlobalEventListener {
	/*
	 *作者:赵星海
	 *时间:18/11/21 16:08
	 *用途:极光IM消息接收处理
	 */

		private Context MainContext;

		private static MsgListFragment msgListFragment = null; // 会话列表对象
		private static ChatMsgActivity msgActivity = null;// 会话详情对象

		public GlobalEventListener(Context context) {
			MainContext = context;
			JMessageClient.registerEventReceiver(this);
		}
		public static void setJG(Activity activity, boolean islist) {
			msgActivity = (ChatMsgActivity) activity;
		}

	public static void setJG(MsgListFragment fragment) {
		msgListFragment =  fragment;
	}

		//通知点击 前往会话列表
		public void onEvent(NotificationClickEvent event) {
			MainContext.startActivity(new Intent(MainContext, ChatMsgActivity.class));
		}

		// 接收消息 (主线程)(刷新UI)
		public void onEventMainThread(MessageEvent event){
			if (msgActivity != null) {
				msgActivity.initData();
			} else if (msgListFragment != null) {
				msgListFragment.initData();
			}
		}
}

