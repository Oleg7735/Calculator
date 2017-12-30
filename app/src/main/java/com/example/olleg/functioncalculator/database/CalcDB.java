package com.example.olleg.functioncalculator.database;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Olleg on 06.12.2017.
 */

public class CalcDB {
    private Realm realm;
    private static RealmConfiguration config  = new RealmConfiguration.Builder()
            .name("calcdb.realm")
        .initialData(new InitialTransaction())
            .build();
    public CalcDB(){
        /*config = new RealmConfiguration.Builder()
        .name("calcdb.realm")
        .initialData(new InitialTransaction())
        .build();*/
        realm = Realm.getInstance(config);

    }
    public RealmResults<Group> getGroups(){
        return realm.where(Group.class).findAll();
    }
    public RealmList<Formula> getFormulaByGroupId(long id){
        return realm.where(Group.class).equalTo(GroupColumns.ID.toString(), id).findFirst().getFurmula();
    }
    public Formula getUnmanagedFormulaById(long id){
        Formula f = realm.where(Formula.class).equalTo(FormulaColumns.ID.toString(), id).findFirst();
        return realm.copyFromRealm(f);
    }
    public void close(){
        realm.close();
    }

    public void insertOrUpdateGroup(Group group){
        if(group.getId() < 1){
            Number currentId = realm.where(Group.class).max(GroupColumns.ID.toString());
            long nextId = (currentId == null) ? 1 : currentId.longValue() + 1;
            group.setId(nextId);
        }
        realm.insertOrUpdate(group);
    }
    public void setGroupName(Group group, String name){
        realm.beginTransaction();
        group.setName(name);
        realm.commitTransaction();
    }
    public void addGroup(Group group){
        if(group.getId() < 1){
            Number currentId = realm.where(Group.class).max(GroupColumns.ID.toString());
            long nextId = (currentId == null) ? 1 : currentId.longValue() + 1;
            group.setId(nextId);
        }
        realm.beginTransaction();
        realm.insert(group);
        realm.commitTransaction();
    }
    public void addFormulaInGroup(Formula newFormula, long groupId){
        if(newFormula.getId() < 1){
            Number currentFormulaId = realm.where(Formula.class).max(FormulaColumns.ID.toString());
            long nextFormulaId = (currentFormulaId == null) ? 1 : currentFormulaId.longValue() + 1;
            newFormula.setId(nextFormulaId);
        }
        Number currentArgumentId = realm.where(Argument.class).max(ArgumentColumns.ID.toString());
        long nextArgumentId = (currentArgumentId == null) ? 1 : currentArgumentId.longValue() + 1;
        for (Argument arg: newFormula.getArgumants()) {
            if(arg.getId() < 1){
                arg.setId(nextArgumentId);
                nextArgumentId++;
            }
        }
        Argument mArg;
        realm.beginTransaction();
        /*for (Argument arg: newFormula.getArgumants()) {
            realm.insert(arg);
        }*/
        Formula mFormula = realm.copyToRealm(newFormula);
        Group gr = realm.where(Group.class).equalTo(GroupColumns.ID.toString(), groupId).findFirst();
        gr.getFurmula().add(mFormula);
        /*for (Argument arg: newFormula.getArgumants()) {
            mArg = realm.copyToRealm(arg);
            mFormula.getArgumants().add(mArg);
        }*/
        realm.commitTransaction();
    }
}
