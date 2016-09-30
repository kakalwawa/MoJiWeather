package lanou.mojiweather;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import lanou.mojiweather.tool.BaseActivity;
import lanou.mojiweather.view.MyListView;
import lanou.mojiweather.view.PullDownScrollView;
import lanou.mojiweather.view.RecyclableImageView;

/**
 * 磨磨唧唧视频站
 * Created by shiyujia on 16/9/28.
 */
public class VideoAtv extends BaseActivity {
    private static final String TAG = "TAG_VideoAtv";
    private RecyclerView moreReView,firstReView,secondReView;
    private MyListView listView;
    private TextView moreSelectionTV,latestTV,moreAuthorTV,firstLatestTitleTV,firstLatestDescriptionTV,firstLatestVideoCountTV,secondLatestTitleTV,secondLatestDescriptionTV,secondLatestVideoCountTV;
    private ImageView moreIV,firstAuthorIcon,secondAuthorIcon,anmiInIV;
    private PullDownScrollView mPullDownScrollView;
    private RelativeLayout beforeSelectRelayout,animRe;
    private ScrollView scrollView;
    private int textItem=0,textItemPos=0;

    @Override
    protected int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        moreReView = (RecyclerView) findViewById(R.id.selection_re);
        listView = (MyListView) findViewById(R.id.selection_ls);
        mPullDownScrollView = (PullDownScrollView) findViewById(R.id.refresh_scrollview);
        moreSelectionTV = (TextView) findViewById(R.id.look_for_more_selection_tv);
        moreIV = (RecyclableImageView) findViewById(R.id.selection_more_iv);
        latestTV = (TextView) findViewById(R.id.latest_tv);
        moreAuthorTV = (TextView) findViewById(R.id.look_for_more_author_tv);
        beforeSelectRelayout = (RelativeLayout) findViewById(R.id.look_before_selection_relayout);

    }

    @Override
    protected void initDate() {

    }
}
