package com.xiangyueta.two.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//原有Fragment有状态丢失
//现有BaseFragment解决这个BUG
public abstract class BaseFragment extends Fragment{

	protected View view;

	// 回调函数:条件 当Fragment移除时
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (view != null) {
			// 将view从它的布局里面使用代码移除
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)// 已经被放到布局
			{
				parent.removeView(view);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (view == null) {
			view = createView(inflater, container, savedInstanceState);
		}
		return view;
	}

	public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
