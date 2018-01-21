package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.BodyPartFragment;
import com.example.android.android_me.HeadPartFragment;
import com.example.android.android_me.LegsPartFragment;
import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment
        .OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legsIndex;

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.body_parts_layout) != null){
            isTwoPane = true;

            // Hide the next button
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            // 2-column gridview
            GridView gridView = (GridView) findViewById(R.id.master_list_grid);
            gridView.setNumColumns(2);

            // Populate the fragments into their containers if the view is tablet
            HeadPartFragment headPartFragment = new HeadPartFragment();
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            LegsPartFragment legsPartFragment = new LegsPartFragment();

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.head_container, headPartFragment)
                    .add(R.id.body_container, bodyPartFragment)
                    .add(R.id.legs_container, legsPartFragment)
                    .commit();
        }

        headIndex = 0;
        bodyIndex = 0;
        legsIndex = 0;
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "position clicked = " + position, Toast.LENGTH_SHORT).show();

        int headCounts = AndroidImageAssets.getHeads().size() - 1;
        int bodyCounts = AndroidImageAssets.getBodies().size() + headCounts;
        int legsCounts = AndroidImageAssets.getLegs().size() + bodyCounts;

        if (isTwoPane) {
            // 2-pane view
            BodyPartFragment fragment = new BodyPartFragment();

            if (position <= headCounts) {
                // This is a head item
                headIndex = position;

                // Get the correct image
                fragment.setImageIds(AndroidImageAssets.getHeads());
                fragment.setListIndex(headIndex);

                // Replace the old fragment with a new one
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.head_container, fragment)
                        .commit();
            } else if (position <= bodyCounts) {
                // this is a body item
                bodyIndex = position - headCounts - 1;

                // Get the correct image
                fragment.setImageIds(AndroidImageAssets.getBodies());
                fragment.setListIndex(bodyIndex);

                // Replace the old fragment with a new one
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body_container, fragment)
                        .commit();
            } else if (position <= legsCounts) {
                // This is a legs item
                legsIndex = position - bodyCounts - 1;

                // Get the correct image
                fragment.setImageIds(AndroidImageAssets.getLegs());
                fragment.setListIndex(legsIndex);

                // Replace the old fragment with a new one
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.legs_container, fragment)
                        .commit();
            }

        } else {
            if (position <= headCounts) {
                // This is a head item
                headIndex = position;
            } else if (position <= bodyCounts) {
                // this is a body item
                bodyIndex = position - headCounts - 1;
            } else if (position <= legsCounts) {
                // This is a legs item
                legsIndex = position - bodyCounts - 1;
            }
        }

        Bundle bundle = new Bundle();
        bundle.putInt("headIndex", headIndex);
        bundle.putInt("bodyIndex", bodyIndex);
        bundle.putInt("legsIndex", legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        // add a button to move launch the intent
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
