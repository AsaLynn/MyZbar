# MyZbar
MyZbar
zbar扫描快，zxing可以生成和识别本地，So,我就把他们结合在了一起，这样二维码识别就更便捷了（包含主要功能，二维码识别生成，条形码识别生成）

# 引入
```
//implementation 'cn.bertsir.zbar:zbarlibary:1.0.1'
implementation 'com.zxn.zbar:android-zbar:1.0.3'

```
### 使用方法
## 1.识别二维码（条形码）
<pre>

        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText("(识别二维码)")//扫描框下文字
                .setShowDes(false)//是否显示扫描框下面文字
                .setShowLight(true)//显示手电筒按钮
                .setShowTitle(true)//显示Title
                .setShowAlbum(true)//显示从相册选择按钮
                .setCornerColor(Color.WHITE)//设置扫描框颜色
                .setLineColor(Color.WHITE)//设置扫描线颜色
                .setLineSpeed(QrConfig.LINE_MEDIUM)//设置扫描线速度
                .setScanType(QrConfig.TYPE_QRCODE)//设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanViewType(QrConfig.SCANVIEW_TYPE_QRCODE)//设置扫描框类型（二维码还是条形码，默认为二维码）
                .setCustombarcodeformat(QrConfig.BARCODE_I25)//此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setPlaySound(true)//是否扫描成功后bi~的声音
                .setNeedCrop(true)//从相册选择二维码之后再次截取二维码
                .setDingPath(R.raw.test)//设置提示音(不设置为默认的Ding~)
                .setIsOnlyCenter(true)//是否只识别框中内容(默认为全屏识别)
                .setTitleText("扫描二维码")//设置Tilte文字
                .setTitleBackgroudColor(Color.BLUE)//设置状态栏颜色
                .setTitleTextColor(Color.BLACK)//设置Title文字颜色
                .setShowZoom(false)//是否手动调整焦距
                .setAutoZoom(false)//是否自动调整焦距
                .setFingerZoom(false)//是否开始双指缩放
                .setScreenOrientation(QrConfig.SCREEN_PORTRAIT)//设置屏幕方向
                .setDoubleEngine(false)//是否开启双引擎识别(仅对识别二维码有效，并且开启后只识别框内功能将失效)
                .setOpenAlbumText("选择要识别的图片")//打开相册的文字
                .setLooperScan(false)//是否连续扫描二维码
                .setLooperWaitTime(5*1000)//连续扫描间隔时间
                .setScanLineStyle(ScanLineView.style_radar)//扫描动画样式
                .setAutoLight(false)//自动灯光
                .setShowVibrator(false)//是否震动提醒
                .create();
   QrManager.getInstance().init(qrConfig).startScan(MainActivity.this, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(ScanResult result) {
                Log.e(TAG, "onScanSuccess: "+result );
                Toast.makeText(getApplicationContext(), "内容："+result.getContent()
                                +"  类型："+result.getType(), Toast.LENGTH_SHORT).show();
            }
        });
</pre>
OK,就这么简单！

##### 如果扫描界面不符合你的需求，来吧QRActivity的布局文件你随便改，保证改起来比别的库简单！

## 2.生成码
###  2.1生成二维码 
<pre>
Bitmap qrCode = QRUtils.getInstance().createQRCode("www.qq.com");
</pre>

####  2.1.1生成二维码并添加Logo
<pre>
Bitmap qrCode = QRUtils.getInstance().createQRCodeAddLogo(et_qr_content.getText().toString(),BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
</pre>
###  2.2生成条形码
<pre>
QRUtils.TextViewConfig textViewConfig = new QRUtils.TextViewConfig();
textViewConfig.setSize(10);
 Bitmap barCodeWithText = QRUtils.getInstance().createBarCodeWithText(getApplicationContext(), content, 300, 100, textViewConfig);
</pre>

## 3.识别本地
### 3.1 识别本地二维码
<pre>
//可以传图片路径，Bitmap,ImageView 是不是很人性化
String s = QRUtils.getInstance().decodeQRcode(iv_qr);
</pre>

### 3.2 识别本地条形码
<pre>
//可以传图片路径，Bitmap,ImageView 是不是很人性化
String s = QRUtils.getInstance().decodeBarcode(iv_qr);
</pre>

## 4.参数描述
| name | format | description |
| ------------- |:-------------:| :-------------:|
| setDesText | String | 设置扫描框下方描述文字 |
| setShowDes | Boolean | 设置是否显示扫描框下方描述文字 |
| setShowLight | Boolean | 是否开启手电筒功能 |
| setShowAlbum | Boolean | 是否开启从相册选择功能 |
| setShowTitle | Boolean | 是否显示Title |
| setTitleText | String | 设置Title文字 |
| setTitleBackgroudColor | int | 设置Title背景色 |
| setTitleTextColor | int | 设置Title文字颜色 |
| setCornerColor | int | 设置扫描框颜色 |
| setLineColor | int | 设置扫描线颜色 |
| setLineSpeed | int | 设置扫描线速度</br>QrConfig.LINE_FAST(快速)</br>QrConfig.LINE_MEDIUM(中速）<br>QrConfig.LINE_SLOW(慢速) <br>也可以自定义时间(单位毫秒)|
| setScanType | int | 设置扫描类型</br>QrConfig.TYPE_QRCODE(二维码)</br>QrConfig.TYPE_BARCODE(条形码)</br>QrConfig.TYPE_ALL(全部类型)</br>QrConfig.TYPE_CUSTOM(指定类型) |
| setScanViewType | int | 设置扫描框类型</br>QrConfig.SCANVIEW_TYPE_QRCODE(二维码)</br>QrConfig.SCANVIEW_TYPE_BARCODE(条形码) |
| setCustombarcodeformat| int| 设置指定扫码类型（举例：QrConfig.BARCODE_EAN13）,此项只有在ScanType设置为自定义时才生效 |
| setIsOnlyCenter| Boolean | 设置是否只识别扫描框中的内容（默认为全屏扫描） |
| setPlaySound | Boolean | 设置扫描成功后是否有提示音 |
| setDingPath | int| 自定义提示音（举例：R.raw.test，不设置为默认的) |
| setNeedCrop | Boolean | 从相册选择二维码之后再次手动框选二维码(默认为true) |
| setShowZoom | Boolean | 是否开启手动调整焦距(默认为false) |
| setAutoZoom | Boolean | 是否开启自动调整焦距(默认为false) |
| setFingerZoom | Boolean | 是否开启双指调整焦距(默认为false) |
| setScreenOrientation | int | 设置屏幕方向</br>QrConfig.SCREEN_PORTRAIT(纵向)</br>QrConfig.SCREEN_LANDSCAPE(横向）<br>QrConfig.SCREEN_SENSOR(传感器方向) |
| setDoubleEngine | Boolean | 是否开启双识别引擎(默认为false) |
| setLooperScan | Boolean | 是否开启连续扫描(默认为false) |
| setOpenAlbumText | String | 设置打开相册的文字 |
| setLooperWaitTime | int | 设置连续扫描间隔时间，单位毫秒（默认为0） |
| setScanLineStyle | int | 设置扫描动画样式</br>ScanLineView.style_radar(雷达)</br>ScanLineView.style_gridding(网格）<br>ScanLineView.style_hybrid(网格+雷达) <br>ScanLineView.style_line(线条)（默认为雷达） |
| setAutoLight | Boolean | 是否开启自动灯光(默认为false)|
| setShowVibrator | Boolean | 是否开启震动提醒(默认为false)|
| setBackImageRes | int | 设置title返回图标，不设置为默认|
| setLightImageRes | int | 设置闪光灯图标，不设置为默认|
| setAblumImageRes | int | 设置相册图标，不设置为默认|
