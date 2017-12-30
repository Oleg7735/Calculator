package com.example.olleg.functioncalculator.adapters;



import android.content.ClipData;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.example.olleg.functioncalculator.R;
import com.example.olleg.functioncalculator.database.Group;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by Olleg on 10.12.2017.
 */

public class GroupListAdapter extends RealmBaseAdapter<Group> implements ListAdapter{
    public GroupListAdapter(@Nullable OrderedRealmCollection<Group> data) {
        super(data);
    }
    TextView groupName;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.group_list_element, parent, false);
        }

        if(adapterData != null){
            groupName = (TextView) view.findViewById(R.id.grName);
            Group currentGroup = adapterData.get(position);
            groupName.setText(currentGroup.getName());
        }
        return view;
    }
}
