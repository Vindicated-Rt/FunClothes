package com.mystery.funclothes.Util;

import android.util.Log;

import com.mystery.funclothes.Base.BaseURL;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vindicated-Rt
 * 2019/7/9 3:11 AM
 */
public class HttpUtil {
    public static String post(String urlPath, JSONObject postJson)throws Exception{
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        Log.i(BaseURL.TAG, "post地址: "+urlPath);
        conn.setConnectTimeout(5000);
        // 设置允许输出
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        // 设置contentType
        conn.setRequestProperty("Content-Type", "application/json");
        DataOutputStream os = new DataOutputStream( conn.getOutputStream());
        String content = String.valueOf(postJson);
        os.writeBytes(content);
        os.flush();
        os.close();
        String result = "";
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Log.i(BaseURL.TAG, "链接成功"+conn.getResponseCode());
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            String recieveData = null;
            while ((recieveData = bf.readLine()) != null){
                result += recieveData + "\n";
            }
            in.close();
            conn.disconnect();
        }else {
            Log.i(BaseURL.TAG, "链接失败"+conn.getResponseCode());
        }
        Log.i(BaseURL.TAG, "返回："+result);
        return result;
    }
}
