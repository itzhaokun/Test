package com.aixuetang.test;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * User: zhaokun
 * Date: 2018-05-24
 * Time: 11:38
 * FIXME
 */
public class VersionNameContrast {

    private String versionName ;
    private Context context;

    public VersionNameContrast(Context context, String VersionName){
        this.versionName = VersionName;
        this.context = context;
    }

    public boolean contrast(){
        String oldVersionName = getLocalVersionName(context);
        String[] newVersionName = versionName.split("\\.");
        String[] localVersionName = oldVersionName.split("\\.");
        if (1==newVersionName[0].compareTo(localVersionName[0])){
                return true;
        }else if (0==newVersionName[0].compareTo(localVersionName[0])){
            if (1==newVersionName[1].compareTo(localVersionName[1])){
                return true;
            }else if (0==newVersionName[1].compareTo(localVersionName[1])){
                if (1==newVersionName[2].compareTo(localVersionName[2])){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

}
