package cn.jkdev.androiduidemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SDCardDemoActivity extends Activity implements View.OnClickListener {

    //open failed: ENOENT (No such file or directory)---> 为什么报这个错误，
    private static final String TAG = "SDCardDemoActivity";
    private Button mWriteDataBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(R.layout.activity_sd_card);
        mWriteDataBtn = this.findViewById(R.id.write_data_2_sd_card_bt);
        mWriteDataBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String externalStorageState = Environment.getExternalStorageState();
        Log.d(TAG,"externalStorageState-->" + externalStorageState);
        //如果点击
        if (v == mWriteDataBtn) {
            //将数据写到sd卡上
            File filePath = new File("/storage/sdcard");
            File file = new File(filePath,"info.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("bbs.sunofbeach.com".getBytes());
                fos.close();
                Log.d(TAG,"写入sd卡成功");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
