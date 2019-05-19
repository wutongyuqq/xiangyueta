package com.xiangyueta.two.watch.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.util.AsyncImageLoader;
import com.xiangyueta.two.util.AsyncImageLoader.ImageDownloadCallBack;
import com.xiangyueta.two.util.Util;
import com.xiangyueta.two.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class WatchListAdapter  extends BaseAdapter {
    private  List<FocusBean> listData;
    Context context;
    Handler handler;
    private AsyncImageLoader imageLoader;
    public WatchListAdapter(Context context, List<FocusBean> listData) {
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
        final Hodler hodler;
        if (convertView == null) {
            hodler = new Hodler();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.fragment_watch_list_item, null);
            hodler.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            hodler.tv_num = (TextView) convertView
                    .findViewById(R.id.tv_num);
            hodler.tv_city = (TextView) convertView
                    .findViewById(R.id.tv_city);
            hodler.ci_head = (CircleImageView) convertView.findViewById(R.id.ci_head);
            hodler.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);

            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
        FocusBean bean = listData.get(position);
       
        hodler.tv_name.setText(bean.getGirlname()+"");
        hodler.tv_num.setText(bean.getNum()+"");
        hodler.tv_city.setText(bean.getCityName()+"");
       // hodler.iv_image.setTag(bean.getPictrueUrl());
        /*ImageAware imageAware = new ImageViewAware(hodler.iv_image, false);
        ImageLoader.getInstance().displayImage("http://192.168.0.110/Mee"+bean.getPictrueUrl(), imageAware);*/
        String photoUrl = bean.getPictrueUrl();
        bean.setPictrueUrl(Util.getIp()+"/Mee"+photoUrl);
    	final String tmpImageUrl = bean.getPictrueUrl();
        if (bean.getPictrueUrl() != null && !bean.getPictrueUrl().equals("")) {
			Bitmap bitmap = imageLoader.loadImage(hodler.iv_image, bean.getPictrueUrl(),
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
				hodler.iv_image.setImageBitmap(bitmap);
			}
		}
        if (bean.getPictrueUrl() != null && !bean.getPictrueUrl().equals("")) {
        	Bitmap bitmap = imageLoader.loadImage(hodler.ci_head, bean.getPictrueUrl(),
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
        		hodler.ci_head.setImageBitmap(bitmap);
        	}
        }
        
        return convertView;
    }

}

class Hodler {
    TextView tv_name;
    CircleImageView ci_head;
    TextView tv_city;
    TextView tv_num;
    ImageView iv_image;
}

