package group.tonight.schoolcleaner.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import group.tonight.schoolcleaner.R;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：启动页
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 17:13
 * 版本：V0.0.1
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
