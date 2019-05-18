package com.xiangyueta.two.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.entity.FourthFarBean;
import com.xiangyueta.two.entity.LiveThirdBean;
import com.xiangyueta.two.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class FourthFragmentAdapter  extends BaseAdapter {
    private List<FourthFarBean> list;
    Context context;
    Handler handler;

    public FourthFragmentAdapter(Context context, List<FourthFarBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final FourthHodler hodler;
        if (convertView == null) {
            hodler = new FourthHodler();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.fourth_item, null);
            

        }else{
            hodler = (FourthHodler) convertView.getTag();
        }
        return convertView;
    }

}
class FourthHodler {
    ImageView img1;
    ImageView img2;
    ImageView img_rank1;
    ImageView img_rank2;
    TextView tv_play1;
    TextView tv_play2;
    TextView tv_tip1;
    TextView tv_tip2;
}

