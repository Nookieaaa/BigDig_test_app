package com.nookdev.bigdigtestapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nookdev.bigdigtestapp.R;
import com.nookdev.bigdigtestapp.app.MyApp;
import com.nookdev.bigdigtestapp.model.Item;
import com.nookdev.bigdigtestapp.model.Response;
import com.nookdev.bigdigtestapp.network.Api;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    public static final String BASE_URL = "http://ellotv.bigdig.com.ua/api/";
    IFragmentDialog dialogCallback;

    private List<Item> data = new ArrayList<>();

    public FeedAdapter(Context context){
        try {
            dialogCallback = (IFragmentDialog) context;
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        refresh();



    }

    public void refresh(){
        if (dialogCallback !=null){
            dialogCallback.showProgressDialog();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Response> call = api.getData();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
                setData(response.body()
                        .extractData());
                if (dialogCallback != null)
                    dialogCallback.dismissProgressDialog();
            }

            @Override
            public void onFailure(Throwable t) {
                if (dialogCallback != null) {
                    dialogCallback.showErrorDialog();
                }
            }
        });
    }




    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (dialogCallback !=null)
            dialogCallback = null;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        FeedViewHolder viewHolder = new FeedViewHolder(v);
        return viewHolder;
    }

    public void setData(List<Item> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    //viewholder
    public static class FeedViewHolder extends RecyclerView.ViewHolder{

        public static final String FONT_DIR = "fonts/";
        public static final String FONT_ROBOTO_BOLD = "Roboto-Bold.ttf";
        public static final String FONT_ROBOTO_LIGHT = "Roboto-Light.ttf";
        public static final String FONT_ROBOTO_REGULAR = "Roboto-Regular.ttf";

        @Bind(R.id.feed_picture) ImageView picture;
        @Bind(R.id.feed_title) TextView title;
        @Bind(R.id.feed_artist) TextView artist;
        @Bind(R.id.feed_views_count) TextView views_count;
        @Bind(R.id.rv_card) CardView card;

        public FeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            title.setTypeface(getTypeFace(FONT_ROBOTO_BOLD));
            artist.setTypeface(getTypeFace(FONT_ROBOTO_LIGHT));
            views_count.setTypeface(getTypeFace(FONT_ROBOTO_REGULAR));
        }

        //getting fonts
        private Typeface getTypeFace(String fontName){
            Typeface typeface = Typeface.createFromAsset(
                    MyApp.getContext().getAssets()
                    , FONT_DIR+fontName);

            return typeface;
        }

    }



    //populating data
    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position) {
        Resources res = MyApp.getContext().getResources();

        Item item = data.get(position);
        Picasso.with(MyApp.getContext())
                .load(Uri.parse(item.getPicture()))
                .resize(900, 600)
                .into(holder.picture);
        holder.artist.setText(
                res.getString(R.string.artist_title, item.getArtistString())
                );
        holder.title.setText(
                res.getString(R.string.title_title, item.getTitle())
                );
        holder.views_count.setText(
                res.getString(R.string.views_count_title,
                        String.valueOf(
                                item.getViewCount())
                )
                );
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface IFragmentDialog{
        public void showProgressDialog();
        public void dismissProgressDialog();
        public void showErrorDialog();
    }
}
