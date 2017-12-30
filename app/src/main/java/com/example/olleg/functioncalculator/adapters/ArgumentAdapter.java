package com.example.olleg.functioncalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.olleg.functioncalculator.CurrentFunction;
import com.example.olleg.functioncalculator.R;

import java.util.zip.Inflater;

/**
 * Created by Olleg on 15.12.2017.
 */

public class ArgumentAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    Context ctx;
    private double argumentValue;
    public ArgumentAdapter(Context context){
        ctx = context;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        int s =  CurrentFunction.getInstance().getFormula().getArgumants().size();
        return s;
    }

    @Override
    public Object getItem(int position) {
        return CurrentFunction.getInstance().getFormula().getArgumants().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.argument_list_element, parent, false);
        }
        ((TextView)view.findViewById(R.id.argumentName)).setText(CurrentFunction.getInstance().getFormula().getArgumants().get(position).getName());
         argumentValue = CurrentFunction.getInstance().getFormula().getArgumants().get(position).getDefaulValue();
        ((TextView)view.findViewById(R.id.argumentValue)).setText(" = "+Double.toString(argumentValue));
        return view;
    }
}
