package com.example.weatherapp2.bean;
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

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {
    @SerializedName("cityid")
    private String cityID;
    private String city;
    @SerializedName("update_time")
    private String updateTime;
    //    使用列表接收data变量里面的数据
    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DayWeatherBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatherBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "cityID='" + cityID + '\'' +
                ", city='" + city + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
