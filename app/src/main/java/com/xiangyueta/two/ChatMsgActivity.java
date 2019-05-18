package com.xiangyueta.two;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.packet.Message.Type;

import com.xiangyueta.two.adapter.ChatMsgAdapter;
import com.xiangyueta.two.dao.LoginDao;
import com.xiangyueta.two.util.App;
import com.xiangyueta.two.util.ConnectionManager;
import com.xiangyueta.two.util.Util;

public class ChatMsgActivity extends Activity implements OnClickListener{
	ListView chat_list;
	EditText chat_content;
	private ChatMsgAdapter mChatAdapter;
	String toChatAccount = "lipeng@127.0.0.1";
	Button chat_sendbtn;
	private Chat chat;
	TextView to_chat_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.chat_msg);
        chat_list = (ListView) findViewById(R.id.chat_list);
		 chat_content = (EditText) findViewById(R.id.chat_content);
		 chat_sendbtn = (Button) findViewById(R.id.chat_sendbtn);
		 to_chat_name = (TextView) findViewById(R.id.to_chat_name);
		 chat_sendbtn.setOnClickListener(this);
		 Util.login(new LoginDao() {
				
				@Override
				public void onSuccuss() {
					new AsyncTask<Void, Void, Void>() {

						@Override
						protected Void doInBackground(Void... params) {
							// ①　在成功登录条件
							XMPPConnection conn = ConnectionManager.get(App.me);
							// ②　创建聊天管理对象(点击一个联系人默认是要聊)
							if(conn!=null){
								ChatManager cm = ChatManager.getInstanceFor(conn);

							cm.addChatListener(new ChatManagerListener() {
								@Override
								public void chatCreated(Chat chat, boolean createdLocally) {
									chat.addMessageListener(new MessageListener() {
										@Override
										public void processMessage(Chat chat, Message message) {
											// TODO Auto-generated method stub
											if(message==null || message.getBody()==null||"".equals(message.getBody())){
												return;
											}
											processMsg(message);
										}
									});
								}
							});
							// ③　创建出聊天对象
							// cm.createChat(账号, 消息监听器 不会拦截到消息);
							chat = cm.createChat(toChatAccount, null);

							// ④　发送消息给目标账号(发送)
							// ⑤　使用监听接收消息
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							mChatAdapter = new ChatMsgAdapter(ChatMsgActivity.this, toChatAccount);
							chat_list.setAdapter(mChatAdapter);
						};

					}.execute();
				}
				
				@Override
				public void onFail() {
				}
			});
	

	}

	private void processMsg(final Message message) {

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return null;
			}

			protected void onPostExecute(Void result) {
				mChatAdapter.add(message);
			};

		}.execute();
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.chat_sendbtn:
			
			send();
			break;
		}
	}
	

	public void send() {
		
		final String text = chat_content.getText().toString().trim();
		chat_content.setText("");

		new AsyncTask<Void, Void, Message>() {

			@Override
			protected Message doInBackground(Void... params) {
				// TODO Auto-generated method stub
				// chat.sendMessage(text);
				Message msg = null;
				try {
					msg = new Message();
					msg.setFrom(App.me);
					msg.setTo("lipeng");
					msg.setType(Type.chat);
					msg.setBody(text);
					chat.sendMessage(msg);
				} catch (NotConnectedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return msg;
			}

			protected void onPostExecute(Message result) {
				if (mChatAdapter == null) {
					mChatAdapter = new ChatMsgAdapter(ChatMsgActivity.this, toChatAccount);
					chat_list.setAdapter(mChatAdapter);
				}
				mChatAdapter.add(result);
			};

		}.execute();
	}
}

