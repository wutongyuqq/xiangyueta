package com.xiangyueta.two.watch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.FirstFragmentAdapter;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.watch.adapter.WatchListAdapter;

import java.util.ArrayList;
import java.util.List;

/*
* 客服页面
* */

public class WatchListActivity extends Activity implements OnClickListener{

    private ListView watch_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_watch_list);

        watch_list = findViewById(R.id.watch_list);
        List<FocusBean> listData =  new ArrayList<>();
        for(int i=0;i<30;i++){
            FocusBean bean = new FocusBean();
            bean.setGirlname("张若涵"+i);
            bean.setCityName("打羽毛球"+i);
            listData.add(bean);
        }
        watch_list.setAdapter(new WatchListAdapter(this,listData));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {

		
	}
}
