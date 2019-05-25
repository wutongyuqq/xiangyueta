package com.xiangyueta.two.chat;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xiangyueta.two.R;
import com.xiangyueta.two.util.SharePreferenceManager;

import java.util.List;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatMsgHolder extends BaseViewHolder implements View.OnClickListener {


		private RoundedImageView MyImg;  //发送的图片
		private TextView MyTv_content, MyTV_Time, My_tc, My_tc1, My_Tv_state;
		private CircleImageView MyHead;
		private Context MyContext;
		private LinearLayout chat_msg_layout;

		private ChatMsgAdapter.OnItemClickListener mOnItemClickLis = null;
		private View view;

		public ChatMsgHolder(View itemView, Context con, ChatMsgAdapter.OnItemClickListener mOnItemClick) {
			super(itemView);
			MyContext = con;
			mOnItemClickLis = mOnItemClick;


		}

		@Override
		public void findView(View view) {
			this.view = view;
			MyImg = this.view.findViewById(R.id.item_jg_details_img);//图片
			MyHead = view.findViewById(R.id.item_jg_details_head);  //头像

			MyTv_content = view.findViewById(R.id.item_jg_details_content);//内容
			chat_msg_layout = view.findViewById(R.id.chat_msg_layout);//内容
			MyTV_Time = view.findViewById(R.id.item_jg_details_time); // 时间
			My_tc = view.findViewById(R.id.item_jg_details_tc);
			My_tc1 = view.findViewById(R.id.item_jg_details_tc1);
			My_Tv_state = view.findViewById(R.id.item_jg_details_state);

			MyImg.setOnClickListener(this);
			MyHead.setOnClickListener(this);
			MyTv_content.setOnClickListener(this);
			MyTV_Time.setOnClickListener(this);
			My_Tv_state.setOnClickListener(this);

		}

		@TargetApi(Build.VERSION_CODES.M)
		@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
		@Override
		public void setHolderData(Object o, int position) {
			if (o != null) {
				Message bean = (Message) o;
				if (bean.getFromUser() != null) {
					if (TextUtils.isEmpty(bean.getFromUser().getUserName())||bean.getFromUser().getUserName().equals(SharePreferenceManager.getCachedUsername())) {
						//是自己的聊天
						MyHead = view.findViewById(R.id.item_jg_details_head1);  //头像 右边
						MyHead.setVisibility(View.VISIBLE);//头像显示隐藏
						MyHead.setImageResource(R.drawable.default_head);
						view.findViewById(R.id.item_jg_details_head).setVisibility(View.GONE);
						//内容背景
						chat_msg_layout.setBackground(MyContext.getDrawable(R.drawable.chat_item_me_bg));
						MyTv_content.setTextColor(MyContext.getResources().getColor(R.color.heise));
						My_tc.setVisibility(View.VISIBLE);//权重挤压
						My_tc1.setVisibility(View.GONE);
						//对方是否未读
						My_Tv_state.setVisibility(View.GONE);
						if (bean.haveRead()) {
							My_Tv_state.setText("已读");
							My_Tv_state.setTextColor(MyContext.getResources().getColor(R.color.blue));
						}
						{
							My_Tv_state.setText("未读");
							My_Tv_state.setTextColor(MyContext.getResources().getColor(R.color.huise));

						}

					} else {
						My_Tv_state.setVisibility(View.GONE);//对方是否未读
						MyHead = view.findViewById(R.id.item_jg_details_head);  //头像
						MyHead.setVisibility(View.VISIBLE);//头像显示隐藏
						MyHead.setImageResource(R.drawable.default_head);
						view.findViewById(R.id.item_jg_details_head1).setVisibility(View.GONE);
						//内容背景
						chat_msg_layout.setBackground(MyContext.getDrawable(R.drawable.chat_item_bg));
						MyTv_content.setTextColor(MyContext.getResources().getColor(R.color.heise));
						My_tc.setVisibility(View.GONE);
						My_tc1.setVisibility(View.VISIBLE);

					}
					MyHead.setOnClickListener(this); //刷新头像点击事件
					//头像
					bean.getFromUser().getAvatarBitmap(new GetAvatarBitmapCallback() {
						@Override
						public void gotResult(int i, String s, Bitmap bitmap) {
							if (bitmap != null) {
								MyHead.setImageBitmap(bitmap);
							} else {
								Log.e("极光会话详情-用户头像赋值", "bitmap为空!");
							}

						}
					});

					switch (bean.getContentType()) {
						case text:
							chat_msg_layout.setVisibility(View.VISIBLE);
							MyTV_Time.setVisibility(View.GONE);
							MyImg.setVisibility(View.GONE);
							//内容
							TextContent textContent = (TextContent) bean.getContent();
							String text = textContent.getText();
							MyTv_content.setText(text);
							break;
						case image:
							chat_msg_layout.setVisibility(View.GONE);
							MyTV_Time.setVisibility(View.GONE);
							MyImg.setVisibility(View.VISIBLE);
							ImageContent imageContent = (ImageContent) bean.getContent();
							if (imageContent.getLocalThumbnailPath() != null) {
								Glide.with(MyContext).load(imageContent.getLocalThumbnailPath()).into(MyImg);
							}
							break;
						case prompt: //提示
							chat_msg_layout.setVisibility(View.GONE);
							MyTV_Time.setVisibility(View.VISIBLE);
							MyImg.setVisibility(View.GONE);
							//内容
							PromptContent promptContent = (PromptContent) bean.getContent();
							String promptText = promptContent.getPromptText();
							MyTV_Time.setText(promptText);
							break;

					}
				}
			}
		}

		@Override
		public void onClick(View v) {
			if (mOnItemClickLis != null) {
				mOnItemClickLis.onItemClick(v, getPosition());
			}
		}

	}


