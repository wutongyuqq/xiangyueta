package com.xiangyueta.two.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.FirstFragmentAdapter;
import com.xiangyueta.two.adapter.SecondFragmentAdapter;
import com.xiangyueta.two.adapter.ThirdFragmentAdapter;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.entity.LiveThirdBean;
import com.xiangyueta.two.http.AsyncHttp;
import com.xiangyueta.two.util.JsonUtils;
import com.xiangyueta.two.util.MyParcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
public class ThirdFragment extends BaseFragment{
	private Context context;
	private ListView listView;
	private LinearLayout dots;
	int currItem = 0;
	int[] arr = { R.drawable.new1, R.drawable.new2, R.drawable.new3 };
	String[] str = { "多看多互动，热力涨不停！", "经验越多，等级越高！", "绿色直播，拒绝违规！" };
	final ArrayList<View> views = new ArrayList<View>();
	List<LiveThirdBean> list = new ArrayList<LiveThirdBean>();
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 103:
				List<LiveThirdBean> listData = (List<LiveThirdBean>) msg.obj;
				listView.setAdapter(new ThirdFragmentAdapter(context, listData));
				break;
			case 102:
				break;
			}
		}
	};

	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.third_fragment, null);
		dots = (LinearLayout) view.findViewById(R.id.dots);
		listView = (ListView) view.findViewById(R.id.list_gl);
		context = getActivity();
		getListData();
		return view;
	}

	private void getListData() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		MyParcel parm = new MyParcel();
		map.put("name", 0);
		parm.setMap(map);
		AsyncHttp ah = new AsyncHttp("/Mee/getZuixin.php", parm, 0) {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			public void onSuccess(Object reult) {
				int status = 0;
				List<LiveThirdBean> list = new ArrayList<LiveThirdBean>();
				Map<String, Object> mapData = new HashMap<String, Object>();
				if (reult != null) {
					String oldReult = reult.toString().replace("\\", "");
					mapData = JsonUtils.jsonToMap(oldReult);
					if (mapData != null) {
						status = Integer.parseInt(mapData.get("code") + "");
					}
				}

				switch (status) {
				case 0:
					Toast.makeText(getActivity(), "请求失败,请稍后重试", Toast.LENGTH_LONG).show();
					break;
				case 1:
					if (mapData.get("dataList") != null) {
						String jsonStr = mapData.get("dataList").toString()
								.replace("\\", "");
						list = JSON.parseArray(jsonStr, LiveThirdBean.class);
						Message message = new Message();
						message.what = 103;
						message.obj = list;
						myHandler.sendMessage(message);
					}
					break;
				}
			}

			@Override
			public void onFail(Object reult) {
				// 返回的网络数据为空；
				Toast.makeText(getActivity(), "未获取到网络数据", Toast.LENGTH_LONG).show();
			}
		};
		ah.execute(1000);
	}


	
	

	
}