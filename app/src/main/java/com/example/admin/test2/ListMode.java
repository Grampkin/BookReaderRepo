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
import com.example.admin.test2.Adapter.MyAdapterList;
import com.example.admin.test2.Model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListMode extends Fragment {
    private static final String TAG = "LIST";

    List<Item> items = new ArrayList<>();
    MyAdapterList adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_mode_main_screen,container,false);

        RecyclerView recycler = view.findViewById(R.id.list_book_rv);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new MyAdapterList(recycler,getActivity(),items);
        recycler.setAdapter(adapter);

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
