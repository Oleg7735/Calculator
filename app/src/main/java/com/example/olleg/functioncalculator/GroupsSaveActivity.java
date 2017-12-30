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

import com.example.olleg.functioncalculator.adapters.GroupListAdapter;
import com.example.olleg.functioncalculator.database.CalcDB;
import com.example.olleg.functioncalculator.database.Formula;
import com.example.olleg.functioncalculator.database.Group;

import io.realm.RealmResults;
// TODO: проверять наличие функции с тем же именем в группе?
public class GroupsSaveActivity extends AppCompatActivity {

    View promptView;
    View functionSavePrompt;
    private final int GROUP_EDIT_ID = 1;
    private CalcDB calcDB;
    private GroupListAdapter adapter;
    ListView groupListView;
    RealmResults<Group> groups;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calcDB = new CalcDB();

        setContentView(R.layout.activity_groups_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                showCreateDialog();

            }
        });

        groups = calcDB.getGroups();

        groupListView = (ListView) findViewById(R.id.groupsSaveList);
        adapter = new GroupListAdapter(groups);
        groupListView.setAdapter(adapter);

        registerForContextMenu(groupListView);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.groupSaveCoordinatorLayout);

        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                showFunctionAddDialog(groups.get(position));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        calcDB.close();
    }

    private void showFunctionAddDialog(Group selectedGroup){
        LayoutInflater inflater = LayoutInflater.from(this);
        functionSavePrompt = inflater.inflate(R.layout.function_add_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(functionSavePrompt);

        final EditText functionName = (EditText)functionSavePrompt.findViewById(R.id.function_prompt_name);
        final EditText functionDescription = (EditText)functionSavePrompt.findViewById(R.id.function_prompt_description);
        final long groupId = selectedGroup.getId();
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String newName = functionName.getText().toString().trim();
                                String newDescription = functionDescription.getText().toString().trim();
                                if(newName.compareTo("")!=0) {
                                    Formula f = CurrentFunction.getInstance().getFormula();
                                    f.setName(newName);
                                    f.setDescription(newDescription);
                                    calcDB.addFormulaInGroup(f, groupId);
                                    adapter.notifyDataSetChanged();
                                    finish();
                                }
                                else{
                                    Snackbar snack = Snackbar.make(coordinatorLayout, "Имя функции не может быть пустым", Snackbar.LENGTH_LONG)
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
    private void showCreateDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        promptView = inflater.inflate(R.layout.group_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptView);

        final EditText groupName = (EditText)promptView.findViewById(R.id.group_prompt_name);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String newName = groupName.getText().toString().trim();
                                if(newName.compareTo("")!=0) {
                                    Group newGroup = new Group();
                                    newGroup.setName(groupName.getText().toString());
                                    calcDB.addGroup(newGroup);
                                    adapter.notifyDataSetChanged();
                                }
                                else{
                                    Snackbar snack = Snackbar.make(coordinatorLayout, "Имя группы не может быть пустым", Snackbar.LENGTH_LONG)
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

    private void showEditDialog(int position){
        LayoutInflater inflater = LayoutInflater.from(this);
        promptView = inflater.inflate(R.layout.group_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptView);

        final EditText groupName = (EditText)promptView.findViewById(R.id.group_prompt_name);
        final Group oldGroup = groups.get(position);
        groupName.setText(oldGroup.getName());
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String newName = groupName.getText().toString().trim();
                                if(newName.compareTo("")!=0) {
                                    calcDB.setGroupName(oldGroup, groupName.getText().toString());
                                    adapter.notifyDataSetChanged();
                                }
                                else{
                                    Snackbar snack = Snackbar.make(coordinatorLayout, "Имя группы не может быть пустым", Snackbar.LENGTH_LONG)
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, GROUP_EDIT_ID, 0, "Изменить");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case GROUP_EDIT_ID:{
                showEditDialog(acmi.position);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

}
