package com.example.weatherapp2.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherUtil {
    private static final String TAG = "tag";
    private static String BASE_URL="https://v0.yiketianqi.com/free/week";
    private static String APP_ID="19898838";
    private static String APP_SECRET="eOqK2ysR";
    public static String doGet(String url){
        String result="";
        BufferedReader reader=null;
        HttpURLConnection httpURLConnection=null;
        try {
            //1、建立连接
            URL requestUrl=new URL(url);
            httpURLConnection=(HttpURLConnection)requestUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");//设置请求方式
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            //2、获取二进制流
            InputStream inputStream=httpURLConnection.getInputStream();
            //3、将二进制流包装
            reader=new BufferedReader((new InputStreamReader(inputStream)));
            //从BufferedReader中读取String字符串
            String line;
            StringBuilder builder=new StringBuilder();
            while((line=reader.readLine())!=null){
                builder.append(line);
                builder.append("\n");
            }
            if(builder.length()==0){
                return null;
            }
            result=builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getWeatherOfCity(String city){
        //拼接出get请求的url
        String weatherUrl=BASE_URL+"?appid="+APP_ID+"&appsecret="+APP_SECRET+"&city="+city+"&unescape=1";
        String weatherResult=doGet(weatherUrl);
        Log.d("util","weatherJson："+weatherResult);
        return weatherResult;
    }
}
