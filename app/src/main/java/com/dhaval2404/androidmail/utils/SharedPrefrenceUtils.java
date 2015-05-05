package com.dhaval2404.androidmail.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceUtils {
    public static final String SHARED_PREFERENCE_TAG="ANDROID_MAIL";

    private SharedPrefrenceUtils(){
    	 throw new AssertionError();
    }
    
    public static String getString(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public static String getString(Context mContext, String key,  String defaultValue){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public static void putString(Context mContext, String key, String value ){
        SharedPreferences pref= mContext.getSharedPreferences(SHARED_PREFERENCE_TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static int getInt(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getInt(key, 0);
    }

    public static int getInt(Context mContext, String key,  int defaultValue){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public static void putInt(Context mContext, String key, int value ){
        SharedPreferences pref= mContext.getSharedPreferences(SHARED_PREFERENCE_TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    
    public static long getLong(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getLong(key, 0);
    }

    public static long getLong(Context mContext, String key,  long defaultValue){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getLong(key, defaultValue);
    }

    public static void putLong(Context mContext, String key, long value ){
        SharedPreferences pref= mContext.getSharedPreferences(SHARED_PREFERENCE_TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    public static boolean getBoolean(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static boolean getBoolean(Context mContext, String key,  boolean defaultValue){
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREFERENCE_TAG,Activity.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context mContext, String key, boolean value ){
        SharedPreferences pref= mContext.getSharedPreferences(SHARED_PREFERENCE_TAG, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
