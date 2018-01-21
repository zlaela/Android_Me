package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by Gormandizer on 1/17/2018.
 */

public class MasterListFragment extends Fragment {

    private MasterListAdapter mListAdapter;
    public MasterListFragment() {}

    // Define an interface that triggers a callback in the host activity
    OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    // Make sure the host activity implements the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + " Must implement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.master_list_grid);

        mListAdapter = new MasterListAdapter(getContext(), getImageIds());

        gridView.setAdapter(mListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // trigger the callback method and pass in the position clicked
                mCallback.onImageSelected(position);
            }
        });
        return view;
    }

    private List<Integer> getImageIds() {
        return AndroidImageAssets.getAll();
    }
}
