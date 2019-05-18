package com.xiangyueta.two.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.FirstFragmentAdapter;
import com.xiangyueta.two.adapter.SecondFragmentAdapter;
import com.xiangyueta.two.entity.FocusBean;
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
public class SecondFragment extends BaseFragment implements
        View.OnClickListener {
    private  Context context;
    private ListView listView;
    List<FocusBean> list = new ArrayList<FocusBean>();
    Handler myHandler = new Handler() {  
        public void handleMessage(Message msg) {   
             switch (msg.what) {   
                  case 101:   
                	  List<FocusBean> listData = (List<FocusBean>) msg.obj;
                	  listView.setAdapter(new SecondFragmentAdapter(context,listData));
                       break;   
                  case 102:
                	  break;
             }   
        }   
   };  
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.second_fragment, null);
        listView = (ListView)view.findViewById(R.id.list2);
        
        context = getActivity();
  getListData();
       
        
        return view;
    }
    private void getListData(){
    	HashMap<String,Object> map = new HashMap<String, Object>();
		MyParcel parm =  new MyParcel();
		map.put("name", 0);
		parm.setMap(map);
		AsyncHttp ah = new AsyncHttp("/Mee/getRedian.php",parm,0) {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
			
			@Override
			public void onSuccess(Object reult) {
				int status=0;
				List<FocusBean> list = new ArrayList<FocusBean>();
				Map<String,Object> mapData = new HashMap<String,Object>();
				if(reult!=null){
					String oldReult = reult.toString().replace("\\", "");
					 mapData = JsonUtils.jsonToMap(oldReult);  
					 if(mapData!=null){
						 status = Integer.parseInt(mapData.get("code")+"");
					 }
				}
				
				switch (status) {
				case 0:
					Toast.makeText(getActivity(), "请求失败,请稍后重试", 0).show();
					break;
				case 1:
					if(mapData.get("dataList")!=null){
						String jsonStr = mapData.get("dataList").toString().replace("\\", "");
						list = JSON.parseArray(jsonStr,FocusBean.class);
					 	Message message = new Message();   
	                    message.what = 101;   
	                    message.obj= list;
	                    myHandler.sendMessage(message);
					}
					break;
				}
			}
			@Override
			public void onFail(Object reult) {
				//返回的网络数据为空；
				Toast.makeText(getActivity(), "未获取到网络数据", 0).show();
			}
		};
		ah.execute(1000);
	}
    @Override
    public void onClick(View v) {

    }
}