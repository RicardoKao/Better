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

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/11/1.
 */
public class SquarePlanProvider extends CardProvider<ListCardProvider> {
    @Nullable
    String planname;
    @Nullable
    String plandes;
    @Nullable
    String plansigndes;
    @Nullable
    private int img;
    @Nullable

    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.square_plan_item);
    }

    public SquarePlanProvider  setPlanname(String text){
        this.planname = text;
        notifyDataSetChanged();
        return this;
    }


    public SquarePlanProvider setImg(final int img) {
        this.img = img;
        notifyDataSetChanged();
        return this;
    }

    public SquarePlanProvider setPlandes(String plandes){
        this.plandes = plandes;
        notifyDataSetChanged();
        return this;
    }

    public SquarePlanProvider setPlansigndes(String plansigndes){
        this.plansigndes = plansigndes;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);

        ImageView imageview = (ImageView)view.findViewById(R.id.square_plan_image);
        imageview.setImageResource(img);

        TextView plan_nameview = (TextView)view.findViewById(R.id.square_plan_name);
        plan_nameview.setText(planname);
        TextView plandesview = (TextView)view.findViewById(R.id.square_plan_des);
        plandesview.setText(plandes);
        TextView plansigndesview = (TextView)view.findViewById(R.id.square_plan_sign_des);
        plansigndesview.setText(plansigndes);
    }
}