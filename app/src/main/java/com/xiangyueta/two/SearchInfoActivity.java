package com.xiangyueta.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

/*
* 搜索页
* */
public class SearchInfoActivity extends Activity implements OnClickListener{
	ImageView iv_back;
	 private LinearLayout ll_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_info);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        ll_show = (LinearLayout) findViewById(R.id.ll_show);
        ll_show.setOnClickListener(this);
   
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_back:
			finish();
			overridePendingTransition(0, 0);
			break;
		 case R.id.ll_show:
         	ll_show.setVisibility(View.VISIBLE);
         	break;
		}
	}
}
