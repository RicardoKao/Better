package aiinno.com.better.card;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.rey.material.widget.ProgressView;

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/10/31.
 */
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.rey.material.widget.ProgressView;
//import android.widget.ProgressBar;

import aiinno.com.better.R;
import aiinno.com.better.ui.BaseActivity;
import aiinno.com.better.ui.Checkin;
import aiinno.com.better.ui.LoginActivity;

public class UserPlanProvider extends CardProvider<ListCardProvider> {
    @Nullable
    String ptitle;
    @Nullable
    private int img;
    @Nullable
    private int cday;

    int fee;
    int day;



    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.user_join_plan);
    }

    public UserPlanProvider  setPlanTitle(String ptitle){
        this.ptitle = ptitle;
        notifyDataSetChanged();
        return this;
    }


    public UserPlanProvider setImg(final int img) {
        this.img = img;
        notifyDataSetChanged();
        return this;
    }

    public UserPlanProvider setUserName(int cday){
        this.cday = cday;
        notifyDataSetChanged();
        return this;
    }

    public UserPlanProvider setFee(int fee){
        this.fee = fee;
        notifyDataSetChanged();
        return this;
    }

    public  UserPlanProvider setDay(int day){
        this.day = day;
        notifyDataSetChanged();
        return this;
    }

    public  UserPlanProvider setCDay(int cday){
        this.cday = cday;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);
        TextView textView = (TextView)view.findViewById(R.id.user_join_plan_title);
        textView.setText(ptitle);
        ImageView imageview = (ImageView)view.findViewById(R.id.user_join_plan_img);
        imageview.setImageResource(img);
        ProgressView pv = (ProgressView)view.findViewById(R.id.user_join_plan_progress);
        pv.setProgress((float)(cday/(float)day));
        TextView des = (TextView)view.findViewById(R.id.user_join_plan_des);
        des.setText("已完成："+ cday + "/" + day + " " + "押金：" + fee);
        Button cb = (Button)view.findViewById(R.id.plan_checkin_button);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Checkin.class);
                getContext().startActivity(intent);
            }
        });
        //pv.stop();
    }
}
