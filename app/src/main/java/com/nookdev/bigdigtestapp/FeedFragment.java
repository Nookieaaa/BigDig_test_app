package com.nookdev.bigdigtestapp;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nookdev.bigdigtestapp.adapter.FeedAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FeedFragment extends Fragment {
    public static final String TAG_NAME = "FEED_FRAGMENT";

    @Bind(R.id.feed) RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);

        rv.setAdapter(new FeedAdapter(getActivity()));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //parallax goes here
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Matrix imageMatrix;
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int first = llm.findFirstVisibleItemPosition();
                int last = llm.findLastVisibleItemPosition();

                for (int i = 0; i < (last - first); i++) {
                    ImageView imageView = (ImageView) recyclerView.getChildAt(i).
                            findViewById(R.id.feed_picture);
                    imageMatrix = imageView.getImageMatrix();
                    imageMatrix.postTranslate(0, dy > 0 ? (1.0f) : (-1.0f));
                    imageView.setImageMatrix(imageMatrix);
                    imageView.invalidate();
                }
            }
        });
        return view;
    }




}
