package com.example.android.slidepager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity{

  private ViewPager viewPager;
  private CirclePageIndicator circlePageIndicator;
  private CustomPagerAdapter pagerAdapter;


  private  String[] str_image_uls = new String[]{
      "http://www.iias-iisa.org/egpa/wp-content/uploads/Boston-skyline.jpg",
      "http://www.viajaratope.com/images/universal-orlando.jpg",
      "http://www.iias-iisa.org/egpa/wp-content/uploads/Boston-skyline.jpg",//boston
      "http://www.baylor.edu/content/imglib/2/4/2/3/242363.jpg", //el paso
      "http://vignette3.wikia.nocookie.net/olympians/images/0/09/El_Paso.jpg/revision/latest?cb=20150708222039" //android
  };

  private int[] animals_Array = new int[]{
      R.drawable.pic_cow,
      R.drawable.pic_fox,
      R.drawable.pic_leon,
      R.drawable.pic_siervo,
      R.drawable.pic_pink
  };

  private  String[] str_image_titles = new String[]{
      "New York City",
      "an Francisco",
      "iladelphia",
      "Boston",
      "El paso"
  };



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //pagerAdapter = new CustomPagerAdapter(this , str_image_titles , animals_Array);
    pagerAdapter = new CustomPagerAdapter(this, str_image_titles ,str_image_uls);
    viewPager = (ViewPager) findViewById(R.id.viewPager_images);
    viewPager.setAdapter(pagerAdapter);



    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("scroll", "scroll" + position );
      }

      @Override public void onPageSelected(int position) {

      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });


    circlePageIndicator = (CirclePageIndicator) findViewById(R.id.pageIndicator);
    circlePageIndicator.setViewPager(viewPager);

  }







}
