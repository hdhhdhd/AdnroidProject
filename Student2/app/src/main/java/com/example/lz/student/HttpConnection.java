package com.example.lz.student;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/6/14.
 */
public class HttpConnection  {
    /**
     * HttpUrlConnection GET请求方式
     */
    public static String getDataByHttpGet(String path){
        String result=null;
        StringBuffer sb=new StringBuffer();
        try {
            URL url=new URL(path);//建立一个URL
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();//通过通道打开连接
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if(connection.getResponseCode()==200){//获得返回码 200代表成功
                InputStream in=connection.getInputStream();//连接获得流

                BufferedInputStream bis=new BufferedInputStream(in);
                byte[]b =new byte[1024];
                int len=0;
                while((len=bis.read(b))!=-1){
                    sb.append(new String(b,0,len));
                }
                result=sb.toString();
            }
         } catch (MalformedURLException e) {
        e.printStackTrace();
         } catch (UnknownHostException e){
            result="连接超时";
          }
        catch (IOException e) {
        e.printStackTrace();
    }
        return result;
    }
    /**
     * Http  Post 请求方式
     */
    public static String getDataByHttpPost(String path,String menuname){
        String result=null;
        StringBuffer sb=new StringBuffer();
        try {
            URL url=new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            String data="key=179acad6a13d1ae6284c6294c3b3ac8f"+"&menu="+ URLEncoder.encode(menuname,"utf-8");
            byte []b=data.getBytes();
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",String.valueOf(b.length));
            OutputStream out=connection.getOutputStream();
            out.write(b);//把数据发送过去
            if(connection.getResponseCode()==200){//获得返回码 200代表成功
                InputStream in=connection.getInputStream();//连接获得流

                BufferedInputStream bis=new BufferedInputStream(in);
                byte[]b1 =new byte[1024];
                int len=0;
                while((len=bis.read(b1))!=-1){
                    sb.append(new String(b1,0,len));
                }
                result=sb.toString();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
        return result;
    }

    //Client  GET
    public static String getDataClinetGet(String path){
        String result=null;
        HttpClient httpClient=new DefaultHttpClient();//多态转型
        HttpGet get=new HttpGet(path);//开启get通道
        HttpResponse response=null;//返回实体
        try {
            response=httpClient.execute(get);//制定连接方式
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity=response.getEntity();
                result= EntityUtils.toString(response.getEntity());
                return  result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    //Client Post
    public static String getDataClientPost(String path){
        String reuslt=null;
        HttpClient httpClient=new DefaultHttpClient();//多态转型
        HttpPost httpPost=new HttpPost(path);
        HttpResponse response=null;
        String result=null;
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("key","179acad6a13d1ae6284c6294c3b3ac8f"));
        list.add(new BasicNameValuePair("menu","家常菜"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            response=httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity=response.getEntity();
                result= EntityUtils.toString(response.getEntity());
                return  result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

}
