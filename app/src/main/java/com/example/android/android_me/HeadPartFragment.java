package com.example.android.android_me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gormandizer on 1/17/2018.
 */

public class HeadPartFragment extends Fragment {
    // Logging tag
    private static final String TAG = "HeadPartFragment";

    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    private List<Integer> mImageIds;
    private int mListIndex;
    ImageView headImage;

    public HeadPartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Load the saved state, if it exists
        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        headImage = (ImageView) view.findViewById(R.id.body_image);

        if (mImageIds != null) {
            // set the image resource to the list item at the stored index
            headImage.setImageResource(mImageIds.get(mListIndex));
            headImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListIndex < mImageIds.size() - 1) {
                        mListIndex ++;
                    } else {
                        mListIndex = 0;
                    }
                    headImage.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.d(TAG, "This fragment has null image IDs");
        }

        return view;
    }

    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setListIndex(int index) {
        mListIndex = index;
    }

    /**
     * Save the current fragment state
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX, mListIndex);
    }
}