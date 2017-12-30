package com.example.olleg.functioncalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olleg.functioncalculator.adapters.GroupListAdapter;
import com.example.olleg.functioncalculator.database.CalcDB;
import com.example.olleg.functioncalculator.database.Group;

import io.realm.RealmResults;

public class GroupActivity extends AppCompatActivity {
    private final int ACTIVITY_FUNCTION_REQUEST = 2;
    private ListView groupList;
    private CalcDB calcDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calcDB = new CalcDB();
        setContentView(R.layout.activity_group);

        RealmResults<Group> groups = calcDB.getGroups();
        //groups.get(0).setName("Default");
        GroupListAdapter groupAdapter = new GroupListAdapter(groups);

        groupList = (ListView) findViewById(R.id.groupListView);
        groupList.setAdapter(groupAdapter);
        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupActivity.this, FunctionActivity.class);
                long groupId = ((Group)parent.getItemAtPosition(position)).getId();
                intent.putExtra("groupId", groupId);
                startActivityForResult(intent, ACTIVITY_FUNCTION_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case ACTIVITY_FUNCTION_REQUEST:{
                if(resultCode == RESULT_OK){
                setResult(RESULT_OK, data);
                finish();
                }
                break;
            }
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        calcDB.close();
    }
}
