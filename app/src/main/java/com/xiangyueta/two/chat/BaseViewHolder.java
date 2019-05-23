package com.xiangyueta.two.chat;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiangyueta.two.view.CircleImageView;

import java.util.List;

import cn.jpush.im.android.api.model.Message;
/**
 * Created by Xinghai.Zhao on 18/03/29.
 */
/*
 *作者:赵星海
 *时间:18/03/29 16:57
 *用途:ViewHolder上层类
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder{


	public BaseViewHolder(View itemView) {
		super(itemView);
		findView(itemView);
	}

	public abstract void findView(View view);
	public abstract void setHolderData(Object o,int position);


}


