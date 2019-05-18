package com.xiangyueta.two;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NewsInfoActivity extends Activity implements OnClickListener{
	ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message_info);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_back:
			finish();
			overridePendingTransition(0, 0);
			break;
	default:
		break;
	}
		
	}
}
