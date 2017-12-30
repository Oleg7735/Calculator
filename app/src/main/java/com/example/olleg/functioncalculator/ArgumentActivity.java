package com.example.olleg.functioncalculator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.olleg.functioncalculator.adapters.ArgumentAdapter;
import com.example.olleg.functioncalculator.database.Argument;
//TODO: сделать проверку на значение аргумента при добавлении  и изменении аргумента
public class ArgumentActivity extends AppCompatActivity {
    View promptView;
    ListView argumentsList;
    AlertDialog argumentDialog;
    CoordinatorLayout coordinatorLayout;
    //AlertDialog argumentEditDialog;
    ArgumentAdapter adapter;
    final int ARGUMENT_EDIT_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argument);

        adapter = new ArgumentAdapter(this);
        argumentsList = (ListView)findViewById(R.id.argumentListView);
        argumentsList.setAdapter(adapter);

        prepareDialog();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                argumentDialog.show();
            }
        });
        registerForContextMenu(argumentsList);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.argument_coordinator_layout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, ARGUMENT_EDIT_ID, 0, "Изменить");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case ARGUMENT_EDIT_ID:{
                showEditDialog(acmi.position);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }
    private void showEditDialog(int position){
        View promptView;
        LayoutInflater inflater = LayoutInflater.from(this);
        promptView = inflater.inflate(R.layout.atribute_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptView);

        final EditText argumentName = (EditText)promptView.findViewById(R.id.atribute_prompt_name);
        final EditText argumentValue = (EditText)promptView.findViewById(R.id.atribute_prompt_value);

        final Argument oldArg = CurrentFunction.getInstance().getFormula().getArgumants().get(position);
        argumentName.setText(oldArg.getName());
        argumentValue.setText(Double.toString(oldArg.getDefaulValue()));

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String name = argumentName.getText().toString().trim();
                                String value =  argumentValue.getText().toString().trim();
                                if(name.compareTo("")!=0 && value.compareTo("")!=0) {
                                    oldArg.setName(name);
                                    oldArg.setDefaulValue(Double.parseDouble(value));
                                    adapter.notifyDataSetChanged();
                                }
                                else{
                                    Snackbar snack = Snackbar.make(coordinatorLayout, "Имя аргумента и его значение не могут быть пустыми", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null);
                                    View view = snack.getView();
                                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setTextColor(Color.RED);
                                    snack.show();
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }
    private void prepareDialog(){
        View promptView;
        LayoutInflater inflater = LayoutInflater.from(this);
        promptView = inflater.inflate(R.layout.atribute_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptView);

        final EditText argumentName = (EditText)promptView.findViewById(R.id.atribute_prompt_name);
        final EditText argumentValue = (EditText)promptView.findViewById(R.id.atribute_prompt_value);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                String name = argumentName.getText().toString().trim();
                                String value =  argumentValue.getText().toString().trim();
                                if(name.compareTo("")!=0 && value.compareTo("")!=0) {
                                    addArgument(argumentName.getText().toString(), Double.parseDouble(argumentValue.getText().toString()));
                                }
                                else{
                                    Snackbar snack = Snackbar.make(coordinatorLayout, "Имя аргумента и его значение не могут быть пустыми", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null);
                                    View view = snack.getView();
                                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                                    tv.setTextColor(Color.RED);
                                    snack.show();
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        argumentDialog = alertDialogBuilder.create();
    }
    private void addArgument(String name, double value){
        Argument arg = new Argument();
        arg.setName(name);
        arg.setDefaulValue(value);
        CurrentFunction.getInstance().getFormula().getArgumants().add(arg);
        adapter.notifyDataSetChanged();
    }
}
