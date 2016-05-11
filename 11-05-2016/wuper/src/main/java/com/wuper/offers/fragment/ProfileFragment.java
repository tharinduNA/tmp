package com.wuper.offers.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.wuper.offers.R;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class ProfileFragment extends Fragment {

    ImageView imageView;
    public static String userName=null;
    public static String userId=null;
    public static String userPicUrl=null;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = (ImageView) view.findViewById(R.id.profilePic);
        if(userId != null) {

//                userPicUrl = new URL("https://graph.facebook.com/" + userId + "/picture?type=normal");
            userPicUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";
            Log.v("as", " > " + userPicUrl);
            new DownloadImageTask(imageView).execute(userPicUrl);
        }
        TextView myName = (TextView) view.findViewById(R.id.myNameTextView);
        if(userName != null)
            myName.setText(userName);

//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(DeviceScreen.getScreenWidth(getContext()), DeviceScreen.getScreenHeigh(getContext()));
//        layoutParams.width = 300;
//        layoutParams.height = 300;
//        layoutParams.topMargin = 5;
//        layoutParams.gravity= Gravity.CENTER;
//        imageView.setLayoutParams(layoutParams);


        return view;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
