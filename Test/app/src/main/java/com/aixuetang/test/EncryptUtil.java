package com.aixuetang.test;


/**
 * User: zhaokun
 * Date: 2018-05-24
 * Time: 13:52
 * FIXME
 */
public class EncryptUtil {

    public String timestamp(){
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        return timestamp;
    }

}
