package com.xiangyueta.two.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.MyApplication;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.util.AsyncImageLoader;
import com.xiangyueta.two.util.AsyncImageLoader.ImageDownloadCallBack;
import com.xiangyueta.two.util.Util;
import com.xiangyueta.two.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class SecondFragmentAdapter  extends BaseAdapter {
	private  List<FocusBean> listData;
	
    Context context;
    Handler handler;
    private AsyncImageLoader imageLoader;
    public SecondFragmentAdapter(Context context, List<FocusBean> listData) {
        this.context = context;
        this.listData = listData;
        imageLoader = new AsyncImageLoader(context);
    }

    @Override
    public int getCount() {
        if (listData.size() == 0) {
            return 0;
        }
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SecondHodler secondHodler;
        if (convertView == null) {
        	secondHodler = new SecondHodler();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.first_fragment_item, null);
            secondHodler.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            secondHodler.tv_num = (TextView) convertView
                    .findViewById(R.id.tv_num);
            secondHodler.tv_city = (TextView) convertView
                    .findViewById(R.id.tv_city);
            secondHodler.ci_head = (CircleImageView) convertView.findViewById(R.id.ci_head);
            secondHodler.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            secondHodler.iv_image.setTag(listData.get(position).getPictrueUrl());
            convertView.setTag(secondHodler);
        }else{
        	secondHodler = (SecondHodler) convertView.getTag();
        }
        FocusBean bean = listData.get(position);
       
        secondHodler.tv_name.setText(bean.getGirlname()+"");
        secondHodler.tv_num.setText(bean.getNum()+"");
        secondHodler.tv_city.setText(bean.getCityName()+"");
        secondHodler.iv_image.setTag(bean.getPictrueUrl());
        /*ImageAware imageAware = new ImageViewAware(hodler.iv_image, false);
        ImageLoader.getInstance().displayImage("http://192.168.0.110/Mee"+bean.getPictrueUrl(), imageAware);*/
        String photoUrl = bean.getPictrueUrl();
       
        bean.setPictrueUrl(Util.getIp()+"/Mee"+photoUrl);
    	final String tmpImageUrl = bean.getPictrueUrl();
        if (bean.getPictrueUrl() != null && !bean.getPictrueUrl().equals("")) {
			Bitmap bitmap = imageLoader.loadImage(secondHodler.iv_image, bean.getPictrueUrl(),
					new ImageDownloadCallBack() {

						@Override
						public void onImageDownloaded(ImageView imageView,
								Bitmap bitmap) {
							// 通过 tag 来防止图片错位
							if (imageView.getTag() != null
									&& imageView.getTag().equals(tmpImageUrl)) {
								imageView.setImageBitmap(bitmap);
							}
						}
					});

			if (bitmap != null) {
				secondHodler.iv_image.setImageBitmap(bitmap);
			}
		}
        if (bean.getPictrueUrl() != null && !bean.getPictrueUrl().equals("")) {
        	Bitmap bitmap = imageLoader.loadImage(secondHodler.ci_head, bean.getPictrueUrl(),
        			new ImageDownloadCallBack() {
        		
        		@Override
        		public void onImageDownloaded(ImageView imageView,
        				Bitmap bitmap) {
        			// 通过 tag 来防止图片错位
        			if (imageView.getTag() != null
        					&& imageView.getTag().equals(tmpImageUrl)) {
        				imageView.setImageBitmap(bitmap);
        			}
        		}
        	});
        	
        	if (bitmap != null) {
        		secondHodler.ci_head.setImageBitmap(bitmap);
        	}
        }
        
        return convertView;
    }

}
class SecondHodler {
    TextView tv_name;
    CircleImageView ci_head;
    TextView tv_city;
    TextView tv_num;
    ImageView iv_image;
}