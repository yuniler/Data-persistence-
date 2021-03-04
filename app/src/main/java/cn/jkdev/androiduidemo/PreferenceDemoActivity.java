package cn.jkdev.androiduidemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;

public class PreferenceDemoActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "PreferenceDemoActivity";
    private Switch mIsArrowUnknownResource;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_demo);

        //找到改控件
        mIsArrowUnknownResource = this.findViewById(R.id.is_allow_unknown_apps_resource_switch);
        mIsArrowUnknownResource.setOnCheckedChangeListener(this);//设置更改
        //1.拿到sharePreference
        mSharedPreferences = this.getSharedPreferences("setting_info", MODE_PRIVATE);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Log.d(TAG,"current mode -- "  + isChecked);
        //2.进入编辑模式
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        //3.保存数据
        edit.putBoolean("state ",isChecked);
        //4.提交数据
        edit.commit();
        Log.d(TAG,"提交完成");

    }
}
