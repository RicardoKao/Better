package aiinno.com.better.ui;

/**
 * Created by lbk on 2016/10/30.
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import aiinno.com.better.ui.PageFragment;
import layout.HomeFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"首页","活动","广场"};
    private Context context;
    private  Drawable[] icons = new Drawable[3];

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
        Drawable icon_home = new IconicsDrawable(this.context)
                .icon(FontAwesome.Icon.faw_home)
                .color(Color.BLACK)
                .sizeDp(24);
        Drawable icon_plan = new IconicsDrawable(this.context)
                .icon(FontAwesome.Icon.faw_calendar)
                .color(Color.BLACK)
                .sizeDp(24);
        Drawable icon_found = new IconicsDrawable(this.context)
                .icon(FontAwesome.Icon.faw_compass)
                .color(Color.BLACK)
                .sizeDp(24);
        icons[0] = icon_home;
        icons[1] = icon_plan;
        icons[2] = icon_found;
    }

    @Override
    public Fragment getItem(int position) {
        //return PageFragment.newInstance(position + 1);

        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SquareFragment();
            case 2:
                return new HomeFragment();
        }
        return new HomeFragment();

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        //Drawable image = context.getResources().getDrawable(imageResId[position]);

        //image1.setBounds(0, 0, image1.getIntrinsicWidth(), image1.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" " +"\n"+ tabTitles[position]);
        ImageSpan imageSpan = new ImageSpan(icons[position], ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
