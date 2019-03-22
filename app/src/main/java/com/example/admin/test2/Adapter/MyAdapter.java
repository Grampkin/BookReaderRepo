package com.example.admin.test2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.test2.Interface.ILoadMore;
import com.example.admin.test2.MainActivity;
import com.example.admin.test2.Model.Item;
import com.example.admin.test2.R;
import com.example.admin.test2.ViewerMode;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}



class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView bookName, authorName;
    public Button removeButton, likeButton, gotoButton, listLike, listRemove;


    public ItemViewHolder(View itemView) {
        super(itemView);

        bookName = itemView.findViewById(R.id.bookName);
        authorName = itemView.findViewById(R.id.authorName);
        removeButton = itemView.findViewById(R.id.removeButton);
        likeButton = itemView.findViewById(R.id.likeButton);
        gotoButton = itemView.findViewById(R.id.invButton);
        listLike = itemView.findViewById(R.id.likeButtonList);
        listRemove = itemView.findViewById(R.id.removeButtonList);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<Item> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;
    int tabPosition;


    MainActivity mainActivity;







    public MyAdapter(RecyclerView recyclerView, Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;

        if(activity.getIntent().hasExtra("Selected tab")) {
            tabPosition = activity.getIntent().getIntExtra("Selected tab",0);
        }


//
//        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)) {
//                    if (loadMore != null)
//                        loadMore.onLoadMore();
//                    isLoading = true;
//                }
//
//            }
//        });
    }




    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }



    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_layout,parent,false);
            return new ItemViewHolder(view);
        }
        else if(viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_load,parent,false);
            return new LoadingViewHolder(view);
        }


        return null;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final boolean[] test = {false};


        if (holder instanceof ItemViewHolder) {
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.bookName.setText(items.get(position).getBookName());
            viewHolder.authorName.setText(items.get(position).getAuthorName());
            viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                                    items.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position,items.size());
                                }
            }
            );
            viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (test[0] ==false){
                        viewHolder.likeButton.setBackgroundResource(R.drawable.like_button);
                        test[0] =true;
                    }
                    else {
                        viewHolder.likeButton.setBackgroundResource(R.drawable.dislike_button);
                        test[0] =false;
                    }
                }
            });
            viewHolder.gotoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),viewHolder.bookName.getText(),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(v.getContext(), ViewerMode.class);
                    intent.putExtra("bookName", viewHolder.bookName.getText());
                    v.getContext().startActivity(intent);

                }
            });

        }
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();


    }

    public void setLoading() {
        isLoading = false;
    }


}
