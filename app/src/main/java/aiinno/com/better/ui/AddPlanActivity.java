package aiinno.com.better.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/11/3.
 */
public class AddPlanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.betteraddplanactionbar);
        setSupportActionBar(toolbar);
        ImageButton imageButton = (ImageButton) findViewById(R.id.add_plan_return_image_button);
        Drawable icon_return = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_times)
                .color(Color.BLACK)
                .backgroundColor(00000000)
                .sizeDp(24);
        imageButton.setImageDrawable(icon_return);
        getSupportActionBar().setTitle("");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(AddPlanActivity.this, BaseActivity.class);
                AddPlanActivity.this.startActivity(intent);
                AddPlanActivity.this.finish();
            }
        });

    }
}
