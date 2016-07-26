package com.example.lz.student;

import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lz on 2016/6/16.
 */
public class Jason {
    /** JSON 解析
    */
    public void JXJSON(String str){
        JSONObject jo=null;
        try {
            jo=new JSONObject(str);//转换成json对象

            int code=jo.getInt("resultcode");
            switch (code){
                case 200:
                    //执行解析操作
                    JSONObject joresult=jo.getJSONObject("result");
//                    joresult.getString("")
                    JSONArray dataarray=joresult.getJSONArray("data");
                    for (int i=0;i<dataarray.length();i++){
                        JSONObject joarray1= dataarray.getJSONObject(i);
                    }
                    break;
//                case 201:
//                    handler.sendEmptyMessage(1);
//                    break;
//                case 202:
//                    handler.sendEmptyMessage(2);
//                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //XML  PULL解析
    void jxDataByXml(){
        InputStream in = getResources().openRawResource(R.raw.student);
        XmlPullParser parser= Xml.newPullParser();//内部类 实例化
        try {

            parser.setInput(in,"utf-8");//设置解析源
            int eventType=parser.getEventType();//获得根节点
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String tagname = parser.getName();//获得标签名
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT://文档开始

                        break;
                    case XmlPullParser.START_TAG://开始标签
                        if (tagname.equals("class")) {
                            Log.i("msg","id+"+parser.getAttributeValue(0)+"num"+parser.getAttributeValue(1));


                        } else if (tagname.equals("name")) {
                            Log.i("msg","name"+parser.nextText());// 获得下一条text
                        } else if (tagname.equals("sex")) {
                            Log.i("msg", "sex" + parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG://标签结束
                        break;
                }
                eventType=parser.next();//移动到下一个根节点
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            Bitmap  bitmap=Bitmap.createBitmap()
//        Bitmap bitmap = BitmapFactory.decodeStream();
//        ImageView  iv=new ImageView();
//        iv.setImageBitmap();
    }

}
