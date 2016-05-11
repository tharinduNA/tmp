package com.wuper.offers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.wuper.offers.R;
import com.wuper.offers.fragment.ProfileFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Tharindu on 30-Apr-16.
 */
public class LoginActivity extends Activity {

    ImageButton fbLoginButton;
    AccessToken accessToken;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        fbLoginButton = (ImageButton) findViewById(R.id.fbLoginBtn);
        fbLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                facebookButtonClicked();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    public void facebookButtonClicked(){
        accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken == null){


            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("email", "public_profile"));
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResults) {

                            getUserDetails();

                        }
                        @Override
                        public void onCancel() {

                            Log.e("dd","facebook login canceled");

                        }

                        @Override
                        public void onError(FacebookException e) {



                            Log.e("dd", "facebook login failed error");

                        }
                    });


        }
        else{
            getUserDetails();
        }

    }

    public void getUserDetails(){

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        try {
                            Log.v("erw", "> " + object.getString("name"));

                            if (object.getString("name") != null) {
                                ProfileFragment.userName = object.getString("name");
                                ProfileFragment.userId = object.getString("id");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name, first_name, last_name, email");
        request.setParameters(parameters);
        request.executeAsync();





    }
}
