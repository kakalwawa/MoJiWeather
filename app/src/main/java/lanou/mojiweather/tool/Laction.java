package lanou.mojiweather.tool;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 高翔 on 16/9/28.
 */
@Entity
public class Laction {
    @Id(autoincrement = true)
    private  Long id ;
    private  String name ;
    @Generated(hash = 1498290632)
    public Laction(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 2013398846)
    public Laction() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

