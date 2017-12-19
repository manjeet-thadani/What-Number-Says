package com.genius.whatnumbersays.random_facts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.genius.whatnumbersays.R;
import com.genius.whatnumbersays.utils.OnItemClickListener;

import java.util.List;

/**
 * Created by ADMIN on 10/29/2016.
 */
public class RecyclerViewAdapterMenu extends RecyclerView.Adapter<RecyclerViewAdapterMenu.RecyclerViewHolder> {

    private List<MenuTiles> mTitles;
    private Context mContext;
    private View view;


    public RecyclerViewAdapterMenu(List<MenuTiles> titles, View v, Context context) {
        mTitles = titles;
        view = v;
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_row_activity_menu_menu_items, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.titleTextView.setText(mTitles.get(position).getTileName());
        holder.iconImageView.setImageResource(mTitles.get(position).getTileIcon());

        holder.setClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean isLongClick) {

                switch (mTitles.get(position).getTileName()) {

                    case RandomFactsMenuActivity.TILE_TRIVIA:
                        Intent i1 = new Intent(mContext, TriviaActivity.class);
                        mContext.startActivity(i1);
                        break;

                    case RandomFactsMenuActivity.TILE_MATH:
                        Intent i2 = new Intent(mContext, MathActivity.class);
                        mContext.startActivity(i2);

                        break;

                    case RandomFactsMenuActivity.TILE_DATE:Intent i3 = new Intent(mContext, DateActivity.class);
                        mContext.startActivity(i3);

                        break;

                    case RandomFactsMenuActivity.TILE_YEAR:Intent i4 = new Intent(mContext, YearActivity.class);
                        mContext.startActivity(i4);

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout containerLayout;
        TextView titleTextView;
        ImageView iconImageView;

        OnItemClickListener itemClickListener;

        public RecyclerViewHolder(final View view) {
            super(view);

            containerLayout = (LinearLayout) view.findViewById(R.id.new_row_activity_menu_menu_items_linearlayout);
            titleTextView = (TextView) view.findViewById(R.id.new_row_activity_menu_menu_items_title_textview);
            iconImageView = (ImageView) view.findViewById(R.id.new_row_activity_menu_menu_items_icon_imageview);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition(), false);
        }

        public void setClickListener(OnItemClickListener listener) {
            this.itemClickListener = listener;
        }


    }

}





