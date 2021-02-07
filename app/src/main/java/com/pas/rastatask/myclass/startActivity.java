package com.pas.rastatask.myclass;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

public interface startActivity {

    //Finish First - After startActivity
    static void Activity2(Context FirstActivity,Class SecondActivity) {
        Intent myIntent = new Intent(FirstActivity, SecondActivity);
        if(isActivityRunning(FirstActivity,SecondActivity)){
            myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }else{
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        FirstActivity.startActivity(myIntent);
    }

    static Boolean isActivityRunning(Context FirstActivity, Class SecondActivity){
        ActivityManager activityManager = (ActivityManager) FirstActivity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (SecondActivity.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }
        return false;
    }

}
