package com.wenbing.mvpdemo.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    private static int LEVEL = WARN;
    private static String TAG = "baby";
    /**
     * 修改Log显示的级别
     * 
     * @param lv
     *            可以使用当前类中定义6个级别
     */
    public static void setLevel(int lv) {
        LEVEL = lv;
    }
    /**
     * 设置自定义缺省TAG，调用单参数的v(String msg)这一类方法时会使用，若不设置则为Indoorun
     * 
     * @param str
     */
    public static void setTag(String str) {
        if (str != null)
            TAG = str;
    }
    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
    public static void v(String msg) {
        v(TAG, msg);
    }
    public static void d(String msg) {
        d(TAG, msg);
    }
    public static void i(String msg) {
        i(TAG, msg);
    }
    public static void w(String msg) {
        w(TAG, msg);
    }
    public static void e(String msg) {
        e(TAG, msg);
    }
    private static boolean _isLog = true;
    private static boolean _println = false;
    private static SimpleDateFormat _sdf    = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
    private static SimpleDateFormat _stf    = new SimpleDateFormat(" [HH:mm:ss:SSS] ");
    private static SimpleDateFormat _lff    = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    private static final String LOG_DIR = "logs";
    private static final String LOG_POSTFIX = "log";
    private static File _logFile;
    public static void setLogFile(Context context){
        Date createTime  = new Date();
        String fileName    = _lff.format(createTime);
        File appDir = context.getExternalFilesDir(null);
        File logDir = new File(appDir, LOG_DIR);
        if(!logDir.exists())
            logDir.mkdir();
        _logFile = new File(logDir,fileName+"."+LOG_POSTFIX);
    }
    public static void setLog(boolean isLog){
        _isLog = isLog;
    }
    public static void setPrintln(boolean println){
        _println = println;
    }
    public static  void amLogTime(String msg){
        StackTraceElement ste   = Thread.currentThread().getStackTrace()[3];
        _amLog(ste, msg, _stf);
    }
    public static  void amLogDate(String msg){
        StackTraceElement ste   = Thread.currentThread().getStackTrace()[3];
        _amLog(ste, msg, _sdf);
    }
    public static  void amLog(String msg){
        StackTraceElement ste   = Thread.currentThread().getStackTrace()[3];
        _amLog(ste, msg, null);
    }
    public static String stackDump(){
        StackTraceElement stes[]   = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append("\t**Stack Dump: ["+stes[3].getMethodName());
        for(int i = 4; i < stes.length; i ++){
            sb.append("<-"+stes[i].getMethodName());
        }
        return sb.toString();
    }
    public static String stackDumpClass(){
        StackTraceElement stes[]   = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append("\t**Stack Dump:\n");
        for(int i = 3; i < stes.length; i ++){
            sb.append(String.format("\t\tline:%04d\t%s.%s\n",
                stes[i].getLineNumber(), stes[i].getClassName(), stes[i].getMethodName()));
        }
        sb.append("\t**Stack Dump END\n");
        return sb.toString();
    }
    public static void amLogEx(String msg, Throwable t){
        if(!_isLog)
            return ;
        StackTraceElement ste   = Thread.currentThread().getStackTrace()[3];
        String className        = ste.getClassName();
        String methodName       = ste.getMethodName();
        StringBuilder sb        = new StringBuilder();
        sb.append(className);
        sb.append(".");
        sb.append(methodName);
        sb.append("()");
        String tag = sb.toString();
        Log.w(tag, msg);
        Log.w(tag, exStackTrace(t));
        String g = null;
        if(_println){
            if(null == g){
                sb.append(" ");
                sb.append(msg);
                g = tag.toString();
            }
            System.out.println(g);
        }
        if(null != _logFile){
            if(null == g){
                sb.append(" ");
                sb.append(msg);
                g = tag.toString();
            }
            _logToFile(g);
        }
    }
    public static String exStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
    static void _logToFile(String msg){
        FileOutputStream fileOut=null;
        OutputStreamWriter outStream = null;
        try{
            fileOut = new FileOutputStream(_logFile, true);
            outStream = new OutputStreamWriter(fileOut);
            outStream.write(msg);
        }catch (Exception e){
        }finally{
            try {
                if(null!=outStream)
                    outStream.close();
                if(null!=fileOut)
                    fileOut.close();
            }catch (Exception e) {
            }
        }
    }
    public static  void _amLog(StackTraceElement ste, String msg, SimpleDateFormat sdf){
        if(!_isLog)
            return ;
        String timeString = null;
        if(null != sdf){
            Date now = new Date();
            timeString = sdf.format(now);
        }
        String className        = ste.getClassName();
        String methodName       = ste.getMethodName();
        StringBuilder tag = new StringBuilder();
        tag.append(className);
        tag.append(".");
        tag.append(methodName);
        tag.append("()");
        if(null != timeString)
            tag.append(timeString);
        Log.w(tag.toString(), msg);
        String g = null;
        if(_println){
            if(null == g){
                tag.append(" ");
                tag.append(msg);
                g = tag.toString();
            }
            System.out.println(g);
        }
        if(null != _logFile){
            if(null == g){
                tag.append(" ");
                tag.append(msg);
                tag.append("\n");
                g = tag.toString();
            }
            _logToFile(g);
        }
    }
}