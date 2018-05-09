package com.koudai.tinker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * @auther jsk
 * @date 2018/5/9
 */
public class TinkerDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPatchBtnClean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker_demo_layout);
        initView();

    }

    private void initView() {
        mPatchBtnClean = findViewById(R.id.clean_patch_btn);
        mPatchBtnClean.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clean_patch_btn:
                comfirCleanPatchDialog();
                break;
        }
    }

    private void comfirCleanPatchDialog(){
        new AlertDialog.Builder(this)
                .setMessage("确定卸载所有的补丁包？")
                .setPositiveButton("卸载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("清除所有的补丁包！");
                        TinkerInstaller.cleanPatch(getApplicationContext());
                    }
                })
                .setNeutralButton("补丁按钮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("添加补丁按钮！");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    private void toast(String text){
        Toast.makeText(TinkerDemoActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
