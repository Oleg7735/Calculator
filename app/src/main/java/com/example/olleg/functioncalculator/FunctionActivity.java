package com.example.olleg.functioncalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olleg.functioncalculator.adapters.FunctionListAdapter;
import com.example.olleg.functioncalculator.database.CalcDB;
import com.example.olleg.functioncalculator.database.Formula;

import io.realm.RealmList;
import io.realm.RealmResults;


public class FunctionActivity extends AppCompatActivity {

    private ListView formulaList;
    private CalcDB calcDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calcDB = new CalcDB();
        setContentView(R.layout.activity_function);

        Intent intent = getIntent();
        RealmList<Formula> formulas = calcDB.getFormulaByGroupId(intent.getLongExtra("groupId",-1));
        /*long id = formulas.get(0).getId();
        String name = formulas.get(0).getName();*/
        FunctionListAdapter formulaAdapter = new FunctionListAdapter(formulas);

        formulaList = (ListView) findViewById(R.id.functionListView);
        formulaList.setAdapter(formulaAdapter);
        formulaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("formulaId",((Formula)parent.getItemAtPosition(position)).getId());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        calcDB.close();
    }
}
