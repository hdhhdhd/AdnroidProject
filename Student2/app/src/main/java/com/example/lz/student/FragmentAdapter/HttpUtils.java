package com.example.lz.student.FragmentAdapter;

import android.util.Log;

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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 * Created by SIXDOG on 2016/6/14.
 */
public class HttpUtils {

    //HttpUrlConnection连接
    public static String getDataByHttpUrlGET(String path) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(path);
            //新建HTTPRULConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //获取返回值
            if (connection.getResponseCode() == 200) {//200代表连接过程中
                InputStream in = connection.getInputStream();//转换成流
                BufferedInputStream bi = new BufferedInputStream(in);
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = bi.read(b)) != -1) {
                    stringBuilder.append(new String(b, 0, len));
                }
                result = stringBuilder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    //HttpUrlConnectionPost连接
    public static String getDataByHttpUrlPOST(String path,String name) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(path);
            //新建HTTPRULConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);//设置可以输出
            connection.setConnectTimeout(5000);//设置连接超时
            connection.setReadTimeout(5000);//设置请求超时
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);//不能使用缓存
            String data = "key=dbd18736c6fdb41135d96a31d5ac6f56"
                    + "&menu=" + URLEncoder.encode(name, "UTF-8");
            byte[] b1=data.getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//     设置请求的头信息
            connection.setRequestProperty("Content-Length", String.valueOf(b1.length));//设置长度
            OutputStream our=connection.getOutputStream();//得到输出流对象
            our.write(b1);
            //获取返回值
            if (connection.getResponseCode() == 200) {//200代表连接过程中
                InputStream in = connection.getInputStream();//转换成流
                BufferedInputStream bi = new BufferedInputStream(in);
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = bi.read(b)) != -1) {
                    stringBuilder.append(new String(b, 0, len));
                }
                result = stringBuilder.toString();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch( UnknownHostException e){
                return "连接超时";
           } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    //CLIENT get
    public static String getDataByClientGet(String path) {
        String result = null;
        StringBuffer sb = new StringBuffer();
        HttpClient httpClient = new DefaultHttpClient();//23已经废弃 推荐还是使用HTTP协议。但是这个容易上手，如果想用，手动添加
        HttpGet get = new HttpGet(path);
        HttpResponse response = null;
        try {
            response = httpClient.execute(get);
////            可以使用EntytyUtils
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                return result;
            }

//            //也可以使用流
//            InputStream input = response.getEntity().getContent();
//            BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
//            String data = "";
//            if (sb.toString().equalsIgnoreCase("")) {
//                while ((data = br.readLine()) != null) {
//                    sb.append(data);
//
//                }
//                result = sb.toString();
//                return result;
//            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Client POST
     */
    public  static String getDataByClientPost(String path ,String name){
        String reuslt=null;
        StringBuffer sb=new StringBuffer();
        HttpPost post = new HttpPost(path);
        HttpClient client = new DefaultHttpClient();
        // 设置连接超时
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 4);
        // 设置请求超时
        HttpConnectionParams.setSoTimeout(client.getParams(), 4);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        //这里一般是服务器需要
        list.add(new BasicNameValuePair("key",
                "dbd18736c6fdb41135d96a31d5ac6f56"));
        list.add(new BasicNameValuePair("menu",name));
        try {
            post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            try {
                HttpResponse response = client.execute(post);
                if (response.getStatusLine().getStatusCode() == 200) {
                   reuslt = EntityUtils.toString(response.getEntity());
                    return reuslt;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                Log.i("msg","超时");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            Log.i("msg","超时1");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return reuslt;
    }
}