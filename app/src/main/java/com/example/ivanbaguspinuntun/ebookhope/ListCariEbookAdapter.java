package com.example.ivanbaguspinuntun.ebookhope;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by ivanbaguspinuntun on 1/23/17.
 */

public class ListCariEbookAdapter extends RecyclerView.Adapter<ListCariEbookAdapter.ViewHolder> {

    private ArrayList <HashMap <String, String>> data_list ;
    Context context;

    public ListCariEbookAdapter(Context context, ArrayList <HashMap <String, String>> data_list) {
        Log.d("DEBUG_", "Container ListAdapter");
        this.data_list = data_list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        Log.d("DEBUG_", "View Holder");
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cari_ebook, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);
        Log.d("DEBUG_", "Bind ViewHolder");

        final HashMap<String, String> data = data_list.get(position);
        holder.judul.setText(data.get("judul"));
//        Picasso.with(context).load(data.get("gambar")).fit().into(holder.img);
        Picasso.with(context).load
                (data.get("gambar"))
                .placeholder(R.drawable.ic_alert)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView img;
        private TextView judul;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            img = (ImageView) itemView.findViewById(R.id.list_gbr);
            judul = (TextView) itemView.findViewById(R.id.list_judul);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }

    }
}

