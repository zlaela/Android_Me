/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.BodyPartFragment;
import com.example.android.android_me.HeadPartFragment;
import com.example.android.android_me.LegsPartFragment;
import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    // Done (1) Create a layout file that displays one body part image named fragment_body_part.xml
        // This layout should contain a single ImageView

    // Done (2) Create a new class called BodyPartFragment to display an image of an Android-Me body
    // part
        // In this class, you'll need to implement an empty constructor and the onCreateView method
        // Done (3) Show the first image in the list of head images
            // Soon, you'll update this image display code to show any image you want
    public static final String HEAD_INDEX = "headIndex";
    public static final String BODY_INDEX = "bodyIndex";
    public static final String LEGS_INDEX = "legsIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        // Only create new fragments if there's no saved state
        if (savedInstanceState == null) {
            // DOne (5) Create a new BodyPartFragment instance and display it using the FragmentManager
            HeadPartFragment headPartFragment = new HeadPartFragment();
            headPartFragment.setImageIds(AndroidImageAssets.getHeads());
            headPartFragment.setListIndex(getIntent().getIntExtra(HEAD_INDEX, 0));

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyPartFragment.setListIndex(getIntent().getIntExtra(BODY_INDEX, 1))
            ;
            LegsPartFragment legsPartFragment = new LegsPartFragment();
            legsPartFragment.setImageIds(AndroidImageAssets.getLegs());
            legsPartFragment.setListIndex(getIntent().getIntExtra(LEGS_INDEX, 2));

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.head_container, headPartFragment)
                    .add(R.id.body_container, bodyPartFragment)
                    .add(R.id.legs_container, legsPartFragment)
                    .commit();
        }
    }
}
