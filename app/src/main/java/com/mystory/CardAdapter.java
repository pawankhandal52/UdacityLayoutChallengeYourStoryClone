package com.mystory;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stemdot on 4/12/18,34
 */
public  class CardAdapter extends RecyclerView.Adapter<CardAdapter.StoryViewHolder>{
    //Number of card
    private static final int LENGTH = 18;
    private final Drawable[] images;
    private final String [] title;
    private final String [] desc;
    private final String [] date;
    private final String [] authors;
    
    public final  CardListClickListener cardListClickListener;
    
    interface CardListClickListener{
        void onCardClick(int clickedItemIndex);
    }
    
    public CardAdapter(Context context, CardListClickListener cardListClickListener){
        this.cardListClickListener = cardListClickListener;
        Resources  resources = context.getResources();
        title = resources.getStringArray(R.array.places);
        desc = resources.getStringArray(R.array.place_desc);
        date = resources.getStringArray(R.array.post_date);
        authors = resources.getStringArray(R.array.post_author);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        images = new Drawable[a.length()];
        for (int i = 0; i < images.length; i++) {
            images[i] = a.getDrawable(i);
        }
        a.recycle();
    }
    
    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoryViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }
    
    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        holder.storyImage.setImageDrawable(images[position % images.length]);
        holder.storyName.setText(title[position % title.length]);
        holder.storyDesc.setText(desc[position % desc.length]);
        holder.storyDate.setText(date[position % date.length]);
        holder.storyAuthor.setText(authors[position % authors.length]);
    }
    
    @Override
    public int getItemCount() {
        return LENGTH;
    }
    
    public  class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView storyImage;
        public TextView storyName;
        public TextView storyDesc;
        public TextView storyDate;
        public TextView storyAuthor;
        public StoryViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.item_card,parent,false));
            storyImage = itemView.findViewById(R.id.card_image);
            storyName = itemView.findViewById(R.id.card_title);
            storyDesc = itemView.findViewById(R.id.card_text);
            storyDate = itemView.findViewById(R.id.tv_card_date);
            storyAuthor = itemView.findViewById(R.id.tv_card_author);
            itemView.setOnClickListener(this);
        }
    
        @Override
        public void onClick(View v) {
            int postion  = getAdapterPosition();
    
            cardListClickListener.onCardClick(postion);
        }
    }
}
