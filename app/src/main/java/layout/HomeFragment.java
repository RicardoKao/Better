package layout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import aiinno.com.better.R;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aiinno.com.better.card.PlanProvider;
import aiinno.com.better.card.UserPlanProvider;
import aiinno.com.better.model.Plan;
import aiinno.com.better.model.Ret;
import aiinno.com.better.service.PlanService;
import aiinno.com.better.service.SignService;
import aiinno.com.better.ui.BaseActivity;
import aiinno.com.better.ui.LoginActivity;
import android.content.SharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeLayout;
    private MaterialListView mListView;
    private MaterialListView mListView2;
    GetPlansTask mSignTask;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));
        //return inflater.inflate(R.layout.fragment_home, container, false);

        Card card = new Card.Builder(getActivity())
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_basic_buttons_card)
                .setTitle("Card number 4")
                .setDescription("Lorem ipsum dolor sit amet")
                .addAction(R.id.left_text_button, new TextViewAction(getActivity())
                        .setText("Izquierda")
                        .setTextResourceColor(R.color.black_button)
                        .setListener(new OnActionClickListener() {
                            @Override
                            public void onActionClicked(View view, Card card) {
                                Toast.makeText(getActivity(), "You have pressed the left button", Toast.LENGTH_SHORT).show();
                            }
                        }))
                .addAction(R.id.right_text_button, new TextViewAction(getActivity())
                        .setText("Derecha")
                        .setTextResourceColor(R.color.accent_material_dark)
                        .setListener(new OnActionClickListener() {
                            @Override
                            public void onActionClicked(View view, Card card) {
                                Toast.makeText(getActivity(), "You have pressed the right button", Toast.LENGTH_SHORT).show();
                            }
                        }))
                .endConfig()
                .build();

        mListView = (MaterialListView) view.findViewById(R.id.material_listview);
        mListView.getAdapter().addAll(initUserProfile());

        List<Card> cards = new ArrayList<>();
        cards.add(initUserPlan());
        cards.add(initUserPlan());

        mListView2 = (MaterialListView) view.findViewById(R.id.material_listview2);
        mListView2.getAdapter().addAll(cards);

        Drawable icon_checkin = new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_calendar)
                .color(Color.BLACK)
                .sizeDp(24);

        ImageView icon_user_checkin = (ImageView)  view.findViewById(R.id.user_checkin_icon);
        icon_user_checkin.setImageDrawable(icon_checkin);

        /*
        int myWidth = 512;
        int myHeight = 384;
        mListView2 =(MaterialListView) view.findViewById(R.id.user_profile_header);
        Glide.with(getActivity())
                .load("http://ofi8akpq7.bkt.clouddn.com/sys/background.png")
                .asBitmap()
                .into(
                        new SimpleTarget<Bitmap>(myWidth, myHeight) {
                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                // Do something with bitmap here.
                                Drawable bitmapd =new BitmapDrawable(bitmap);
                                //Log.d("bigimgab",bitmapd.toString());
                                Card bgcard = new Card.Builder(getActivity())
                                        .setTag("BIG_IMAGE_CARD")
                                        .withProvider(new CardProvider())
                                        .setLayout(R.layout.material_big_image_card_layout)
                                        .setTitle("Card number 5")
                                        .setDescription("Lorem ipsum dolor sit amet")
                                        .setDrawable(bitmapd)
                                        .endConfig()
                                        .build();
                                mListView2.getAdapter().addAll(bgcard);
                            }
                        }
                );
        */
        mSignTask = new GetPlansTask();
        mSignTask.execute((Void) null);
        return view;
    }
    @Override
    public void onRefresh() {
        Log.d("refresh","refreshing");
    }

    private Card initUserProfile(){
        Card card = new Card.Builder(getActivity())
                .withProvider(new PlanProvider())
                .setText("等级LV 20")
                .setImg(R.drawable.alex)
                .setUserName("Alex")
                .setExp(80)
                .endConfig()
                .build();
        return card;
    }

    private Card initUserPlan(){
        Card card = new Card.Builder(getActivity())
                .withProvider(new UserPlanProvider())
                .setPlanTitle("每天6：00起床")
                .setImg(R.drawable.alarmclock)
                .setDay(10)
                .setCDay(3)
                .setFee(50)
                .endConfig()
                .build();
        /*
        Card card = new Card.Builder(getActivity())
                .withProvider(new UserPlanProvider())
                .setPlanTitle("每天早上跑步")
                .setImg(R.drawable.running)
                .setDay(10)
                .setCDay(5)
                .setFee(50)
                .endConfig()
                .build();
         */
        return card;
    }

    private List<Card> getUserPlans()
    {
        List<Card> cards = new ArrayList<>();
        return cards;
    }

    public class GetPlansTask extends AsyncTask<Void, Void, ArrayList<Plan>> {

        @Override
        protected ArrayList<Plan> doInBackground(Void... params) {
            SharedPreferences preference = getActivity().getSharedPreferences("person",Context.MODE_PRIVATE);
            String email = preference.getString("Email","");
            String passwd = preference.getString("Passwd", "");
            try {
                SignService s = new SignService();
                Ret authinfo = s.Login(email,passwd,"email","android");
                PlanService _planss = new PlanService();
                ArrayList<Plan> _plans = _planss.GetPlan(authinfo.data);
                return _plans;
            }  catch(IOException e){
                Log.d("ex",e.toString());

                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Plan> plans) {
            List<Card> cards = new ArrayList<>();
            if (plans!=null) {
                //finish();
                for (Plan j : plans) {
                    Card card = new Card.Builder(getActivity())
                            .withProvider(new UserPlanProvider())
                            .setPlanTitle(j.getTitle())
                            .setImg(R.drawable.alarmclock)
                            .setDay(j.getDays())
                            .setCDay(j.getComplated_days())
                            .setFee(j.getFee())
                            .endConfig()
                            .build();
                    cards.add(card);
                }
                mListView2.getAdapter().addAll(cards);
            } else {

            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
