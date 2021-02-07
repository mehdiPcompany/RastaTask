package com.pas.rastatask.myclass;

import android.database.Cursor;

public class keyValueStore {

    private final sql sql1;

    public keyValueStore(sql sql1) {
        this.sql1 = sql1;
    }

    public void Initialize(String Dir, String FileName) {
        if (sql1.IsInitialized()) {
            sql1.Close();
        }
        sql1.Initialize(Dir, FileName, true);
        CreateTable();
    }

    private void CreateTable() {
        sql1.ExecNonQuery("CREATE TABLE IF NOT EXISTS main(key TEXT PRIMARY KEY, value TEXT)");
    }

    public void Put(String Key, Object Value) {
        sql1.ExecNonQuery("INSERT OR REPLACE INTO main VALUES('" + Key + "','" + Value + "')");
    }

    public String Get(String Key) {
        Cursor cursor1 = sql1.ExecQuery("SELECT value FROM main WHERE key = '" + Key + "'");
        cursor1.moveToPosition(0);
        return cursor1.getString(cursor1.getColumnIndexOrThrow("value"));
    }

    public Boolean ContainsKey(String Key) {
        Cursor cursor1 = sql1.ExecQuery("SELECT value FROM main WHERE key = '" + Key + "'");
        if(cursor1.getColumnCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public void Remove(String Key){
        sql1.ExecNonQuery("DELETE FROM main WHERE key = '" + Key + "'");
    }

    public void DeleteAll(){
        sql1.ExecNonQuery("DROP TABLE main");
        CreateTable();
    }


}
