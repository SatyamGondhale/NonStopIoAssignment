package com.nonstopioassignment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nonstopioassignment.R;
import com.nonstopioassignment.model.Subpaths;

import java.util.ArrayList;

public class SubPathsAdapter extends RecyclerView.Adapter<SubPathsAdapter.ViewHolder> {
    ArrayList<Subpaths> subEntries;
    Context context;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void scrollToNext(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    public SubPathsAdapter(ArrayList<Subpaths> subEntries,Context context){
        this.subEntries=subEntries;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.subpath_row_view,parent,false);
        return new ViewHolder(v,mlistener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subpaths sub=subEntries.get(position);
        holder.currentName.setText(sub.getSubPathTitle());
        holder.nextName.setText(sub.getSubPathTitle());
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(sub.getSubPathImageUrl())
                .into(holder.subImage);

    }

    @Override
    public int getItemCount() {
        return subEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView subImage;
        Button currentName,nextName;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            subImage=itemView.findViewById(R.id.subpathImage);
            currentName=itemView.findViewById(R.id.subPathName);
            nextName=itemView.findViewById(R.id.subPathNameNext);
            currentName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        listener.scrollToNext(position);

                    }
                }
            });
            /*currentName.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 0;
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (currentName.getRight() - currentName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here

                            return true;
                        }
                    }
                    return false;
                }
            });*/
        }
    }
}
