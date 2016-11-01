package aiinno.com.better.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.SwipeRefreshLayout;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/11/1.
 */
public class SquareFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout swipeLayout;
    private MaterialListView squareHeaderListView;

    public SquareFragment(){

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
        View view = inflater.inflate(R.layout.fragment_square, container, false);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.square_swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));

        Card card = new Card.Builder(getActivity())
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setDrawable(R.drawable.background)
                .endConfig()
                .build();

        squareHeaderListView = (MaterialListView) view.findViewById(R.id.square_header_viewlist);
        squareHeaderListView.getAdapter().addAll(card);
        return view;
    }
}
