package com.xiangyueta.two.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangyueta.two.R;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

public class ChatMsgAdapter  extends RecyclerView.Adapter{
	/**
	 * Created by Xinghai.Zhao on 18/11/19.
	 */
	/*
	 *作者:赵星海
	 *用途: 极光聊天页面Adapter
	 */
		private OnItemClickListener mOnItemClickListener = null;

		private Context MyContext;
		private List<Message> mList;

		public ChatMsgAdapter(Context context) {
			this.MyContext = context;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater from = LayoutInflater.from(MyContext);
			View view = from.inflate(R.layout.adapter_chat_msg, parent, false);
			return new ChatMsgHolder(view, MyContext,mOnItemClickListener);
		}

		@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			ChatMsgHolder holder1 = (ChatMsgHolder) holder;
			if (mList!=null||mList.size()>0){
				holder1.setHolderData(mList.get(position),position);
				//将position保存在itemView的Tag中，以便点击时进行获取   ----------------------
				holder.itemView.setTag(position);
			}

		}

		@Override
		public int getItemCount() {
			return mList == null ? 0 : mList.size();
		}


		public void setOnItemClickListener(OnItemClickListener listener) {//-------------
			this.mOnItemClickListener = listener;
		}


		public void setData(List<Message> data) {
			this.mList = data;
		}

		public void removeItem(int position) {
			mList.remove(position);
		}

		//define interface
		public interface OnItemClickListener {  //--------------------------
			void onItemClick(View view, int position);
		}

	}


