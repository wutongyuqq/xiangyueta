package com.xiangyueta.two.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.MyFragmentPagerAdapter;
import com.xiangyueta.two.fragment.FirstFragment;
import com.xiangyueta.two.fragment.FourthFragment;
import com.xiangyueta.two.fragment.SecondFragment;
import com.xiangyueta.two.fragment.ThirdFragment;
import com.xiangyueta.two.support.NavSupport;
import com.xiangyueta.two.support.TabSupport;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23 0023.
 * 首页
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener{


  private LinearLayout fragment_tab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

		new TabSupport(this);
		new NavSupport(this,1);
		fragment_tab = findViewById(R.id.fragment_tab);
		getSupportFragmentManager()    //
				.beginTransaction()
				.add(R.id.fragment_tab,new HomeFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
				.commit();
    }


    @Override
    public void onClick(View v) {
    }

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
