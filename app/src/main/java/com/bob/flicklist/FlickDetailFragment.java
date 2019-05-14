package com.bob.flicklist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bob.flicklist.Model.Photo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FlickDetailFragment extends Fragment {

    private Photo photo;
    private ImageView mainImageView;
    private ProgressBar progressBar;
    public FlickDetailFragment() {
        super();
    }

    public static FlickDetailFragment newInstance(Photo photo){

        FlickDetailFragment fragment =  new FlickDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_photo", photo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            photo = (Photo) getArguments().get("selected_photo");
        }

        //To save the state of the fragment on activity recreation
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed_flick, container, false);
        mainImageView = rootView.findViewById(R.id.main_image);
        progressBar = rootView.findViewById(R.id.progressBar2);
        progressBar.setIndeterminate(true);
        if(photo.getImage() == null) { //Image not cached
            Picasso.with(getContext()).load(photo.getImageUrl()).fit().centerCrop().into(mainImageView, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {
                    progressBar.setVisibility(View.INVISIBLE);
                    mainImageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            });
        }
        else{ //IF Target is used in adapter instead of image view
            mainImageView.setImageBitmap(photo.getImage());
            progressBar.setVisibility(View.INVISIBLE);

       }
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
