package com.xiangyueta.two.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.xiangyueta.two.R;
import com.xiangyueta.two.MyApplication;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.entity.MsgBean;
import com.xiangyueta.two.util.AsyncImageLoader;
import com.xiangyueta.two.util.Util;
import com.xiangyueta.two.util.AsyncImageLoader.ImageDownloadCallBack;
import com.xiangyueta.two.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class WatchMsgAdapter  extends BaseAdapter {
    private  List<MsgBean> listData;
    Context context;
    Handler handler;
    public WatchMsgAdapter(Context context, List<MsgBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        if (listData.size() == 0) {
            return 0;
        }
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MsgHodler hodler;
        if (convertView == null) {
            hodler = new MsgHodler();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.watch_msg_item, null);
            hodler.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            hodler.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            hodler.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            hodler.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
            convertView.setTag(hodler);
        }else{
            hodler = (MsgHodler) convertView.getTag();
        }
        hodler.tv_name.setText(listData.get(position).getName());
        hodler.tv_time.setText(listData.get(position).getTime());
        hodler.tv_des.setText(listData.get(position).getTipMsg());
        return convertView;
    }

}
class MsgHodler {
	ImageView iv_head;
	TextView tv_name;
	TextView tv_time;
	TextView tv_des;
	
}

