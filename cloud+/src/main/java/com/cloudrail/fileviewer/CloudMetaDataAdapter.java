package com.cloudrail.fileviewer;

/**
 * Created by PM on 11/8/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//package com.cloudrail.fileviewer;

import android.graphics.Color;

/**
 * Created by Team 5 on 10.22.16.
 * An adapter class to fill up the list view item data
 */
public class CloudMetaDataAdapter extends ArrayAdapter<CloudMetaDataServ> {

    private List<CloudMetaDataServ> data;


    public CloudMetaDataAdapter(Context context, int resource, List<CloudMetaDataServ> objects) {
        super(context, resource, objects);
        this.data = objects;

    }


    //To remove an item from the listview
    @Override
    public void remove(CloudMetaDataServ object) {
        String target = object.getName();

        for(int i = 0; i < this.data.size(); ++i) {
            CloudMetaDataServ cloudMetaData = this.data.get(i);
            if(cloudMetaData.getName().equals(target)) {
                this.data.remove(i);
                break;
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        CloudMetaDataServ cmd = this.data.get(position);

        if(cmd != null) {
            ImageView img = (ImageView) v.findViewById(R.id.icon);

            if(img != null) {
                if(cmd.getFolder()) {
                    img.setImageResource(R.drawable.ic_file_folder);
                } else {
                    img.setImageResource(R.drawable.ic_editor_insert_drive_file);
                }
            }

            //For assigning name to list item
            TextView tv = (TextView) v.findViewById(R.id.list_item);
            tv.setText(cmd.getName());

            ImageView tv1 = (ImageView) v.findViewById(R.id.driveimg);

            //For assigning image

            //For setting up the color information
            if(cmd.getService()==1) {
                tv.setTextColor(Color.BLUE);
                tv1.setImageResource(R.drawable.ic_dropbox);
            }
                if(cmd.getService()==3)
                {
                tv.setTextColor(Color.GREEN);
                 tv1.setImageResource(R.drawable.ic_google_drive);
                }
            if(cmd.getService()==4)
            {
             tv.setTextColor(Color.RED);
                tv1.setImageResource(R.drawable.ic_onedrive);
            }

        }

        return v;
    }
}
