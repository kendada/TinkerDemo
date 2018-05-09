package com.koudai.tinker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.koudai.tinker.utils.Utils;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private Button mBtnRepir;
    private Button mBtnShow;
    private ImageView mViewImage;
    private Button mActivityBtnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnRepir = findViewById(R.id.repir_btn);
        mBtnRepir.setOnClickListener(this);
        mBtnShow = findViewById(R.id.show_btn);
        mBtnShow.setOnClickListener(this);
        mViewImage = findViewById(R.id.image_view);
        mActivityBtnNew = findViewById(R.id.new_activity_btn);
        mActivityBtnNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repir_btn:
                onRepir();
                break;
            case R.id.show_btn:
                mViewImage.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.new_activity_btn:
                startActivity(new Intent(this, TinkerDemoActivity.class));
                break;
        }
    }

    private void onRepir() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch.patch");
        Log.d(TAG, "补丁路径：" + file.getPath());
        if (!file.exists()) {
            Log.d(TAG, "补丁不存在！");
        }
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch.patch");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Utils.setBackground(false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.setBackground(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
