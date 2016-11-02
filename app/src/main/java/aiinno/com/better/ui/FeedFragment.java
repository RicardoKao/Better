package aiinno.com.better.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import aiinno.com.better.R;
import aiinno.com.better.card.FeedProvider;
import aiinno.com.better.card.SquarePlanProvider;

/**
 * Created by lbk on 2016/11/2.
 */
public class FeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout swipeLayout;
    private MaterialListView squareHeaderListView;
    private MaterialListView plansquareitemlistview;

    public FeedFragment(){

    }


    @Override
    public void onRefresh() {
        Log.d("refresh","refreshing");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.feed_swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));

        Card card2 = new Card.Builder(getActivity())
                .withProvider(new FeedProvider())
                .setUsername("xiaotingv6")
                .setPlanname("一起跑步")
                .setAvatar(R.drawable.alex)
                .setPlanfeedimg(R.drawable.running)
                .setPlafeeddes("好好学习，天天向上")
                .setTimedes("2016.1.1")
                .endConfig()
                .build();

        List<Card> cards = new ArrayList<>();
        cards.add(card2);
        cards.add(card2);

        squareHeaderListView = (MaterialListView) view.findViewById(R.id.feed_plans);
        squareHeaderListView.getAdapter().addAll(cards);
        return view;
    }
}
