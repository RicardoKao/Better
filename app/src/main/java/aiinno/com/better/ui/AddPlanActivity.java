package aiinno.com.better.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import android.content.DialogInterface;


import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import aiinno.com.better.model.Plan;
import aiinno.com.better.util.DateTimePickDialogUtil;
import aiinno.com.better.ItemBean;
import aiinno.com.better.ListAdapter;
import aiinno.com.better.R;
import aiinno.com.better.TestActivity;
import aiinno.com.better.adapter.RulesAdapter;
import aiinno.com.better.model.RuleBean;
import android.util.Log;
import aiinno.com.better.service.SignService;
import aiinno.com.better.service.PlanService;
import aiinno.com.better.model.Ret;
import aiinno.com.better.util.QiNiuUploader;
import me.nereo.multi_image_selector.MultiImageSelector;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import android.content.pm.PackageManager;

import android.os.Build;
import android.Manifest;

import java.util.Date;
import java.text.SimpleDateFormat;


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
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    private ArrayList<String> mSelectPath;
    TextView img_path;
    public String img_title;
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
        Button addimg_button = (Button) findViewById(R.id.create_plan_addimg_button);
        addimg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        img_path = (TextView) findViewById(R.id.img_path);

        Button cbutton = (Button) findViewById(R.id.create_plan_button);
        final EditText title_view = (EditText) findViewById(R.id.create_plan_title_value);
        final EditText declare_view = (EditText) findViewById(R.id.create_plan_declare_value);
        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //datas.add(new ItemBean());
                String title_value = title_view.getText().toString();
                String declare_value = declare_view.getText().toString();
                String stime_value = itemdata.getStime();
                String etime_value = itemdata.getEtime();
                ArrayList<String> rules = new ArrayList<>();
                for(int i = 0;i <datas.size();i++){
                    rules.add(i,datas.get(i).getRule());
                }
                Log.d("test_edit",title_value+","+declare_value+","+stime_value+","+etime_value);
                for(int i =0;i<rules.size();i++){
                    Log.d("test_edit",rules.get(i));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                long days = 0;

                try {
                    java.util.Date beginDate = sdf.parse(stime_value);
                    java.util.Date endDate = sdf.parse(etime_value);
                    days =(beginDate.getTime()-endDate.getTime())/(24*60*60*1000);
                }catch(java.text.ParseException e){
                    Log.e("parse_error",e.toString());
                };
                /*

                try {
                    SignService s = new SignService();
                    PlanService p = new PlanService();
                    Ret authinfo = s.WechatAuth("xiaotingv6", s.API_URL);
                    System.out.println(authinfo.data);
                    Plan plan = new Plan();
                    plan.setName(title_value );
                    plan.setTitle(title_value );
                    plan.setYourdeclear(declare_value);
                    plan.setRules(rules);
                    plan.setDays((int)days);
                    plan.setComplated_days(0);
                    plan.setFee(50);
                    plan.setImg("studytest.png");
                    plan.setStart("nowdays");
                    ArrayList<String> tags = new ArrayList<>();
                    tags.add(0, "great");
                    tags.add(1, "hello");
                    plan.setTags(tags);
                    Ret addplaninfo = p.AddPlan(plan, authinfo.data);
                    System.out.println(addplaninfo.data);
                } catch (IOException e){

                };
                */
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("plan_img_path","test");
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: mSelectPath){
                    sb.append(p);
                    //sb.append("\n");
                }
                //mResultText.setText(sb.toString());
                img_path.setText(sb.toString());
                Log.d("plan_img_path",sb.toString());
                String regEx = "\\.[a-z]+";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(sb.toString());
                boolean rs = matcher.find();
                Log.d("plan_img_path",matcher.group());

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                System.out.println();

                QiNiuUploader qiNiuUploader = new QiNiuUploader();
                qiNiuUploader.setUploadFilePath(sb.toString());
                qiNiuUploader.uploadFile(df.format(new Date())+matcher.group());
                img_title = df.format(new Date())+matcher.group();
            }
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(AddPlanActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {

            MultiImageSelector selector = MultiImageSelector.create(AddPlanActivity.this);
            //selector.showCamera();
            //selector.count();
            selector.single();
            selector.origin(mSelectPath);
            selector.start(AddPlanActivity.this, REQUEST_IMAGE);
        }
    }

    class ViewHolder {
        TextView stime;
        TextView etime;
    }
}
