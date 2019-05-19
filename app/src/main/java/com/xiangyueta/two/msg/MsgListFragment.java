package com.xiangyueta.two.msg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.WatchMsgAdapter;
import com.xiangyueta.two.chat.ChatMsgActivity;
import com.xiangyueta.two.entity.MsgBean;
import com.xiangyueta.two.fragment.BaseFragment;

/*
* 私信列表
*
* */
public class MsgListFragment extends BaseFragment implements OnClickListener{
    ImageView image;
    ListView listView;
    List<MsgBean> listData;
    private View mView;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_msg, null);
        initView();
        return mView;
    }

    private View findViewById(int id){
        return mView.findViewById(id);
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.list_msg);
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

        listView.setAdapter(new WatchMsgAdapter(getActivity(),listData));
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent toChat = new Intent(getActivity(), ChatMsgActivity.class);
				startActivity(toChat);
                getActivity().overridePendingTransition(0, 0);
			}
		});
    }
	@Override
	public void onClick(View v) {

	}
}
