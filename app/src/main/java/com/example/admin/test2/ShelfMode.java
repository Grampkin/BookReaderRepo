package com.example.admin.test2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.test2.Adapter.MyAdapter;
import com.example.admin.test2.Model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelfMode extends Fragment {
    private static final String TAG = "SHELF";

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shelf_mode_main_screen,container,false);

        RecyclerView recycler = view.findViewById(R.id.book_rv);
        RecyclerView recycler2 = view.findViewById(R.id.book_rv_2);
        RecyclerView recycler3 = view.findViewById(R.id.book_rv_3);



        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MyAdapter(recycler,getActivity(),items);
        recycler.setAdapter(adapter);

        recycler2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        adapter = new MyAdapter(recycler2,getActivity(),items);
        recycler2.setAdapter(adapter);

        recycler3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MyAdapter(recycler3,getActivity(),items);
        recycler3.setAdapter(adapter);



        final AssetManager mgr = getActivity().getAssets();
        getBooksList(mgr,"books");

        return view;
    }

    public void getBooksList(AssetManager assetManager, String path) {
        try {
            String list[] = assetManager.list(path);
            if (list != null)
                for (int i = 0; i < list.length; ++i) {
                    String bookName = list[i];
                    String shortBookName = bookName.substring(0,Math.min(bookName.length(),bookName.length()-4));
//                    shortBookName = shortBookName.substring(0,1).toUpperCase() + shortBookName.substring(1);
                    Item item = new Item(shortBookName,"Author of this pretty good book");
                    items.add(item);
                    Log.v("Assets", list[i]);
                    getBooksList(assetManager, path + "/" + list[i]);
                }
        } catch (IOException e) {
            Log.v("List error:", "can't list" + path);
        }


    }


}
