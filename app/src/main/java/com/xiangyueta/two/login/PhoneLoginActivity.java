package com.xiangyueta.two.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangyueta.two.R;
import com.xiangyueta.two.home.HomeActivity;
import com.xiangyueta.two.util.Constance;
import com.xiangyueta.two.util.SharePreferenceManager;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class PhoneLoginActivity extends Activity implements OnClickListener{

    private TextView login_btn;
    private EditText phone_num,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_login);
        login_btn = findViewById(R.id.login_btn);
        phone_num = findViewById(R.id.phone_num);
        pwd = findViewById(R.id.pwd);

        login_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                if(phone_num.getText()==null||TextUtils.isEmpty(phone_num.getText().toString())){
                    Toast.makeText(this,"手机号未填写",Toast.LENGTH_LONG).show();
                    return;
                }
                if(pwd.getText()==null||TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                String phoneNum = phone_num.getText().toString();
                String password = pwd.getText().toString();
                JMessageClient.login(phoneNum, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.e("JMessageClient.login",i+":"+s);
                    }
                });
                SharePreferenceManager.init(this,Constance.CATCH_USERINFO_FILE);
                SharePreferenceManager.setCachedUsername(phoneNum);
                SharePreferenceManager.setCachedPsw(password);
                startActivity(new Intent(this,HomeActivity.class));
                break;
            default:
                break;
        }
		
	}
}
