package com.reivai.soal1.Slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.reivai.soal1.R;
import com.reivai.soal1.ui.home.HomeModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private List<HomeModel> homeModels;
    private LayoutInflater inflater;
    private ViewPager2 viewPager;
    Context context;

    public SliderAdapter(Context context, List<HomeModel> homeModels, ViewPager2 viewPager) {
        this.inflater = LayoutInflater.from(context);
        this.homeModels = homeModels;
        this.viewPager = viewPager;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_slider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeModel homeModel = homeModels.get(position);
        Glide.with(context)
                .load(homeModel.getImg_url())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return homeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View item) {
            super(item);
            img = item.findViewById(R.id.img);
            relativeLayout = item.findViewById(R.id.container);
        }
    }
}
