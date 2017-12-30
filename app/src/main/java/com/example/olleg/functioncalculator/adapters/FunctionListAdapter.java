package com.example.olleg.functioncalculator.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.olleg.functioncalculator.R;
import com.example.olleg.functioncalculator.database.Formula;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by Olleg on 12.12.2017.
 */

public class FunctionListAdapter  extends RealmBaseAdapter<Formula> implements ListAdapter {

    public FunctionListAdapter(@Nullable OrderedRealmCollection<Formula> data) {
        super(data);
    }
    TextView formulaName;
    TextView formulaDescription;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.function_list_element, parent, false);
        }
        if(adapterData != null){
            formulaName = (TextView) view.findViewById(R.id.fName);
            formulaDescription = (TextView) view.findViewById(R.id.fDescription);
            Formula currentFormula = adapterData.get(position);
            formulaName.setText(currentFormula.getName());
            formulaDescription.setText(currentFormula.getDescription());
        }
        return view;
    }
}
