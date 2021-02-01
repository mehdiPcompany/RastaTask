package com.pas.rastatask.myclass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

public class sql {

    private SQLiteDatabase db;

    public void Initialize(String Dir, String FileName, boolean CreateIfNecessary) {
        db = SQLiteDatabase.openDatabase(new File(Dir, FileName).toString(), null,
                (CreateIfNecessary ? SQLiteDatabase.CREATE_IF_NECESSARY : 0) | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }
    
    private void checkNull() {
        if (db == null)
            throw new RuntimeException("Object should first be initialized.");
    }

    public boolean IsInitialized() {
        if (db == null)
            return false;
        return db.isOpen();
    }

    public void ExecNonQuery(String Statement) {
        checkNull();
        db.execSQL(Statement);
    }

    public Cursor ExecQuery(String Query) {
        checkNull();
        return ExecQuery2(Query, null);
    }

    public Cursor ExecQuery2(String Query, String[] StringArgs) {
        checkNull();
        return db.rawQuery(Query, StringArgs);
    }

    public String ExecQuerySingleResult(String Query) {
        return ExecQuerySingleResult2(Query, null);
    }

    public String ExecQuerySingleResult2(String Query, String[] StringArgs) {
        checkNull();
        try (Cursor cursor = db.rawQuery(Query, StringArgs)) {
            if (!cursor.moveToFirst())
                return null;
            if (cursor.getColumnCount() == 0)
                return null;
            return cursor.getString(0);
        }
    }

    public void BeginTransaction() {
        checkNull();
        db.beginTransaction();
    }

    public void TransactionSuccessful() {
        db.setTransactionSuccessful();
    }

    public void EndTransaction() {
        db.endTransaction();
    }

    public void Close() {
        if (db != null && db.isOpen())
            db.close();
    }

}
