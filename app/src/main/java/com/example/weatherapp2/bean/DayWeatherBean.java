package com.example.weatherapp2.bean;

import com.google.gson.annotations.SerializedName;

//代表每一天的天气 date
/*
{
        "nums":5,
        "cityid":"101280601",
        "city":"深圳",
        "update_time":"2023-10-11 08:38:26",
        "data":[{
        "date":"2023-10-11",
        "wea":"阴",
        "wea_img":"yin",
        "tem_day":"28",
        "tem_night":"23",
        "win":"无持续风向",
        "win_speed":"<3级"
        },]
}
*/
public class DayWeatherBean {
    private String date;
    private String wea;
    @SerializedName("tem_day")
    private String temDay;
    @SerializedName("tem_night")
    private String temNight;
    private String win;
    @SerializedName("win_speed")
    private String winSpeed;
    @SerializedName("wea_img")
    private String weaImg;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getTemDay() {
        return temDay;
    }

    public void setTemDay(String temDay) {
        this.temDay = temDay;
    }

    public String getTemNight() {
        return temNight;
    }

    public void setTemNight(String temNight) {
        this.temNight = temNight;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "date='" + date + '\'' +
                ", wea='" + wea + '\'' +
                ", temDay='" + temDay + '\'' +
                ", temNight='" + temNight + '\'' +
                ", win='" + win + '\'' +
                ", winSpeed='" + winSpeed + '\'' +
                ", weaImg='" + weaImg + '\'' +
                '}';
    }
}

