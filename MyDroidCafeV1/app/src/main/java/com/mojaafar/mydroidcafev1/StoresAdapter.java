package com.mojaafar.mydroidcafev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {

    private ArrayList<recipe> mStores;
    private Context mContext;

    public StoresAdapter (ArrayList<recipe> stores, Context context) {
        this.mContext = context;
        this.mStores = stores;
    }

    @NonNull
    @Override
    public StoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.stores_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoresAdapter.ViewHolder holder, int position) {
        recipe currentStore = mStores.get(position);
        holder.bindTo(currentStore);

    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        private ImageView mStoreImage;
        private TextView mStoreLocation;
        private TextView mStoreDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mStoreImage = itemView.findViewById(R.id.store_image);
            mStoreLocation = itemView.findViewById(R.id.store_location);
            mStoreDescription = itemView.findViewById(R.id.store_description);
        }

        public void bindTo (recipe currentstore) {
            Glide.with(mContext)
                    .load(currentstore.getRecipeImage())
                    .into(mStoreImage);

            mStoreLocation.setText(currentstore.getRecipeTitle());
            mStoreDescription.setText(currentstore.getRecipeDescription());
        }
    }
}
