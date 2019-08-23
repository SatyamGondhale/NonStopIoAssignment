package com.nonstopioassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nonstopioassignment.Adapter.PathsAdapter;
import com.nonstopioassignment.model.Paths;
import com.nonstopioassignment.model.Subpaths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView name;
    ArrayList<Paths> pathEntries=new ArrayList<>();
    ArrayList<Subpaths> subPathEntres;
    PathsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name=findViewById(R.id.user_name);
        recyclerView=findViewById(R.id.recyclerviewlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false));
        String userName=getIntent().getStringExtra("name");
        name.setText(userName);
        setHomeFeed();

    }

    private void setHomeFeed() {
        String url= "https://5d55541936ad770014ccdf2d.mockapi.io/api/v1/paths";
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    try {
                        for (int i=0;i<response.length()-1;i++){
                            Paths p=new Paths();
                            JSONObject getPath=response.getJSONObject(i);
                            p.setPathTitle(getPath.getString("title"));
                            JSONArray sub=getPath.getJSONArray("sub_paths");
                            subPathEntres=new ArrayList<>();
                            for(int j=0;j<sub.length();j++){
                                Subpaths subp=new Subpaths();
                                JSONObject obj=sub.getJSONObject(j);
                                subp.setSubPathTitle(obj.getString("title"));
                                subp.setSubPathImageUrl(obj.getString("image"));
                                subp.setSubPathId(obj.getString("id"));
                                subPathEntres.add(subp);
                            }
                            p.setSubPaths(subPathEntres);
                            pathEntries.add(p);
                        }
                        adapter=new PathsAdapter(pathEntries,HomeActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


}
