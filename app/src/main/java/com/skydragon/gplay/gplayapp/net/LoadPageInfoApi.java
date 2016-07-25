package com.skydragon.gplay.gplayapp.net;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/15.
 */
public class LoadPageInfoApi {
    private String murl;
    private HttpURLConnection httpURLConnection;
    private JSONObject dataJson;
    private InputStream inputStream;
    private String dataString;

    public LoadPageInfoApi(String url) {
        this.murl = url;
    }

    public String getData() {

        return netGetData(murl);
    }

    private String netGetData(final String murl){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(murl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    int code = httpURLConnection.getResponseCode();
                    if (code==200) {
                        inputStream=httpURLConnection.getInputStream();
                        byte[] bytes =isToByteArray(inputStream);
                        dataString=new String(bytes);
                        Log.d("dataString","dataString:"+dataString);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    httpURLConnection.disconnect();
                }
            }
        }.start();

        return dataString;
    }


    private byte[] isToByteArray(InputStream inputStream) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte buffer[]=new byte[1024];
        int length=0;
        try {
            while ((length=inputStream.read(buffer))!=-1) {
                baos.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.flush();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }
}
