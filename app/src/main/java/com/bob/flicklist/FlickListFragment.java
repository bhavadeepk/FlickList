package com.bob.flicklist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.flicklist.Model.Photo;
import com.bob.flicklist.Model.FlicksResponse;
import com.bob.flicklist.Network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FlickListFragment extends Fragment implements FlickListAdapter.ListItemClickListener {

    private static final int REQUEST_CODE = 1;
    private OnListFragmentInteractionListener mListener;
    private Context context;
    private View rootView;
    private ListView listView;
    private FlickListAdapter adapter;
    private ArrayList<Photo> photoList;
    private TextView titleTextView;
    private ProgressBar progressBar;
    private boolean isListShowing;

    public FlickListFragment() {
        super();
    }

    public static FlickListFragment newInstance(){
        return new FlickListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
            this.context = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_list_flicks, container, false);
            listView = rootView.findViewById(R.id.listView);
            photoList = new ArrayList<>();
            adapter = new FlickListAdapter(context, R.layout.item_flick_list, photoList, this);
            listView.setAdapter(adapter);
            progressBar = rootView.findViewById(R.id.progressBar);
            titleTextView = rootView.findViewById(R.id.title_text_view);
//Check if internet permission is granted

            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET) == PERMISSION_GRANTED) {
                //If internet is available get cloud data else look for local data
                if(isInternetAvailable()){
                    retrieveFlicksFromCloud();
                }
                else {
                    internetException();
                }

            }
            else{ // If device is marshmellow or above dynamically ask for internet permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, FlickListFragment.REQUEST_CODE);
                }
                else{
                    Toast.makeText(getActivity(), "Please grant internet permission", Toast.LENGTH_SHORT).show();
                    //Intent to navigate user to the application settings page to enable internet permission
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        }

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void internetException() {
        Toast.makeText(context, "Internet is unavailable", Toast.LENGTH_LONG).show();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onItemClicked(int position) {
        mListener.onFlickClicked(photoList.get(position));
    }

    public interface OnListFragmentInteractionListener {
        void onFlickClicked(Photo photo);

    }

    private void retrieveFlicksFromCloud(){
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        RetrofitClient.FlickrService flickrService = RetrofitClient.getFlickrService();
        Call<FlicksResponse> recents = flickrService.getRecents();
        recents.enqueue(new Callback<FlicksResponse>() {
            @Override
            public void onResponse(Call<FlicksResponse> call, Response<FlicksResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        photoList.clear();
                        photoList.addAll(response.body().getPhotos().getPhoto());
                        adapter.notifyDataSetChanged();
                        if (progressBar.isShown()) {
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                }else {
                    if (response.errorBody() != null) {
                        Log.e("RetrofitError", String.valueOf(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FlicksResponse> call, Throwable t) {
                Log.e("RetrofitError", t.getMessage());
            }
        });
    }
}
