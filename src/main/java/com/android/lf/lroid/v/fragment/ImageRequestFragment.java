package com.android.lf.lroid.v.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.lf.lroid.R;
import com.android.lf.lroid.volley.RequestManager;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/5.
 */

public class ImageRequestFragment extends BaseFragment {

    public static final String IMAGE_URL_FLAG = "image_url_flag";

    public static ImageRequestFragment newInstance(String imageUrl) {
        Bundle args = new Bundle();
        args.putString(IMAGE_URL_FLAG,imageUrl);
        ImageRequestFragment fragment = new ImageRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.id_niv_fragment_image_request)
    NetworkImageView niv_image;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_request_layout;
    }

    @Override
    protected void initView(View view) {
        niv_image.setDefaultImageResId(R.drawable.drawable_image_request_default);
        niv_image.setErrorImageResId(R.drawable.drawable_image_request_default);
        niv_image.setImageUrl(getArguments().getString(IMAGE_URL_FLAG,""), RequestManager.getImageLoader());
        niv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    protected void setComponent() {
    }
}
