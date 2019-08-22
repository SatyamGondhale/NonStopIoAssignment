package com.nonstopioassignment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment {


    FragmentManager fm;
    FragmentTransaction ft;
    private Handler handler;
    private Runnable r;
    public Splash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.splash, container, false);
        fm=getFragmentManager();
        handler = new Handler();
        r=new Runnable() {
            @Override
            public void run() {
               {
                   AccessToken accessToken = AccessToken.getCurrentAccessToken();
                   if (accessToken != null) {
                       getUserFirstName(accessToken);
                   }else{
                       ft=fm.beginTransaction();
                       ft.replace(R.id.frame,new LoginScreen());
                       ft.commit();


                   }

                }
            }
        };
        return v;
    }
    private void getUserFirstName(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    getActivity().finish();
                    String name = object.getString("name");
                    Intent intent =new Intent(getActivity(),HomeActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();

    }
    @Override
    public void onResume() {
        super.onResume();
        moveForward();
    }

    private void moveForward() {
        handler.removeCallbacks(r);
        handler.postDelayed(r, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }

}
