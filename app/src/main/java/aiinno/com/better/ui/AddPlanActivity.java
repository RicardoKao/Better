package aiinno.com.better.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import aiinno.com.better.R;

/**
 * Created by lbk on 2016/11/3.
 */
public class AddPlanActivity extends AppCompatActivity {
    private ListView lv;// 适配器控件------->V视图
    private ArrayAdapter<String> adapter;// 适配器------>C控制器
    private String[] data = { "我是第1个列表项", "我是第2个列表项", "我是第3个列表项", "我是第4个列表项",
            "我是第5个列表项", "我是第6个列表项", "我是第7个列表项" };// 数据源-->M
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

        lv = (ListView) findViewById(R.id.textfield_rules);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        // R.layout.cell 自己定义视图
        // android.R.layout.simple_list_item_1 系统定义视图样式
        // 绑定适配器到适配器控件上
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // use position to find your values
                view.setVisibility(view.GONE);


            }
        });


    }
}
