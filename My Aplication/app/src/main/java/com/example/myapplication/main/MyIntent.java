package com.example.myapplication.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MyIntent {
    public static void intent(Context context, Class<?> myClass){
        Intent intent = new Intent(context, myClass);
        context.startActivity(intent);
    }

    public static void intentFinish_Top(Context context, Class<?> myClass){
        Intent intent = new Intent(context, myClass);
        ((Activity)context).finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
