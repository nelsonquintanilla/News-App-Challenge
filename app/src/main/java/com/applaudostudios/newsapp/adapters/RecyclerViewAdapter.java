package com.applaudostudios.newsapp.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<News> mData;
    private CallBack mCallBack;

    // When this method is called in the CategoryFragment, the  notifyDataSetChanged() method
    // is causing the execution of all RecyclerViewAdapter methods again, so that the data is
    // passed in and bound to the holders.
    public void setData(List<News> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(CallBack callBack) {
        mData = new ArrayList<>();
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_blueprint, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mHeadline.setText(mData.get(position).getHeadline());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mHeadline;
        private ConstraintLayout mItemNew;

        private MyViewHolder(View itemView) {
            super(itemView);
            mHeadline = itemView.findViewById(R.id.headline);
            mItemNew = itemView.findViewById(R.id.news_item_id);
            mItemNew.setOnClickListener(this);
        }

        /**
         * Implements the onItemClick method of the Callback interface which body is defined in
         * the class that implements it (CategoryFragment)
         *
         * @param view the constraint layout that is root of the cardView that holds the headline
         */
        @Override
        public void onClick(View view) {
            mCallBack.onItemClick(getAdapterPosition());
        }
    }

    public interface CallBack {
        void onItemClick(int position);
    }

}
