package com.reivai.soal1.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.reivai.soal1.Api;
import com.reivai.soal1.R;
import com.reivai.soal1.Slider.SliderAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private List<HomeModel> homeModels;
    private SliderAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGE = 3;
    LinearLayout indicatorlay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        indicatorlay = view.findViewById(R.id.indicator);

        getData();

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGE) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },3000, 3000);

        return view;
    }

    private void getData() {
        Call<List<HomeModel>> call = Api.getInstance().getApi().getImage();
        call.enqueue(new Callback<List<HomeModel>>() {
            @Override
            public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                homeModels = response.body();
                adapter = new SliderAdapter(getActivity(), homeModels, viewPager);
                viewPager.setAdapter(adapter);

                setupIndicator();
                setupCurrentIndicator(0);
            }

            @Override
            public void onFailure(Call<List<HomeModel>> call, Throwable t) {

            }
        });
    }

    private void setupIndicator() {
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(4,0,4,0);

        for (int i = 0; i<indicator.length; i++) {
            indicator[i] = new ImageView(getContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            indicatorlay.addView(indicator[i]);
        }
    }

    private void setupCurrentIndicator(int index) {
        int itemcildcount=indicatorlay.getChildCount();
        for (int i=0; i<itemcildcount; i++){
            ImageView imageView=(ImageView)indicatorlay.getChildAt(i);
            if (i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.indicator_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.indicator_inactive));
            }
        }
    }
}