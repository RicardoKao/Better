package layout;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import android.graphics.Bitmap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeLayout;
    private MaterialListView mListView;
    private MaterialListView mListView2;


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
        mListView.getAdapter().addAll(card);
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
        return view;
    }
    @Override
    public void onRefresh() {
        Log.d("refresh","refreshing");
    }
}
