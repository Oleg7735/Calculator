package com.example.olleg.functioncalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Olleg on 03.12.2017.
 */

/*public class DBHelper extends SQLiteOpenHelper {
    private static final String ARGUMENT_TABLE_NAME = "argument";
    private static final String FORMULA_TABLE_NAME = "formula";
    private static final String GROUP_TABLE_NAME = "group";


    private enum ArgumentColumns { argumentid, argumentname, argumentdefault, argumentdescription, formulaid }
    private enum GroupColumns { groupid, groupname}
    private enum FormulaColumns { formulaid, formulaname, formuladescription, formulaexpression, groupid}

    public DBHelper(Context context){
        super(context, "functionCalculatorDB", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE argument (\n" +
                "argumentid integer PRIMARY KEY AUTOINCREMENT,\n" +
                "argumentname varchar not null,\n" +
                "argumentdefault double,\n" +
                "argumentdescription varchar,\n" +
                "formulaid integer not null\n" +
                ");");
        db.execSQL("CREATE TABLE formula (\n" +
                "formulaid integer PRIMARY KEY AUTOINCREMENT,\n" +
                "formulaname varchar not null,\n" +
                "formuladescription varchar,\n" +
                "formulaexpression varchar not null,\n" +
                "groupid integer not null\n" +
                ");");
        db.execSQL("CREATE TABLE group (\n" +
                "groupid integer PRIMARY KEY AUTOINCREMENT,\n" +
                "groupname varchar not null\n" +
                ");");
        ContentValues cv = new ContentValues();
        cv.put(GroupColumns.groupname.toString(), "Разные");

        long groupid = db.insert(GROUP_TABLE_NAME, null, cv);

        cv.clear();
        cv.put(FormulaColumns.formulaname.toString(), "Площадь прямоугольника");
        cv.put(FormulaColumns.formuladescription.toString(), "Формула для расчета площади прямоугольника" +
                " по двум его сторонам");
        cv.put(FormulaColumns.formulaexpression.toString(), "a*b");
        cv.put(FormulaColumns.groupid.toString(), groupid);

        long formulaid = db.insert(FORMULA_TABLE_NAME, null, cv);

        cv.clear();
        cv.put(ArgumentColumns.argumentname.toString(), "a");
        cv.put(ArgumentColumns.argumentdescription.toString(), "Длина прямоугольника");
        cv.put(ArgumentColumns.argumentdefault.toString(), 5.0);
        cv.put(ArgumentColumns.formulaid.toString(), formulaid);
        db.insert(ARGUMENT_TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}*/
