package com.bob.flicklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bob.flicklist.Model.Photo;

public class MainActivity extends AppCompatActivity implements FlickListFragment.OnListFragmentInteractionListener {

    private static final String TAG1 = "list";
    FlickListFragment listFlicksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Avoid unnecessary recreation of fragments on orientation change
        if (savedInstanceState != null) {
            listFlicksFragment = (FlickListFragment) getSupportFragmentManager().findFragmentByTag(TAG1);
            return;
        } else {

            //List Fragment attached to activity
            listFlicksFragment = FlickListFragment.newInstance();
            if (findViewById(R.id.main_frame) != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.main_frame, listFlicksFragment, TAG1).commit();
            }

          /*  //Details Fragment attached in case of multi-pane
            if (findViewById(R.id.fragment_container_2) != null) {
                commitDetailsFragment = new CommitDetailsFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_2, commitDetailsFragment, TAG2).addToBackStack(TAG2).commit();
            }*/
        }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*//*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    }

    @Override
    public void onFlickClicked(Photo photo) {

    }
}
