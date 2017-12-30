package com.example.olleg.functioncalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olleg.functioncalculator.adapters.ArgumentAdapter;
import com.example.olleg.functioncalculator.calculator.Calculator;
import com.example.olleg.functioncalculator.database.Argument;
import com.example.olleg.functioncalculator.database.CalcDB;
import com.example.olleg.functioncalculator.database.Formula;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.Inflater;

import io.realm.RealmList;
import layout.FunctionFragment;
import layout.FunctionFragment2;
//TODO: добавить возможность вставлять переменные и подстановку значений в переменные при расчете значения выражения
//TODO: добавить признак загруженности из базы и в зависимочти от него выбирать процедуру сохранения; реализовать сохранение функции, которая уже есть в базе
//TODO: справить при добавлении аргумента поля  заполняются предыдущим добавленным аргументом
public class MainActivity extends FragmentActivity {

    private Calculator calculator;
    private final int ACTIVITY_GROUP_REQUEST = 1;
    private final int ACTIVITY_ARGUMENTS_REQUEST = 2;
    private final int ACTIVITY_GROUPS_SAVE_REQUEST = 3;
    static final int PAGE_COUNT = 2;

    private CalcDB calcDB;

    //private Formula currentFormula;

    EditText functionText;

    ViewPager functionPager;
    PagerAdapter functionPagerAdapter;
    private Button loadButton;
    private Button argumentButton;
    private Button saveButton;
    private TextView resultText;

    private void setFormula(Formula formula){
        CurrentFunction.getInstance().setFormula(formula);
        functionText.setText(formula.getExpression());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case ACTIVITY_GROUP_REQUEST:{
                if(resultCode == RESULT_OK){
                    Formula selectedFormula = calcDB.getUnmanagedFormulaById(data.getLongExtra("formulaId", -1));
                    setFormula(selectedFormula);
                    CurrentFunction.getInstance().fromDB = true;

                }
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        calcDB = new CalcDB();

        functionPager = (ViewPager) findViewById(R.id.functionPager);
        functionPagerAdapter = new FunctionFragmentPagerAdapter(getSupportFragmentManager());

        functionPager.setAdapter(functionPagerAdapter);
        functionText = (EditText) findViewById(R.id.functionText);

        resultText = (TextView) findViewById(R.id.resultText);
        //functionText.setShowSoftInputOnFocus(false);
        functionText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText text;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        text = (EditText) v;
                        int position = text.getOffsetForPosition(event.getX(), event.getY());
                        text.setSelection(position);
                        break;
                    }
                }
                return true;
            }
        });

        loadButton = (Button) findViewById(R.id.functionLoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GroupActivity.class);
                startActivityForResult(intent, ACTIVITY_GROUP_REQUEST);
            }
        });

        argumentButton = (Button) findViewById(R.id.argumentsButton);
        argumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArgumentActivity.class);
                startActivityForResult(intent, ACTIVITY_ARGUMENTS_REQUEST);
            }
        });

        saveButton = (Button)findViewById(R.id.functionSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GroupsSaveActivity.class);
                startActivityForResult(intent, ACTIVITY_GROUPS_SAVE_REQUEST);
            }
        });

    }
    private class FunctionFragmentPagerAdapter extends FragmentPagerAdapter{

        public FunctionFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
                if(position == 0){
                    return FunctionFragment.newInstance("param1", "param2");
                }
                else{
                    return FunctionFragment2.newInstance("param1", "param2");
                }

        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

    public void numSymbol(String text){
        functionText.getText().insert(functionText.getSelectionStart(), text);
        CurrentFunction.getInstance().getFormula().setExpression(functionText.getText().toString());
        //functionText.append(text);
    }
    public void deleteSymbol(){
        int startPosition = functionText.getSelectionStart();
        if(startPosition != 0) {
            functionText.getText().delete(startPosition-1, startPosition);
            CurrentFunction.getInstance().getFormula().setExpression(functionText.getText().toString());
        }
        /*int endPosition = functionText.getSelectionEnd();
        if(startPosition != endPosition) {
            functionText.getText().delete(startPosition, endPosition);
        }*/
    }
    public void clearAll(){
        functionText.getText().clear();
        resultText.setText("");
        CurrentFunction.getInstance().getFormula().setExpression(functionText.getText().toString());
    }
    public void calculate(){
        try {
            String function = functionText.getText().toString();
            RealmList<Argument> arguments = CurrentFunction.getInstance().getFormula().getArgumants();
            ArrayList<Argument> args = new ArrayList<Argument>();
            Argument arg;
            for (Argument argument :arguments ) {
                arg = new Argument();
                arg.setName(argument.getName());
                arg.setDefaulValue(argument.getDefaulValue());
                args.add(arg);
            }
            //args.sort((a1,a2) -> a1.getName().length() - a2.getName().length());
            Collections.sort(args, (a1,a2) -> a2.getName().length() - a1.getName().length());
            /*args.sort(new Comparator<Argument>() {
                @Override
                public int compare(Argument o1, Argument o2) {
                    return o1.getName().length() - o2.getName().length();
                }
            });*/
            for(Argument a : args){
                function = function.replaceAll(a.getName(), Double.toString(a.getDefaulValue()));
            }
            //resultText.setText("");
            resultText.setText(Double.toString(calculator.calculate(function)));
        }
        catch (IllegalArgumentException ex){
            Toast.makeText(this, "Некорректоное выражение для вычисления",Toast.LENGTH_LONG).show();
        }
    }
    public void showInsertArgumentPrompt(){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        ArgumentAdapter adapter = new ArgumentAdapter(this);
        adb.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numSymbol(CurrentFunction.getInstance().getFormula().getArgumants().get(which).getName());

            }
        });
        adb.setCancelable(true)
                .setNegativeButton("Готово",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        adb.create().show();
        /*View promptView;
        LayoutInflater inflater = LayoutInflater.from(this);
        promptView = inflater.inflate(R.layout.argument_insert_in_function_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptView);
        ArgumentAdapter adapter = new ArgumentAdapter(this);
        ListView argumentsList = (ListView) promptView.findViewById(R.id.argument_insert_list_view);
        argumentsList.setAdapter(adapter);
        argumentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                numSymbol(CurrentFunction.getInstance().getFormula().getArgumants().get(position).getName());
            }
        });
        alertDialogBuilder
                .setCancelable(true)
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();*/
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        calcDB.close();
    }
}
