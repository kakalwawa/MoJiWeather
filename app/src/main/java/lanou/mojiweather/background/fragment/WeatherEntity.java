package lanou.mojiweather.background.fragment;

/**
 * Created by dllo on 16/9/29.
 */
public class WeatherEntity {
    String city;
    String date;
    String temperature;
    String direction;
    String power;
    String status;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("城市" + city + "\r\n");
        builder.append("日期:" + date + "\r\n");
        builder.append("天气状况:" + status + "\r\n");
        builder.append("温度:" + temperature + "\r\n");
        builder.append("风向:" + direction + "\r\n");
        builder.append("风力:" + power + "\r\n");
        return builder.toString();
    }
}
