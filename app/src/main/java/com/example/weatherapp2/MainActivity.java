package com.example.weatherapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.FeatureGroupInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp2.adapter.FutureWeatherAdapter;
import com.example.weatherapp2.bean.DayWeatherBean;
import com.example.weatherapp2.bean.WeatherBean;
import com.example.weatherapp2.util.WeatherUtil;
import com.google.gson.Gson;

import java.util.List;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;
    private ImageView ivWeather;
    private TextView tvTem, tvWeather, tvTemLowHigh, tvWin;
    private RecyclerView rlvFutureWeather;
    private final String TAG = "tag";
    private FutureWeatherAdapter mFutureWeatherAdapter;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;
//                Toast.makeText(MainActivity.this, "主线程收到天气数据", Toast.LENGTH_SHORT).show();
                Log.d(TAG, weather);
                //解析
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                Log.d(TAG, "weatherBean数据：" + weatherBean.toString());
                updateUiOfWeather(weatherBean);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCities = getResources().getStringArray(R.array.cities);
        mSpinner = findViewById(R.id.sp_city);
        //关联Spinner自定义布局，使用适配器关联
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);//参数：上下文，布局，数据
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = mCities[i];
                Log.d(TAG, city);
                getWeatherOfCity(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ivWeather = findViewById(R.id.iv_weather);
        tvTem = findViewById(R.id.tv_tem);
        tvWeather = findViewById(R.id.tv_weather);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);
        //未来天气
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
    }

    private void getWeatherOfCity(String city) {
        //开启子线程请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                String weatherOfCity = WeatherUtil.getWeatherOfCity(city);
                Message message = Message.obtain();//效率更高 从消息池中获取
                message.what = 0;
                message.obj = weatherOfCity;
                handler.sendMessage(message);
            }
        }).start();
    }


    private void updateUiOfWeather(WeatherBean weatherBean) {
        //添加空判断，避免空异常
        if (weatherBean == null) {
            return;
        }
        //[DateWeatherBean{date='2023-10-11', wea='多云', temDay='29', temNight='23', win='无持续风向', winSpeed='<3级'},
        //DateWeatherBean{date='2023-10-12', wea='晴', temDay='30', temNight='23', win='无持续风向', winSpeed='<3级'},
        List<DayWeatherBean> dayWeatherBeans = weatherBean.getDayWeathers();
        DayWeatherBean todayWeather = dayWeatherBeans.get(0);//获取第一天的天气
        //判断todayWeather是否为空,避免空指针异常
        if (todayWeather == null) {
            return;
        }
        tvTem.setText(todayWeather.getTemDay() + "°C");
        tvWeather.setText(todayWeather.getWea() + "(" + weatherBean.getUpdateTime() + ")");
        tvTemLowHigh.setText(todayWeather.getTemDay() + "°C~" + todayWeather.getTemNight() + "°C");
        tvWin.setText(todayWeather.getWin() + todayWeather.getWinSpeed());
        ivWeather.setImageResource(getImgResOfWeather(todayWeather.getWeaImg()));
        dayWeatherBeans.remove(0);//删除第一天天气
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mFutureWeatherAdapter=new FutureWeatherAdapter(this,dayWeatherBeans);
        rlvFutureWeather.setLayoutManager(layoutManager);
        rlvFutureWeather.setAdapter(mFutureWeatherAdapter);
    }

    //获取天气对应的图片
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

    ;

}