package com.xiangyueta.two.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.xiangyueta.two.ChooseCityActivity;
import com.xiangyueta.two.R;
import com.xiangyueta.two.SearchInfoActivity;

public class NavSupport  implements View.OnClickListener{

    private ImageView right_btn,left_btn;
    private Activity mActivity;

    public NavSupport(Activity activity, int formType){
        this.mActivity = activity;
        initView();
    }
    private void initView(){
        right_btn = (ImageView) findViewById(R.id.right_btn);
        left_btn = (ImageView) findViewById(R.id.left_btn);
        right_btn.setOnClickListener(this);
        left_btn.setOnClickListener(this);
    }




    public void setLeftBtnVisible(boolean isVisible){

    }

    private View findViewById(int id){
        return mActivity.findViewById(id);
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            /*
             * 搜索页面
             * */
            case R.id.right_btn:
                intent = new Intent(mActivity, SearchInfoActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;

            case R.id.left_btn:
                intent = new Intent(mActivity, ChooseCityActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;
            default:
                break;
        }

    }
}
