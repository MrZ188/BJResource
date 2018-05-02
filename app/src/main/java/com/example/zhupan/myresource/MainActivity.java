package com.example.zhupan.myresource;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhupan.myresource.base.BaseActivity;
import com.example.zhupan.myresource.config.Constants;
import com.example.zhupan.myresource.utils.TipsDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_cn)
    Button btnCn;
    @BindView(R.id.btn_en)
    Button btnEn;
    @BindView(R.id.btn_dialog)
    Button btnDialog;

    private MySdk mySdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mySdk = MySdk.getInstant();
    }

    private void chooseChinese() {
        mySdk.setLanguage(this, Constants.CN);
    }

    private void chooseEnglish() {
        mySdk.setLanguage(this, Constants.EN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_cn, R.id.btn_en, R.id.btn_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cn:
                chooseChinese();
                break;
            case R.id.btn_en:
                chooseEnglish();
                break;
            case R.id.btn_dialog:
                TipsDialog dialogUtil = new TipsDialog(MainActivity.this);
                dialogUtil.show();
                dialogUtil.setCanceledOnTouchOutside(true);
                break;
        }
    }
}
