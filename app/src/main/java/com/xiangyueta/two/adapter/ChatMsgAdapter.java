package com.xiangyueta.two.adapter;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.Message;

import com.xiangyueta.two.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class ChatMsgAdapter extends ArrayAdapter<Message> {

	private List<Message> list = new ArrayList<Message>();

	public void add(Message msg) {
		list.add(msg);

		// 刷新列表
		notifyDataSetChanged();
	}
	private String toChatAccount="";


	public ChatMsgAdapter(Context context,String toChat) {
		super(context, 0);
		toChatAccount=toChat;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Message msg = list.get(position);
		if (msg.getBody()!=null && msg.getBody().startsWith("#")) {
			// 右视图 发送
			convertView = View.inflate(getContext(), R.layout.formclient_chat_out, null);
			// 用户
			TextView user = (TextView) convertView.findViewById(R.id.formclient_row_userid);
			TextView date = (TextView) convertView.findViewById(R.id.formclient_row_date);
			TextView msgtext = (TextView) convertView.findViewById(R.id.formclient_row_msg);
			user.setText(msg.getFrom());
			msgtext.setText(msg.getBody().toString());
		} else {
			// 左视图 接收
			convertView = View.inflate(getContext(), R.layout.formclient_chat_in, null);
			TextView user = (TextView) convertView.findViewById(R.id.formclient_row_userid);
			TextView date = (TextView) convertView.findViewById(R.id.formclient_row_date);
			TextView msgtext = (TextView) convertView.findViewById(R.id.formclient_row_msg);
			user.setText(msg.getFrom());
			msgtext.setText(msg.getBody());
		}
		return convertView;
	}
}
