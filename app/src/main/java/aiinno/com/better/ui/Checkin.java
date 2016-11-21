package aiinno.com.better.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aiinno.com.better.R;
import aiinno.com.better.SetQuestionsActivity;
import aiinno.com.better.util.QiNiuUploader;
import me.nereo.multi_image_selector.MultiImageSelector;

public class Checkin extends AppCompatActivity {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    TextView img_path;
    Button set_quesions_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        Button cb = (Button)findViewById(R.id.checkin_add_img_button);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });
        img_path = (TextView)findViewById(R.id.c_img_path);
        set_quesions_button = (Button) findViewById(R.id.set_questions_button);

        set_quesions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Checkin.this, SetQuestionsActivity.class);
                Checkin.this.startActivity(intent);
                Checkin.this.finish();
            }
        });
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {

            MultiImageSelector selector = MultiImageSelector.create(Checkin.this);
            selector.showCamera(true);
            selector.count(9);
            //selector.single();
            selector.origin(mSelectPath);
            selector.start(Checkin.this, REQUEST_IMAGE);
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
                            ActivityCompat.requestPermissions(Checkin.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
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
                    sb.append("\n");
                }
                //mResultText.setText(sb.toString());
                img_path.setText(sb.toString());
                /*
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
                */
            }
        }
    }
}
