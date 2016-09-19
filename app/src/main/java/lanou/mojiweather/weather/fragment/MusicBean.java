package lanou.mojiweather.weather.fragment;

/**
 * Created by 高翔 on 16/9/19.
 */
public class MusicBean {
    private   String name ;
    private   String path ;
    private   int size ;
    private   int duration ;

    public MusicBean() {
    }

    public MusicBean(String name, String path, int size, int duration) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public int getDuration() {
        return duration;
    }
}
