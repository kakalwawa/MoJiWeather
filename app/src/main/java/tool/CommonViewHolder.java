package tool;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 高翔 on 16/9/13.
 * yigetongyongdeViewHolder
 * 适用于所有的adapter
 */
public class CommonViewHolder {
    /**
     * SparseArray可以看成是一个key值是int类型的Hashmap
     * 它是Android特有的,它的效率要比HashMap高
     */
    private SparseArray<View> views ;
    private View convertView ; //行布局
    //返回航布局
    public  View getConvertView (){
        return  convertView ;
    }
    public static CommonViewHolder getHolder(View convertView , LayoutInflater inflater , int id , ViewGroup parent ){
        CommonViewHolder viewHolder ;
        if (convertView == null){
            View view = inflater.inflate(id , parent ,false);
            viewHolder = new CommonViewHolder(view);
        }else {
            viewHolder = (CommonViewHolder) convertView.getTag();
        }
        return viewHolder ;

    }
    public CommonViewHolder(View convertView ) {
        views = new SparseArray<>();
        this.convertView = convertView ;
        this.convertView.setTag(this);
    }

    /**
     * 通过id来获得行布局里指定的View的方法
     * @param id
     * @return 该id所对应的view
     */
    public <T extends View> T getView (int id){
        View view = views.get(id);
        if (view == null){
            //TODO 执行fingdviewbyId 找到这个组件放到views里
            view = convertView.findViewById(id);
            //然后放到views里
            views.put(id, view);
        }
        return (T) view;
    }
    //设置文字
    public void setTex (int id , String text){
        TextView textView = getView(id);
        textView.setText(text);
    }
}
