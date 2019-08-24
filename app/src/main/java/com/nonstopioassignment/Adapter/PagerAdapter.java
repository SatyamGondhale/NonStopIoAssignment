package com.nonstopioassignment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nonstopioassignment.AppController;
import com.nonstopioassignment.HomeActivity;
import com.nonstopioassignment.MainActivity;
import com.nonstopioassignment.R;
import com.nonstopioassignment.SubItemNextNameClick;
import com.nonstopioassignment.model.Subpaths;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class PagerAdapter  extends android.support.v4.view.PagerAdapter {
    Context context;
    ArrayList<Subpaths> subEntries;
    LayoutInflater inflater;
    SubItemNextNameClick nameClick;
   /* public void setOnItemClickListener(SubItemNextNameClick nameClick) {
        this.nameClick = nameClick;
    }*/
     PagerAdapter(ArrayList<Subpaths> subEntries, Context context, SubItemNextNameClick click){
        this.subEntries=subEntries;
        this.context=context;
        this.nameClick=click;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return subEntries.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==((CardView)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View v= inflater.inflate(R.layout.subpath_row_view,container,false);
        Subpaths sub=subEntries.get(position);
        Button currentName=v.findViewById(R.id.subPathName);
        Button nextName=v.findViewById(R.id.subPathNameNext);
        ImageView subImage=v.findViewById(R.id.subpathImage);
        currentName.setText(sub.getSubPathTitle());
        int i=position+1;
        nextName.setText(subEntries.get(i).getSubPathTitle());
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(sub.getSubPathImageUrl())
                .into(subImage);
        container.addView(v);
        nextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameClick.goToNext(position);
            }
        });
        return v;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((CardView)object);
    }
}
