package com.example.admin.test2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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





class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView bookName, authorName, isFavorite;
    public Button gotoButton, listLike, listRemove;


    public MyViewHolder(View itemView) {
        super(itemView);

        bookName = itemView.findViewById(R.id.bookName);
        authorName = itemView.findViewById(R.id.authorName);

        gotoButton = itemView.findViewById(R.id.invButton);
        listLike = itemView.findViewById(R.id.likeButtonList);
        listRemove = itemView.findViewById(R.id.removeButtonList);

    }
}

public class MyAdapterList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
//    ILoadMore loadMore;
//    boolean isLoading;
    Activity activity;
    List<Item> items;
//    int visibleThreshold = 5;
//    int lastVisibleItem, totalItemCount;
//    int tabPosition;
//    Intent intent;


//    MainActivity mainActivity;







    public MyAdapterList(RecyclerView recyclerView, Activity activity, List<Item> items) {

        this.activity = activity;
        this.items = items;



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



//    public void setLoadMore(ILoadMore loadMore) {
//        this.loadMore = loadMore;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(activity).inflate(R.layout.list_item_layout,parent,false);
            return new MyViewHolder(view);
        }
        else if(viewType == 1) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_load,parent,false);
            return new LoadingViewHolder(view);
        }


        return null;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final boolean[] test = {false};


        if (holder instanceof MyViewHolder) {
            final MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.bookName.setText(items.get(position).getBookName());
            viewHolder.authorName.setText(items.get(position).getAuthorName());
            viewHolder.listRemove.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {
                                                               items.remove(position);
                                                               notifyItemRemoved(position);
                                                               notifyItemRangeChanged(position,items.size());
                                                           }
                                                       }
            );
            viewHolder.listLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (test[0] ==false){
                        viewHolder.listLike.setBackgroundResource(R.drawable.like_button);
                        test[0] =true;

                    }
                    else {
                        viewHolder.listLike.setBackgroundResource(R.drawable.dislike_button);
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

}
