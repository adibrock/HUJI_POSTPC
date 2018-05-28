package com.brock.adi.imagedownloader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
        }
    }

    interface OnImageClicked{
        void onClicked(String url);
    }

    private ArrayList<String> imageUrls = new ArrayList<>();

    private final OnImageClicked onImageClicked;

    public ImageAdapter(OnImageClicked onImageClicked) {
        this.onImageClicked = onImageClicked;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
            GlideApp.with(holder.imageView.getContext()).
                    load(imageUrls.get(position)).
                    centerCrop().
                    into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClicked.onClicked(imageUrls.get(holder.getAdapterPosition()));
                }
            });

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


}
