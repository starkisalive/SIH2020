package com.test.datafetchmultiple.mData;

/**
 */
public class TVShow {

    String name;
    String image;
    String desc;
    String lat;
    String lon;
    String date;
    String id;

    public TVShow(String name, String image,String desc, String lat, String lon, String date,String id) {
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }
    public String getId() {
        return id;
    }
    public String getLat() {
        return lat;
    }
    public String getLon() {
        return lon;
    }
    public String getDate() {
        return date;
    }
}
