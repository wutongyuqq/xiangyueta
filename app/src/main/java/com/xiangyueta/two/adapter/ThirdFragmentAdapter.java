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
import com.xiangyueta.two.entity.FocusBean;
import com.xiangyueta.two.entity.LiveThirdBean;
import com.xiangyueta.two.util.AsyncImageLoader;
import com.xiangyueta.two.util.Util;
import com.xiangyueta.two.util.AsyncImageLoader.ImageDownloadCallBack;
import com.xiangyueta.two.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class ThirdFragmentAdapter extends BaseAdapter {
	private AsyncImageLoader imageLoader2;
	private List<LiveThirdBean> list;
	Context context;
	Handler handler;
	int[] arrInt = { R.drawable.rank_0, R.drawable.rank_1, R.drawable.rank_2,
			R.drawable.rank_3, R.drawable.rank_4, R.drawable.rank_5,
			R.drawable.rank_6, R.drawable.rank_7, R.drawable.rank_8,
			R.drawable.rank_9, R.drawable.rank_10, R.drawable.rank_11,
			R.drawable.rank_12, R.drawable.rank_13, R.drawable.rank_14,
			R.drawable.rank_15, R.drawable.rank_16, R.drawable.rank_17 };

	public ThirdFragmentAdapter(Context context, List<LiveThirdBean> list) {
		this.context = context;
		this.list = list;
		imageLoader2 = new AsyncImageLoader(context);
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
		final MyHodler hodler;
		if (convertView == null) {
			hodler = new MyHodler();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.third_grid_item, null);
			hodler.img1 = (ImageView) convertView.findViewById(R.id.img1);
			hodler.img2 = (ImageView) convertView.findViewById(R.id.img2);
			hodler.img_rank1 = (ImageView) convertView
					.findViewById(R.id.img_rank1);
			hodler.img_rank2 = (ImageView) convertView
					.findViewById(R.id.img_rank2);

			hodler.tv_play1 = (TextView) convertView
					.findViewById(R.id.tv_play1);
			hodler.tv_play2 = (TextView) convertView
					.findViewById(R.id.tv_play2);
			hodler.tv_tip1 = (TextView) convertView.findViewById(R.id.tv_tip1);
			hodler.tv_tip2 = (TextView) convertView.findViewById(R.id.tv_tip2);
			convertView.setTag(hodler);
		} else {
			hodler = (MyHodler) convertView.getTag();
		}


			hodler.tv_play1.setText(list.get(position).getTypeHouse_a() == null ? "" : list.get(position)
					.getTypeHouse_a());
			hodler.tv_play2.setText(list.get(position).getTypeHouse_b() == null ? "" : list.get(position)
					.getTypeHouse_b());

			hodler.tv_tip1.setText(list.get(position).getNum_people_a() == null ? "" : list.get(position)
					.getNum_people_a());
			hodler.tv_tip2.setText(list.get(position).getNum_people_b() == null ? "" : list.get(position)
					.getNum_people_b());

			int aStar = Integer.parseInt(list.get(position).getStar_a() == null ? "0" : list.get(position)
					.getStar_a());
			int bStar = Integer.parseInt(list.get(position).getStar_b() == null ? "0" : list.get(position)
					.getStar_b());
			if (aStar < 17) {
				hodler.img_rank1.setImageResource(arrInt[aStar]);
			} else {
				hodler.img_rank1.setImageResource(arrInt[0]);
			}
			if (bStar < 17) {
				hodler.img_rank2.setImageResource(arrInt[bStar]);
			} else {
				hodler.img_rank2.setImageResource(arrInt[0]);
			}

			
			if (list.get(position).getPictrueUrl_a() != null
					&& !list.get(position).getPictrueUrl_a().equals("")) {
				Bitmap bitmap = imageLoader2.loadImage(hodler.img1,
						Util.getIp() + "/Mee"+list.get(position).getPictrueUrl_a(), new ImageDownloadCallBack() {

							@Override
							public void onImageDownloaded(ImageView imageView,
									Bitmap bitmap) {
								// 通过 tag 来防止图片错位
								if (imageView.getTag() != null
										&& imageView.getTag().equals(
												Util.getIp() + "/Mee"+list.get(position).getPictrueUrl_a())) {
									imageView.setImageBitmap(bitmap);
								}
							}
						});

				if (bitmap != null) {
					hodler.img1.setImageBitmap(bitmap);
				}
			}
			
			if (list.get(position).getPictrueUrl_b() != null
					&& !list.get(position).getPictrueUrl_b().equals("")) {
				Bitmap bitmap = imageLoader2.loadImage(hodler.img2,
						Util.getIp() + "/Mee"+list.get(position).getPictrueUrl_b(), new ImageDownloadCallBack() {

							@Override
							public void onImageDownloaded(ImageView imageView,
									Bitmap bitmap) {
								// 通过 tag 来防止图片错位
								if (imageView.getTag() != null
										&& imageView.getTag().equals(
												Util.getIp() + "/Mee"+list.get(position).getPictrueUrl_b())) {
									imageView.setImageBitmap(bitmap);
								}
							}
						});

				if (bitmap != null) {
					hodler.img2.setImageBitmap(bitmap);
				}
		}
		return convertView;
	}

}

class MyHodler {
	ImageView img1;
	ImageView img2;
	ImageView img_rank1;
	ImageView img_rank2;
	TextView tv_play1;
	TextView tv_play2;
	TextView tv_tip1;
	TextView tv_tip2;
}
