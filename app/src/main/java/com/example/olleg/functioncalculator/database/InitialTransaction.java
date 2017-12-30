package com.example.olleg.functioncalculator.database;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;

import io.realm.RealmObjectSchema;


/**
 * Created by Olleg on 07.12.2017.
 */

public class InitialTransaction implements Realm.Transaction {

    @Override
    public void execute(Realm realm) {
        Argument a = new Argument();
        a.setName("a");
        a.setDefaulValue(5.0);
        a.setArgumentDescription("Длина прямоугольника");

        Argument b = new Argument();
        b.setName("b");
        b.setDefaulValue(2.0);
        b.setArgumentDescription("Ширина прямоугольника");

        ArrayList<Argument> rectSArguments = new ArrayList<>();
        rectSArguments.add(a);
        rectSArguments.add(b);

        Formula sFormula = new Formula();
        sFormula.setName("Площадь прямоугольника");
        sFormula.setDescription("Форула для вычисления площади прямоугольника по двум сторонам");
        sFormula.setExpression("a*b");

        //realm.beginTransaction();
        Number currentId = realm.where(Group.class).max(GroupColumns.ID.toString());
        long nextId = (currentId == null) ? 1 : currentId.longValue() + 1;

        Group defaultGroup = realm.createObject(Group.class, nextId);
        defaultGroup.setName("Предопределенные");

        currentId = realm.where(Formula.class).max(FormulaColumns.ID.toString());
        nextId = (currentId == null) ? 1 : currentId.longValue() + 1;
        sFormula.setId(nextId);

        currentId = realm.where(Argument.class).max(ArgumentColumns.ID.toString());
        nextId = (currentId == null) ? 1 : currentId.longValue() + 1;
        for(Argument arg:rectSArguments) {
            arg.setId(nextId);
            nextId++;
        }
        List<Argument> managedArgs = realm.copyToRealm(rectSArguments);
        Formula managedFormula = realm.copyToRealm(sFormula);
        for(Argument arg:managedArgs){
            managedFormula.getArgumants().add(arg);
        }
        defaultGroup.getFurmula().add(managedFormula);

        //realm.commitTransaction();
    }
}
