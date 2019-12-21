package com.zxn.expand;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.utils.QRUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_code_content)
    TextView tvCodeContent;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        findViewById(R.id.tv_title).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_title) {
            start();
        } else if (v.getId() == R.id.tv2) {
            try {
                String code = QRUtils.getInstance().decodeQRcode(ivCode);
                Log.i(TAG, "onClick: " + code);
                tvCodeContent.setText(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        int scan_type = QrConfig.TYPE_BARCODE;
        int scan_view_type = QrConfig.SCANVIEW_TYPE_BARCODE;
        int screen = QrConfig.SCREEN_PORTRAIT;

        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText("扫描框下文字")//扫描框下文字
                .setShowDes(true)//是否显示扫描框下面文字
                .setShowLight(true)//显示手电筒按钮
                .setShowTitle(true)//显示Title
                .setShowAlbum(false)//显示从相册选择按钮
                .setNeedCrop(false)//是否从相册选择后裁剪图片
                .setCornerColor(Color.parseColor("#E42E30"))//设置扫描框颜色
                .setLineColor(Color.parseColor("#E42E30"))//设置扫描线颜色
                .setLineSpeed(QrConfig.LINE_MEDIUM)//设置扫描线速度
                .setScanType(scan_type)//设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanViewType(scan_view_type)//设置扫描框类型（二维码还是条形码，默认为二维码）
                .setCustombarcodeformat(QrConfig.BARCODE_EAN13)//此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setPlaySound(true)//是否扫描成功后bi~的声音
                .setDingPath(R.raw.qrcode)//设置提示音(不设置为默认的Ding~)
                .setIsOnlyCenter(true)//是否只识别框中内容(默认为全屏识别)
                .setTitleText("扫描")//设置Tilte文字
                .setTitleBackgroudColor(Color.parseColor("#262020"))//设置状态栏颜色
                .setTitleTextColor(Color.WHITE)//设置Title文字颜色
                .setShowZoom(false)//是否开始滑块的缩放
                .setAutoZoom(false)//是否开启自动缩放(实验性功能，不建议使用)
                .setFingerZoom(false)//是否开始双指缩放
                .setDoubleEngine(false)//是否开启双引擎识别(仅对识别二维码有效，并且开启后只识别框内功能将失效)
                .setScreenOrientation(screen)//设置屏幕方式
                .setOpenAlbumText("选择要识别的图片")//打开相册的文字
                .setLooperScan(false)//是否连续扫描二维码
                .create();
        QrManager.getInstance().init(qrConfig).startScan(MainActivity.this, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                Toast.makeText(MainActivity.this, "5--->" + result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
