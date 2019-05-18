package com.xiangyueta.two.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.xiangyueta.two.R;

public class RegisterActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {

		
	}
}
