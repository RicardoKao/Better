package aiinno.com.better.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import aiinno.com.better.R;

/**
 * Created by Administrator on 2015/7/30.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments() != null ? getArguments().getInt(ARG_PAGE) : 1;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        View textView = view.findViewById(R.id.text);
        ((TextView)textView).setText("Fragment #" + mPage);
        return view;
    }

}