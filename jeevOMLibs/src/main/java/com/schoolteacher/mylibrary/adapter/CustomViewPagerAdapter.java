package com.schoolteacher.mylibrary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolteacher.mylibrary.R;

/**
 * Created by chandan on 26/11/15.
 */
public class CustomViewPagerAdapter extends PagerAdapter {

    LayoutInflater mLayoutInflater;
    int[] mResources;
    int[] mIconRes;
    int[] mIntroStrRes;
    Context context;

    public CustomViewPagerAdapter(Context context, int[] mRes, int[] mIconRes, int[] mIntroStrRes) {
        this.context = context;
        this.mResources = mRes;
        this.mIconRes = mIconRes;
        this.mIntroStrRes = mIntroStrRes;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = mLayoutInflater.inflate(R.layout.slider_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.slider_imageview);
        imageView.setImageResource(mResources[position]);

        ImageView iconImgvw = (ImageView) itemView.findViewById(R.id.category_imgvw);
        iconImgvw.setImageResource(mIconRes[position]);

        TextView introStrTxtVw = (TextView) itemView.findViewById(R.id.intro_text);
        introStrTxtVw.setText(mIntroStrRes[position]);

        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }


}