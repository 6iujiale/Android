package com.example.weatherapp2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp2.R;
import com.example.weatherapp2.bean.DayWeatherBean;
import com.example.weatherapp2.bean.WeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.MyViewHolder> {
    private Context context;
    private List<DayWeatherBean> dayWeatherBeanList;

    public FutureWeatherAdapter(Context context, List<DayWeatherBean> dayWeatherBeanList) {
        this.context = context;
        this.dayWeatherBeanList = dayWeatherBeanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.weather_items_layout, null);//参数：上下文对象,布局,null
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定数据
        DayWeatherBean dayWeatherBean=dayWeatherBeanList.get(position);
        WeatherBean weatherBean=new WeatherBean();
        holder.tvTem.setText(dayWeatherBean.getTemDay()+"°C");
        holder.tvTime.setText(dayWeatherBean.getDate());
        holder.tvWeather.setText(dayWeatherBean.getWea());
        holder.tvTemLowHigh.setText(dayWeatherBean.getTemDay() + "°C~" + dayWeatherBean.getTemNight() + "°C");
        holder.tvWin.setText(dayWeatherBean.getWin()+dayWeatherBean.getWinSpeed());
        holder.ivWeather.setImageResource(getImgResOfWeather(dayWeatherBean.getWeaImg()));
    }

    @Override
    public int getItemCount() {
        return dayWeatherBeanList==null?0:dayWeatherBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTem, tvWeather, tvTemLowHigh, tvWin,tvTime;
        ImageView ivWeather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWeather = itemView.findViewById(R.id.iv_weather);
            tvTem =  itemView.findViewById(R.id.tv_tem);
            tvWeather =  itemView.findViewById(R.id.tv_weather);
            tvTemLowHigh =  itemView.findViewById(R.id.tv_tem_low_high);
            tvWin =  itemView.findViewById(R.id.tv_win);
            tvTime=itemView.findViewById(R.id.tv_time);
        }
    }
    private int getImgResOfWeather(String weaImg) {
//        xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaImg) {
            case "xue":
                result = R.drawable.xue;
                break;
            case "lei":
                result = R.drawable.lei;
                break;
            case "shachen":
                result = R.drawable.shachen;
                break;
            case "wu":
                result = R.drawable.wu;
                break;
            case "bingbao":
                result = R.drawable.bingbao;
                break;
            case "yun":
                result = R.drawable.yun;
                break;
            case "yu":
                result = R.drawable.yu;
                break;
            case "yin":
                result = R.drawable.yintian;
                break;
            case "qing":
                result = R.drawable.qing;
                break;
            default:
                break;
        }
        return result;
    }
}
