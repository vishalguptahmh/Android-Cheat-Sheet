package com.baseandroidproject.Utilities.ImageUtlity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.baseandroidproject.Utilities.ImageUtils;
import com.baseandroidproject.common.BaseActivity;
import com.moode.R;
import com.moode.databinding.ActivityImageZoomBinding;

public class ImageZoomActivity extends AppCompatActivity  {

    static String TAG="ImageZoomActivity";
   static String IMG_URL="IMG_URL";
   static String IMG_PLACE_HOLDER="IMG_PLACE_HOLDER";
    ActivityImageZoomBinding binding;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_image_zoom);

        binding.backbutton.setOnClickListener(v -> {
            close();
        });

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        if(getIntent().getExtras().getString(IMG_URL)!=null){

            try {
                String url=getIntent().getExtras().getString(IMG_URL);
                int placeholder=Integer.valueOf(getIntent().getExtras().getString(IMG_PLACE_HOLDER));

                ImageUtils.loadImage(binding.mainImage,url,placeholder);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.8f, Math.min(mScaleFactor, 10.0f));
            binding.mainImage.setScaleX(mScaleFactor);
            binding.mainImage.setScaleY(mScaleFactor);
            Log.d(TAG, "onScale: "+mScaleFactor);
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
            Log.d(TAG, "onScaleEnd: "+detector.getScaleFactor());
            mScaleFactor=detector.getScaleFactor();
            binding.mainImage.setScaleX(mScaleFactor);
            binding.mainImage.setScaleY(mScaleFactor);

        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }
    }


    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    public void close() {

      setResult(Activity.RESULT_OK);
       finish();
    }

    public static void open(String imgUrl,int placeholder,FragmentActivity activity){
        Intent intent=new Intent(activity,ImageZoomActivity.class);
        intent.putExtra(IMG_URL,imgUrl);
        intent.putExtra(IMG_PLACE_HOLDER,String.valueOf(placeholder));
        activity.startActivityForResult(intent,0);
    }

}

