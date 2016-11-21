package aiinno.com.better.card;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import aiinno.com.better.R;
import aiinno.com.better.ui.Checkin;

/**
 * Created by lbk on 2016/11/21.
 */

public class QuestionProvider extends CardProvider<ListCardProvider> {
    @Nullable
    private String content;

    @Nullable
    private int ans;

    @Nullable
    private ArrayList<String> options;

    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.questions_list_item);
    }

    public QuestionProvider  setContetn(String content){
        this.content = content;
        notifyDataSetChanged();
        return this;
    }


    public QuestionProvider setOptions(ArrayList<String>  options) {
        this.options = options;
        notifyDataSetChanged();
        return this;
    }

    public QuestionProvider setAnd(int ans){
        this.ans = ans;
        notifyDataSetChanged();
        return this;
    }


    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);
        /*
        ArrayList<EditText>
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
        */
    }
}
