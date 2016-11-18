package aiinno.com.better.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

import aiinno.com.better.util.DateTimePickDialogUtil;
import aiinno.com.better.ItemBean;
import aiinno.com.better.ListAdapter;
import aiinno.com.better.R;
import aiinno.com.better.TestActivity;
import aiinno.com.better.adapter.RulesAdapter;
import aiinno.com.better.model.RuleBean;

/**
 * Created by lbk on 2016/11/3.
 */
public class AddPlanActivity extends AppCompatActivity {
    private ListView lv;// 适配器控件------->V视图
    private ArrayAdapter<String> adapter;// 适配器------>C控制器
    private String[] data = { "我是第1个列表项", "我是第2个列表项", "我是第3个列表项", "我是第4个列表项",
            "我是第5个列表项", "我是第6个列表项", "我是第7个列表项" };// 数据源-->M

    //public static List<ItemBean> datas;
    public static List<RuleBean> datas;
    public static ItemBean itemdata;
    private Context context;
    /*
    private DatePicker datePicker;
    private TimePicker timePicker;
    private String dateTime;
    private String initDateTime;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        final ViewHolder viewHolder;
        itemdata = new ItemBean();
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
/*
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
        });*/

        ListView listView = (ListView) findViewById(R.id.listview);
        Button button = (Button) findViewById(R.id.add);

        datas = new ArrayList<>();
        //datas.add(new ItemBean());
        datas.add(new RuleBean());
        //final ListAdapter adapter = new ListAdapter(datas, this, AddPlanActivity.this);
        final RulesAdapter adapter = new RulesAdapter(datas, this, AddPlanActivity.this);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //datas.add(new ItemBean());
                datas.add(new RuleBean());
                adapter.notifyDataSetChanged();
            }
        });

        viewHolder = new ViewHolder();
        viewHolder.stime = (TextView) findViewById(R.id.stime_value);
        viewHolder.etime = (TextView) findViewById(R.id.etime_value);

        viewHolder.stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil pick = new DateTimePickDialogUtil(AddPlanActivity.this, "");
                pick.dateTimePicKDialog(viewHolder.stime, 0, 1);
            }
        });
        viewHolder.etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil pick = new DateTimePickDialogUtil(AddPlanActivity.this, "");
                pick.dateTimePicKDialog(viewHolder.etime, 0, 2);
            }
        });


    }

    class ViewHolder {
        TextView stime;
        TextView etime;
    }
}
