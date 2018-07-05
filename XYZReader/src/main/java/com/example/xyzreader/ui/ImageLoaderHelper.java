package com.example.xyzreader.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class ImageLoaderHelper {
    private static ImageLoaderHelper sInstance;

    public static ImageLoaderHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ImageLoaderHelper(context.getApplicationContext());
        }

        return sInstance;
    }

    private final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(20);
    private ImageLoader mImageLoader;

    private ImageLoaderHelper(Context applicationContext) {
        RequestQueue queue = Volley.newRequestQueue(applicationContext);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        mImageLoader = new ImageLoader(queue, imageCache);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}

// TODO (1) what is the difference between constraint.Placeholder and FrameLayout?
// I didn't found article about that

// TODO (2) what is the purpose of using <merge tag>?
// read about that it is useful because it can get rid of unneeded ViewGroups, i.e. layouts that
// are simply used to wrap other views and serve no purpose themselves, but I still din't get the
// purpose of using it, will you give me an example with your answer

// TODO (3) what is the use of /values/macro_layout.xml?
// I didn't found article about that too

// TODO (4) Is it better to use this way to fix statusBar OverLay on its content (when using CollapsingToolbarLayout)
// https://stackoverflow.com/a/43299028/5873832
// or To use toolBar.setPadding() in appBarLayout.addOnOffsetChangedListener()? and if the first way
// is better please help me in understanding how to apply it to activity_article_list.xml
