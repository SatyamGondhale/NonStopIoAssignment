package com.nonstopioassignment.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nonstopioassignment.R;
import com.nonstopioassignment.SubItemNextNameClick;
import com.nonstopioassignment.ZoomOutPageTransformer;
import com.nonstopioassignment.model.Paths;
import com.nonstopioassignment.model.Subpaths;

import java.nio.file.Path;
import java.util.ArrayList;


public class PathsAdapter extends RecyclerView.Adapter<PathsAdapter.ViewHolder> {
    private ArrayList<Paths> pathEntries;
    private Context context;


    public PathsAdapter(ArrayList<Paths> pathEntries,Context context){
        this.pathEntries=pathEntries;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Paths singlePath=pathEntries.get(position);
        ArrayList<Subpaths>  subpaths=pathEntries.get(position).getSubPaths();
        holder.pathName.setText(singlePath.getPathTitle());
        holder.pathCount.setText(String.valueOf(subpaths.size())+" paths");
        PagerAdapter adapter=new PagerAdapter(subpaths, context, new SubItemNextNameClick() {
            @Override
            public void goToNext(int position) {
                holder.subPathsList.setCurrentItem(position+1);
            }
        });
        holder.subPathsList.setAdapter(adapter);
        holder.subPathsList.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    @Override
    public int getItemCount() {
        return pathEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pathName,pathCount;
        ViewPager subPathsList;
        public ViewHolder(View itemView) {
            super(itemView);
            pathName=itemView.findViewById(R.id.pathName);
            subPathsList=itemView.findViewById(R.id.subPathsList);
            pathCount=itemView.findViewById(R.id.pathCountOne);
        }
    }
}
