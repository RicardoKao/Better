package aiinno.com.better.card;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.rey.material.widget.ProgressView;
//import android.widget.ProgressBar;

import aiinno.com.better.R;

public class PlanProvider extends CardProvider<ListCardProvider> {
    @Nullable
    String text;
    @Nullable
    private int img;
    @Nullable
    private String username;
    @Nullable int exp;
    private int gold;



    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.user_profile_header);
    }

    public PlanProvider  setText(String text){
        this.text = text;
        notifyDataSetChanged();
        return this;
    }


    public PlanProvider setImg(final int img) {
        this.img = img;
        notifyDataSetChanged();
        return this;
    }

    public PlanProvider setUserName(String name){
        this.username = name;
        notifyDataSetChanged();
        return this;
    }

    public PlanProvider setExp(int exp){
        this.exp = exp;
        notifyDataSetChanged();
        return this;
    }

    public PlanProvider setGold(int gold){
        this.gold = gold;
        notifyDataSetChanged();
        return this;
    }

    public void addGoldNum(int gold){
        this.gold += gold;
    }

    public void addExp(int exp){
        this.exp += exp;
    }







    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);
        TextView textView = (TextView)view.findViewById(R.id.user_profile_header_grade);
        textView.setText(text);
        ImageView imageview = (ImageView)view.findViewById(R.id.user_profile_header_avatar);
        imageview.setImageResource(img);
        TextView usernametextview = (TextView)view.findViewById(R.id.user_profile_header_name);
        usernametextview.setText(username);
        TextView goldView = (TextView) view.findViewById(R.id.user_profile_header_gold);
        goldView.setText("金币："+ gold);
        ProgressBar pv = (ProgressBar)view.findViewById(R.id.progressBar_exp);
        pv.setProgress(exp);
        pv.setVisibility(View.VISIBLE);
    }
}