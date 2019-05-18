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
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.FirstFragmentAdapter;
import com.xiangyueta.two.adapter.FourthFragmentAdapter;
import com.xiangyueta.two.adapter.ThirdFragmentAdapter;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.entity.FourthFarBean;
import com.xiangyueta.two.entity.LiveThirdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
public class FourthFragment extends BaseFragment{
	private Context context;
	private ListView listView;
	private LinearLayout dots;
	int currItem = 0;
	private boolean flag = true;
	int[] arr = { R.drawable.new1, R.drawable.new2, R.drawable.new3 };
	String[] str = { "多看多互动，热力涨不停！", "经验越多，等级越高！", "绿色直播，拒绝违规！" };
	final ArrayList<View> views = new ArrayList<View>();
	List<FourthFarBean> list = new ArrayList<FourthFarBean>();

	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.third_fragment, null);
		dots = (LinearLayout) view.findViewById(R.id.dots);
		listView = (ListView) view.findViewById(R.id.list_gl);
		context = getActivity();
		showList();
		return view;
	}
	private void showList(){
		FourthFarBean bean1 = new FourthFarBean();
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		list.add(bean1);
		
		 listView.setAdapter(new FourthFragmentAdapter(context,list));
		
		
	}
}