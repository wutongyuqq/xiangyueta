package com.xiangyueta.two;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiangyueta.two.adapter.FirstFragmentAdapter;
import com.xiangyueta.two.adapter.WatchMsgAdapter;
import com.xiangyueta.two.entity.JSONBean;
import com.xiangyueta.two.entity.MsgBean;
import com.xiangyueta.two.entity.Resualt;
import com.xiangyueta.two.http.AsyncHttp;
import com.xiangyueta.two.util.MyParcel;

public class WatchMsgActivity extends Activity implements OnClickListener{
    Intent localIntent;
    ImageView image;
    DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类  
    ListView listView;
    List<MsgBean> listData;
    LinearLayout ll_bt_ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.watch_msg);
        ll_bt_ff = (LinearLayout) findViewById(R.id.ll_bt_ff);
        ll_bt_ff.setOnClickListener(this);
        listData = new ArrayList<MsgBean>();
        MsgBean bean1 = new MsgBean();
        bean1.setName("青儿");
        bean1.setTime("20:33");
        bean1.setTipMsg("我是一名营养师");
        MsgBean bean2 = new MsgBean();
        bean2.setName("荷叶");
        bean2.setTime("19:01");
        bean2.setTipMsg("你想找什么样的类型呢");
        MsgBean bean3 = new MsgBean();
        bean3.setName("婉儿");
        bean3.setTime("18:02");
        bean3.setTipMsg("你是一名帅哥吗");
        MsgBean bean4 = new MsgBean();
        bean4.setName("风雪");
        bean4.setTime("17:05");
        bean4.setTipMsg("帅哥，在吗？");
        MsgBean bean5 = new MsgBean();
        bean5.setName("青青河边草");
        bean5.setTime("12:06");
        bean5.setTipMsg("给你发消息怎么不回呢？");
        MsgBean bean6 = new MsgBean();
        bean6.setName("也会烧不尽");
        bean6.setTime("8:08");
        bean6.setTipMsg("能发一张你的照片吗?");
        MsgBean bean7 = new MsgBean();
        bean7.setName("还好有你");
        bean7.setTime("11:11");
        bean7.setTipMsg("今晚约吗?");
        listData.add(bean1);
        listData.add(bean2);
        listData.add(bean3);
        listData.add(bean4);
        listData.add(bean5);
        listData.add(bean6);
        listData.add(bean7);
        listData.add(bean1);
        listData.add(bean2);
        listData.add(bean3);
        listData.add(bean4);
        listData.add(bean5);
        listData.add(bean6);
        listData.add(bean7);
        listView = (ListView) findViewById(R.id.list_msg);
        listView.setAdapter(new WatchMsgAdapter(this,listData));
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent toChat = new Intent(WatchMsgActivity.this, ChatMsgActivity.class);
				startActivity(toChat);
	             overridePendingTransition(0, 0);
			}
		});
    }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ll_bt_ff:
			 Intent centerIntent = new Intent(this, MainNavActivity.class);
             startActivity(centerIntent);
             overridePendingTransition(0, 0);
			break;
		}
	}
}
