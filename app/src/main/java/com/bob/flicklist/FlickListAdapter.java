package com.bob.flicklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bob.flicklist.Model.Photo;

import java.util.ArrayList;

public class FlickListAdapter extends ArrayAdapter<Photo> {
    private final Context context;
    private ArrayList<Photo> photos;

    private ListItemClickListener listener;

    public FlickListAdapter(@NonNull Context context, int resource, ArrayList<Photo> photos, ListItemClickListener listener) {
        super(context, resource, photos);
        this.context = context;
        this.listener = listener;
        this.photos = photos;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_flick_list, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.titleTextView = convertView.findViewById(R.id.title_text_view);
            viewHolder.flickImageView = convertView.findViewById(R.id.image_icon_view);

            //Handle click on each list item
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(position);
                }
            });
            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        Photo photo = photos.get(position);
        viewHolder.titleTextView.setText(photo.getTitle());
        return convertView;
    }

    static class ViewHolder {
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
