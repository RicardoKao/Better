package aiinno.com.better.util;

import android.util.Log;
import android.widget.Toast;

import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.utils.AsyncRun;

import org.json.JSONException;

import java.io.IOException;

import aiinno.com.better.R;

import aiinno.com.better.model.Ret;
import aiinno.com.better.service.SignService;

import java.io.File;

import com.qiniu.android.storage.UpCompletionHandler;

import com.qiniu.android.http.ResponseInfo;

import org.json.JSONObject;

/**
 * Created by lbk on 2016/11/19.
 */

public class QiNiuUploader {
    private UploadManager uploadManager;
    private long uploadLastTimePoint;
    private long uploadLastOffset;
    private long uploadFileLength;
    private String uploadFilePath;

    public void setUploadFilePath(String uploadFilePath){
        this.uploadFilePath = uploadFilePath;
    }

    public void uploadFile(String key) {
        final String m_key = key;
        if (this.uploadFilePath == null) {
            return;
        }

        //从业务服务器获取上传凭证
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SignService s = new SignService();
                    Ret authinfo = s.WechatAuth("xiaotingv6",s.API_URL);
                    System.out.println("authinfo:"+authinfo.data);
                    Ret qiniuauthinfo = s.QiNiuAuth(m_key,authinfo.data);
                    System.out.println("quniuauthinfo:"+qiniuauthinfo.data);
                    upload(qiniuauthinfo.data,m_key);

                } catch (IOException e) {

                    //Todo close app and sendbug
                }  finally {
                    //Todo close app and sendbug
                }
            }
        }).start();

    }

    private void upload(String uploadToken,String uploadFileKey) {
        Configuration config = new Configuration.Builder().zone(Zone.zone2).build();
        if (this.uploadManager == null) {
            this.uploadManager = new UploadManager(config);
        }

        File uploadFile = new File(this.uploadFilePath);
        UploadOptions uploadOptions = new UploadOptions(null, null, false,
                new UpProgressHandler() {
                    @Override
                    public void progress(String key, double percent) {

                    }
                }, null);

        this.uploadManager.put(uploadFile, uploadFileKey, uploadToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {
                        if (respInfo.isOK()) {
                            try {
                                String fileKey = jsonData.getString("key");
                                String fileHash = jsonData.getString("hash");
                            } catch (JSONException e) {
                                Log.d("json-error:",e.toString());

                            }
                        } else {
                            Log.d("upload-failed:","upload-failed");
                        }
                    }

                }, null);
    }

    public static void main(String... args) throws IOException, InterruptedException {

    }
}
