package com.bob.flicklist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bob.flicklist.Model.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import java.util.ArrayList;

public class FlickListAdapter extends ArrayAdapter<Photo> {
    private final Context context;
    private ArrayList<Photo> photos;
    Photo photo;
    ViewHolder viewHolder;

    private ListItemClickListener listener;
    private RequestCreator request;

    public FlickListAdapter(@NonNull Context context, int resource, ArrayList<Photo> photos, ListItemClickListener listener) {
        super(context, resource, photos);
        this.context = context;
        this.listener = listener;
        this.photos = photos;
        setNotifyOnChange(true);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_flick_list, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.titleTextView = convertView.findViewById(R.id.title_text_view);
            viewHolder.flickImageView = convertView.findViewById(R.id.image_icon_view);

            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }



        photo = photos.get(position);
        if(photo.getTitle().length() == 0)
            photo.setTitle("Untitled");
        viewHolder.titleTextView.setText(photo.getTitle());

       viewHolder.flickImageView.setImageResource(R.drawable.ic_launcher_foreground);



       Target target = new Target() {
           @Override
           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               viewHolder.flickImageView.setImageBitmap(bitmap);
               photo.setImage(bitmap);

           }

           @Override
           public void onBitmapFailed(Drawable errorDrawable) {
               viewHolder.flickImageView.setImageDrawable(errorDrawable);
           }

           @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) {

           }
       };

       //Picasso
        Picasso.with(context).load(photo.getImageUrl())
                //.into(target);                // Uncomment to make picasso network call only once (Delay loading and resizing images)
                .fit().centerCrop().into(viewHolder.flickImageView);  //Comment if above line is uncommented.


        //Handle click on each list item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(position);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        private TextView titleTextView;
        private ImageView flickImageView;
        }


/**
 * Interface to establish communication between adapter and @ListCommitsFragment
 */
interface ListItemClickListener{
    void onItemClicked(int position);
}
}
