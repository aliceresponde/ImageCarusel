package com.example.android.slidepager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alice on 3/14/16.
 */
public class CustomPagerAdapter extends PagerAdapter {

  private Context context;
  private LayoutInflater mLayoutInflater;
  private String[] mResources_url;
  private String[] mTitles;
  private  int[] animals;

  /**
   *
   * @param context
   * @param mTitles - image Title
   * @param mResources  - imagesUrl
   */
  public CustomPagerAdapter( Context context, String[] mTitles, String[] mResources) {
    this.mTitles = mTitles;
    this.mResources_url = mResources;
    this.context = context;
    this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
  }

  public CustomPagerAdapter(Context context, String[] str_image_titles, int[] animals_array) {
    this.context=context;
    this.animals=animals_array;
    this.mTitles = str_image_titles;
    this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

  }

  /**
   *  This method should return the number of views available, i.e., number of pages o be displayed/created in the ViewPager.
   * @return
   */
  @Override public int getCount() {
    return mTitles.length;
  }

  /**
   * The object returned by instantiateItem() is a key/identifier. This method checks whether the
   * View passed to it (representing the page) is associated with that key or not. It is required by a
   * PagerAdapter to function properly. For our example, the implementation of this method is really simple,
   * we just compare the two instances and return the evaluated boolean.
   *
   * @param view
   * @param object
   * @return
   */
  @Override public boolean isViewFromObject(View view, Object object) {
    return view == ((RelativeLayout) object);
  }


  @Override
  public void destroyItem(View container, int position, Object object) {
    ((ViewPager) container).removeView((RelativeLayout) object);
  }

  /**
   * – This method should create the page for the given position passed to it as an argument.
   *   In our case, we inflate() our layout resource to create the hierarchy of view objects and
   *   then set resource for the ImageView in it. Finally, the inflated view is added to the
   *   container (which should be the ViewPager) and return it as well.
   *
    * @param container
   * @param position
   * @return
   */
  @Override public Object instantiateItem(ViewGroup container, int position) {

    View item_view =mLayoutInflater.inflate(R.layout.item_carousel, container, false);
    ImageView imageViewPhoto = (ImageView) item_view.findViewById(R.id.imageV_photo);
    //imageViewPhoto.setImageResource(animals[position]);

    Log.d("itle", mTitles[position] + "         posss:" +position);

    try {
      new ImageLoadTask(mResources_url[position], imageViewPhoto).execute();
    }catch (Exception e){
      Log.e("Error" , e.getMessage());
    }

    TextView tv_title = (TextView) item_view.findViewById(R.id.tv_title);
    tv_title.setText(mTitles[position]);

    container.addView(item_view);

    return item_view;
  }

  /**
   * Load ImageView from URL , downloading it by internet
   */
  private class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String url;
    private ImageView imageView;

    public ImageLoadTask(String url, ImageView imageView) {
      this.url = url; this.imageView = imageView;
    }


    @Override protected Bitmap doInBackground(Void... params) {
      try {
        URL urlConnection = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
      }catch (Exception e) { e.printStackTrace(); } return null; }

    @Override protected void onPostExecute(Bitmap result) {
      super.onPostExecute(result);
      imageView.setImageBitmap(result);
    }
  }

}
