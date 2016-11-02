package aiinno.com.better.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import aiinno.com.better.R;

public class BaseActivity extends AppCompatActivity {
    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        Toolbar toolbar = (Toolbar) findViewById(R.id.betteractionbar);
        setSupportActionBar(toolbar);
        ImageButton imageButton = (ImageButton) findViewById(R.id.image_button);
        Drawable icon_plus = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_plus)
                .color(Color.BLACK)
                .backgroundColor(00000000)
                .sizeDp(24);
        imageButton.setImageDrawable(icon_plus);
        getSupportActionBar().setTitle("");
        imageButton.setImageDrawable(icon_plus);
        getSupportActionBar().setTitle("");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BaseActivity.this, AddPlanActivity.class);
                BaseActivity.this.startActivity(intent);
                BaseActivity.this.finish();
            }
        });
    }
}
