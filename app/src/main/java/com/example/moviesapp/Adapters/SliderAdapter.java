package com.example.moviesapp.Adapters;

import android.content.Context;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.Domain.Slider;
import com.example.moviesapp.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<Slider> slideritems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapter(List<Slider> slideritems, ViewPager2 viewPager2) {
        this.slideritems = slideritems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slider_item_container, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(slideritems.get(position));
        if (position == slideritems.size()-2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slideritems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(Slider slider) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions= requestOptions.transform(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context).load(slider.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slideritems.addAll(slideritems);
            notifyDataSetChanged();
        }
    };
}
