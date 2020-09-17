package com.test.datafetchmultiple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by Shree on 10/25/2016.
 */
public class Getjson {
    public static String[] Image_Url;
    public static Bitmap[] bitmaps;
    public static String[] Android_Name;
    public static final String JSON_ARRAY="result";
    public static final String IMAGEURL = "ImagePath";
    public static final String AndroidName = "near";
    private String json;
    private JSONArray urls;
    public Getjson(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(IMAGEURL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void getAllImages() throws JSONException {
        Android_Name = new String[urls.length()];
        Image_Url = new String[urls.length()];
        bitmaps = new Bitmap[urls.length()];
        for(int i=0;i<urls.length();i++)
        { Android_Name[i]= urls.getJSONObject(i).getString(AndroidName);
            Image_Url[i] = urls.getJSONObject(i).getString(IMAGEURL);
            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);
        }
    }
}
