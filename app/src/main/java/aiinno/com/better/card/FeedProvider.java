package aiinno.com.better.card;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.rey.material.widget.ProgressView;
import android.view.ViewGroup.LayoutParams;

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/11/2.
 */
public class FeedProvider  extends CardProvider<ListCardProvider> {


    public FeedProvider setUsername(@Nullable String username) {
        this.username = username;
        notifyDataSetChanged();
        return this;
    }

    @Nullable
    private String username;

    public FeedProvider setPlanname(@Nullable String planname) {
        this.planname = planname;
        notifyDataSetChanged();
        return this;
    }

    public FeedProvider setPlafeeddes(@Nullable String plafeeddes) {
        this.plafeeddes = plafeeddes;
        notifyDataSetChanged();
        return this;
    }

    public FeedProvider setPlanfeedimg(@Nullable int planfeedimg) {
        this.planfeedimg = planfeedimg;
        notifyDataSetChanged();
        return this;
    }

    public FeedProvider setTimedes(@Nullable String timedes) {
        this.timedes = timedes;
        notifyDataSetChanged();
        return this;
    }
    public FeedProvider setAvatar(@Nullable int avatarimg)
    {
        this.avatarimg = avatarimg;
        notifyDataSetChanged();
        return this;
    }
    @Nullable
    String planname;
    @Nullable
    String plafeeddes;
    @Nullable
    int planfeedimg;
    @Nullable
    String timedes;
    @Nullable
    private int avatarimg;
    @Nullable



    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.user_feed_item);
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);

        ImageView user_feed_plan_avatar_view = (ImageView)view.findViewById(R.id.user_feed_plan_avatar_img);
        user_feed_plan_avatar_view.setImageResource(avatarimg);
        LayoutParams para;
        para = user_feed_plan_avatar_view.getLayoutParams();

        para.height = 128;
        para.width = 128;
        user_feed_plan_avatar_view.setLayoutParams(para);

        TextView usernameview = (TextView)view.findViewById(R.id.user_feed_plan_username);
        usernameview.setText(username);

        TextView plannameview = (TextView)view.findViewById(R.id.user_feed_plan_planname);
        plannameview.setText(planname);

        TextView plandesview = (TextView)view.findViewById(R.id.user_feed_plan_feeddes);
        plandesview.setText(plafeeddes);

        ImageView planimageview = (ImageView)view.findViewById(R.id.user_feed_plan_img);
        planimageview.setImageResource(planfeedimg);

        TextView feedplamview = (TextView)view.findViewById(R.id.user_feed_plan_timedes);
        feedplamview.setText(timedes);
    }
}
