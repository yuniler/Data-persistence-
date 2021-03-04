package cn.jkdev.androiduidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mAccount;
    private EditText mPassword;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一步：初始化View
        initView();
        //第二步：设置点击事件
        initListener();
    }

    private void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了登录按钮");
                handleLoginEvent(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        File filesDir = this.getFilesDir();
//        File saveFile = new File(filesDir,"info.text");


        try {

            FileInputStream fileInputStream = fileInputStream = this.openFileInput("info.text");//打开输入流文件
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String info = bufferedReader.readLine();
            //fos.write((accountText + "***" + passwordText).getBytes());
            //这个是之前数据的保存形式， 拿到数据后进行切割
            String[] split = info.split("\\*\\*\\*");
            //重新赋值account 和 passw.加在中间
            String account = split[0];
            String password = split[1];
            //回显数据
            mAccount.setText(account);
            mPassword.setText(password);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void handleLoginEvent(View v) {
        //3.拿到界面内容，account password
        String accountText = mAccount.getText().toString();
        String passwordText = mPassword.getText().toString();

        //账号密码进行检查，合法

        //这里进行为空检查

//        if (accountText.length() == 0) {
//            Toast.makeText(this,"账号不可以为空",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (passwordText.length() == 0) {
//            Toast.makeText(this,"密码不可以为空",Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (TextUtils.isEmpty(accountText)) {
            Toast.makeText(this,"账号不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this,"密码不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //将页面置空
        mAccount.setText("");
        mPassword.setText("");
        //账号密码保存起来
        saveUserInfo(accountText, passwordText);
    }

    private void saveUserInfo(String accountText, String passwordText) {
        Log.d(TAG, "保存用户信息");

        //获取缓存--路径
        File cacheDir = this.getCacheDir();
        Log.d(TAG,"cacheDir" + cacheDir);

        //保存数据的路径
        File filesDir = this.getFilesDir();
        File saveFile = new File(filesDir,"info.text");
        Log.d(TAG,"filesDir" + filesDir.toString());
//        File saveFile =
        try {
//        File file = new File("/data/data/cn.jkdev.androiduidemo/info.text");
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(saveFile);
            //以特定方式存储
            fos.write((accountText + "***" + passwordText).getBytes());

            fos.close();
            Toast.makeText(this,"成功登录",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        mAccount = this.findViewById(R.id.et_account);
        mPassword = this.findViewById(R.id.et_password);
        mLogin = this.findViewById(R.id.bt_login);
    }
}
