package com.xiangyueta.two.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.home.HomeActivity;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class RegisterActivity extends Activity implements OnClickListener{
    private TextView register_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                registerJPush("10044","123456");
                startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                break;
            default:
                    break;
        }
		
	}

	private void registerJPush(String username,String password){
        JMessageClient.register(username, password, new BasicCallback() {
            @Override
            public void gotResult(int code, String desc) {
                if (code == 0) {
                    // 注册成功
                } else {
                    // 注册失败。status：错误码；desc：错误描述
                }
            }
        });
    }
}
