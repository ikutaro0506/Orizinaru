package android.lifeistech.com.orizinaru;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class KotaeActivity extends Activity {
    ImageView imageView4;
    String[] Unsei = new String[7];
    String[][] Gakumon = new String[7][5];
    String[][] Kinun = new String[7][5];
    String[][] Usemono = new String[7][4];
    String[][] Renai = new String[7][4];
    String[][] Byouki = new String[7][4];
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    ArrayList<String> unseiData = new ArrayList<String>();
    //ここでオブジェクトを生成する
    //箱を用意する

    private static final String TAG = "ScreenCapture";
    private static final int REQUEST_CODE_SCREEN_CAPTURE = 1;

    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private VirtualDisplay mVirtualDisplay;
    private int mWidth;
    private int mHeight;
    private ImageReader mImageReader;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotae);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        imageView4 = (ImageView) findViewById(R.id.imageView4);

//        Unsei[0] = "大吉";
//        Unsei[1] = "中吉";
//        Unsei[2] = "小吉";
//        Unsei[3] = "吉";
//        Unsei[4] = "末吉";
//        Unsei[5] = "凶";
//        Unsei[6] = "大凶";

        //ArrayListに要素を追加する
        unseiData.add("大吉");//0
        unseiData.add("中吉");//1
        unseiData.add("小吉");//2
        unseiData.add("吉");//3看
        unseiData.add("末吉");//4
        unseiData.add("凶");//5
        unseiData.add("大凶");//6

        Gakumon[0][0] = "学\n門\n\n今\nま\nで\nの\n努\n力\nが\n実\nを\n結\nぶ\nで\nし\nょ\nう\n";
        Gakumon[0][1] = "学\n門\n\n成\n績\nが\n上\nが\nる\nだ\nろ\nう\n";
        Gakumon[1][0] = "学\n門\n\n安\n心\nを\nし\nて\n勉\n強\nし\nな\nさ\nい\n";
        Gakumon[1][1] = "学\n門\n\n苦\n手\n科\n目\nを\n克\n服\nで\nき\nる\nか\nも\n";
        Gakumon[2][0] = "学\n門\n\n大\n丈\n夫\nだ\n\n問\n題\nな\nい\n";
        Gakumon[2][1] = "学\n門\n\n程\nよ\nく\n勉\n強\nせ\nよ\n";
        Gakumon[3][0] = "学\n門\n\n平\n均\nを\n少\nし\n下\n回\nる\nこ\nと\nに\nな\nる\nか\nも\nし\nれ\nな\nい\n";
        Gakumon[3][1] = "学\n門\n\n学\n業\nに\n専\n念\nし\nた\nほ\nう\nが\nい\nい\n";
        Gakumon[4][0] = "学\n門\n\nあ\nま\nり\n期\n待\nし\nな\nい\nほ\nう\nが\nい\nい\n";
        Gakumon[4][1] = "学\n門\n\n得\n意\n科\n目\nの\n成\n績\nが\n落\nち\nる\nか\nも\n";
        Gakumon[5][0] = "学\n門\n\nも\nう\nち\nょ\nっ\nと\n勉\n強\nし\nた\n方\nが\nい\nい\nの\nで\nは\n？\n";
        Gakumon[5][1] = "学\n門\n\nテ\nス\nト\nが\n思\nっ\nた\nよ\nり\n難\nし\nい\nか\nも\n注\n意\nし\nよ\nう\n！\n";
        Gakumon[6][0] = "学\n問\n\n手\nの\n施\nよ\nう\nが\nあ\nり\nま\nせ\nん\n";
        Gakumon[6][1] = "学\n門\n\n全\n体\n的\nに\n成\n績\nが\n下\nが\nる\nか\nも\n";

        Kinun[0][0] = "金\n運\n\nギ\nャ\nン\nブ\nル\nで\n大\n儲\nけ\nす\nる\nか\nも\n！︎\n";
        Kinun[0][1] = "金\n運\n\n収\n入\nが\n多\nく\nな\nる\n";
        Kinun[1][0] = "金\n運\n\n道\n端\nに\nお\n金\nが\n落\nち\nて\nい\nる\nか\nも\n！\n︎";
        Kinun[1][1] = "金\n運\n\n日\n々\nの\n生\n活\nに\nき\nす\nれ\nば\n良\nく\nな\nる\n";
        Kinun[2][0] = "金\n運\n\n給\n料\nが\n増\nえ\nる\n";
        Kinun[2][1] = "金\n運\n\n金\nの\n動\nき\nが\n激\nし\nく\nな\nる\n";
        Kinun[3][0] = "金\n運\n\n無\n駄\nな\n動\nき\nを\nし\nな\nけ\nれ\nば\n良\nい\nこ\nと\nが\n起\nき\nる\nだ\nろ\nう\n";
        Kinun[3][1] = "金\n運\n\nポ\nケ\nッ\nト\nに\n五\n百\n円\n入\nっ\nて\nい\nる\nか\nも\n";
        Kinun[4][0] = "金\n運\n\nギ\nャ\nン\nブ\nル\nを\nし\nな\nい\n方\nが\nい\nい\nか\nも\n";
        Kinun[4][1] = "金\n運\n\nお\n金\nを\n貸\nさ\nな\nい\nほ\nう\nが\nい\nい\n";
        Kinun[5][0] = "金\n運\n\n詐\n欺\nに\nひ\nっ\nか\nか\nる\n";
        Kinun[5][1] = "金\n運\n\n千\n円\n落\nと\nす\n";
        Kinun[6][0] = "金\n運\n\n無\n駄\n遣\nい\nを\nし\nな\nい\nほ\nう\nが\nい\nい\n";
        Kinun[6][1] = "金\n運\n\n無\n駄\nに\n動\nか\nな\nい\nほ\nう\nが\nい\nい\n";

        Usemono[0][0] = "失\n物\n\n近\n々\n見\nつ\nか\nる\nだ\nろ\nう\n";
        Usemono[0][1] = "失\n物\n\nほ\nど\nな\nく\n出\nる\n";
        Usemono[1][0] = "失\n物\n\nポ\nケ\nッ\nト\nに\n百\n円\n玉\nが\nあ\nる\nこ\nと\nを\n見\nつ\nけ\nる\n";
        Usemono[1][1] = "失\n物\n\nタ\nン\nス\nの\n中\nに\nあ\nる\nだ\nろ\nう\n";
        Usemono[2][0] = "失\n物\n\n壊\nれ\nた\n状\n態\n\nで\n見\nつ\nか\nる\n";
        Usemono[2][1] = "失\n物\n\n家\nの\n中\nに\nあ\nる\n";
        Usemono[3][0] = "失\n物\n\n一\n番\n大\n切\nな\nも\nの\nを\n失\nう\nだ\nろ\nう\n";
        Usemono[3][1] = "失\n物\n\n物\nと\n物\nの\n間\nに\nあ\nり\n";
        Usemono[4][0] = "失\n物\n\n財\n布\nを\n落\nと\nし\nて\nし\nま\nう\nの\nで\n外\n出\nを\n控\nえ\nよ\nう\n";
        Usemono[4][1] = "失\n物\n\n箱\nの\n中\nに\nあ\nる\n";
        Usemono[5][0] = "失\n物\n\n百\n万\n円\n落\nと\nす\nか\nも\n！\n︎";
        Usemono[5][1] = "失\n物\n\n出\nる\nが\n遅\nい\n";
        Usemono[6][0] = "失\n物\n\nな\nく\nな\nっ\nた\n物\nは\n一\n生\n見\nつ\nか\nら\nな\nい";
        Usemono[6][1] = "失\n物\n\n出\nず\n、\nさ\nら\nな\nる\n失\nせ\n物\nに\n気\nを\nつ\nけ\nよ\n";

        Renai[0][0] = "恋\n愛\n\n異\n性\nに\nも\nて\nる\nか\nも\n";
        Renai[0][1] = "恋\n愛\n\n他\n人\nの\n言\n動\nに\n惑\nわ\nさ\nれ\nる\nな\n";
        Renai[1][0] = "恋\n愛\n\n告\n白\nさ\nれ\nる\nか\nも\n";
        Renai[1][1] = "恋\n愛\n\n気\nに\nな\nる\n人\nに\n落\nち\nた\n消\nし\nゴ\nム\nを\n拾\nっ\nて\nも\nら\nう\n";
        Renai[2][0] = "恋\n愛\n\n父\n母\nに\n相\n談\nせ\nよ\n";
        Renai[2][1] = "恋\n愛\n\nほ\nど\nな\nく\nし\nて\n出\nる\n";
        Renai[3][0] = "恋\n愛\n\n同\n性\nに\nモ\nテ\nる\nか\nも\n";
        Renai[3][1] = "恋\n愛\n\n頑\n張\nれ\nば\n実\nる\n";
        Renai[4][0] = "恋\n愛\n\n異\n性\nと\n一\n回\nも\n話\nさ\nな\nい\nだ\nろ\nう\n";
        Renai[4][1] = "恋\n愛\n\n他\n人\nの\n言\n動\nに\n惑\nわ\nさ\nれ\nる\n";
        Renai[5][0] = "恋\n愛\n\n告\n白\nは\n明\n日\nの\n方\nが\nい\nい\nか\nも\n！\n︎";
        Renai[5][1] = "恋\n愛\n\n振\nら\nれ\nる\n";
        Renai[6][0] = "恋\n愛\n\nほ\nっ\nし\nゃ\nん\nと\nデ\n|\nト\nす\nれ\nば\n大\n吉\nに\nな\nる\n";
        Renai[6][1] = "恋\n愛\n\n頬\nを\n叩\nか\nれ\nな\nが\nら\n振\nら\nれ\nる\n";

        Byouki[0][0] = "病\n気\n\n順\n調\n治\nる\nだ\nろ\nう\n";
        Byouki[0][1] = "病\n気\n\n今\n日\nは\n健\n康\nに\n過\nご\nせ\nる\nで\nし\nょ\nう\n";
        Byouki[1][0] = "病\n気\n\n怪\n我\nの\nし\nな\nい\n一\n日\nに\nな\nる\nだ\nろ\nう\n";
        Byouki[1][1] = "病\n気\n\n生\n命\nの\n気\n遣\nい\nな\nし\n";
        Byouki[2][0] = "病\n気\n\n身\n近\nな\n人\nの\n病\n気\nが\n治\nる\nか\nも\n";
        Byouki[2][1] = "病\n気\n\n軽\nい\n病\nに\n特\nに\n注\n意\n";
        Byouki[3][0] = "病\n気\n\n筋\n肉\n痛\nに\nな\nる\nだ\nろ\nう\n";
        Byouki[3][1] = "病\n気\n\n心\n長\nく\n保\n養\nせ\nよ\n";
        Byouki[4][0] = "病\n気\n\n肩\nこ\nり\nの\n激\nし\nい\n1\n日\nに\nな\nる\nだ\nろ\nう\n";
        Byouki[4][1] = "病\n気\n\n風\n邪\nを\nひ\nく\nか\nも\n";
        Byouki[5][0] = "病\n気\n\n心\n長\nく\n保\n養\nし\nた\n方\nが\nい\nい\n";
        Byouki[5][1] = "病\n気\n\n怪\n我\nに\nあ\nう\nの\nで\n気\nを\nつ\nけ\nま\nし\nょ\nう\n";
        Byouki[6][0] = "病\n気\n\n入\n院\nを\nし\nな\nい\nと\nい\nけ\nな\nい\nよ\nう\nな\n病\n気\nに\nか\nか\nる\n";
        Byouki[6][1] = "病\n気\n\n重\nい\n病\n気\nに\nな\nる\nだ\nろ\nう\n";


        Random random = new Random();
        int number = random.nextInt(100);
        int numberg = random.nextInt(2);
        int numberk = random.nextInt(2);
        int numberu = random.nextInt(2);
        int numberr = random.nextInt(2);
        int numberb = random.nextInt(2);
        if (number <= 15) {



            mySetText(unseiData.get(0), Gakumon[0][numberg], Kinun[0][numberk], Usemono[0][numberu], Renai[0][numberr], Byouki[0][numberb]);
//            int j = random.nextInt(4);
//            int k = random.nextInt(4);
//            int l = random.nextInt(4);
//            mySetText(Unsei[0], Gakumon[0], Kinun[k], Usemono[l], Renai[0], Byouki[0]);
//            mySetText(unseiData.get(j), Gakumon[2], Kinun[0], Usemono[0], Renai[0], Byouki[0]);
//
//            mySetText(Unsei[0],         Gakumon[2], Kinun[0], Usemono[0], Renai[0], Byouki[0]);
//            mySetText(unseiData.get(0), Gakumon[2], Kinun[0], Usemono[0], Renai[0], Byouki[0]);
//            mySetText("大吉",            Gakumon[2], Kinun[0], Usemono[0], Renai[0], Byouki[0]);

//            textView1.setText(Unsei[0]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (15 < number && number <= 32) {

            mySetText(unseiData.get(1), Gakumon[1][numberg], Kinun[1][numberk], Usemono[1][numberu], Renai[1][numberr], Byouki[1][numberb]);

//            textView1.setText(Unsei[1]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (32 < number && number <= 50) {

            mySetText(unseiData.get(2), Gakumon[2][numberg], Kinun[2][numberk], Usemono[2][numberu], Renai[2][numberr], Byouki[2][numberb]);
//            textView1.setText(Unsei[2]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (50 < number && number <= 65) {

            mySetText(unseiData.get(3), Gakumon[3][numberg], Kinun[3][numberk], Usemono[3][numberu], Renai[3][numberr], Byouki[3][numberb]);

//            textView1.setText(Unsei[3]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (65 < number && number <= 80) {

            mySetText(unseiData.get(4), Gakumon[4][numberg], Kinun[4][numberk], Usemono[4][numberu], Renai[4][numberr], Byouki[4][numberb]);

//            textView1.setText(Unsei[4]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (80 < number && number <= 90) {

            mySetText(unseiData.get(5), Gakumon[5][numberg], Kinun[5][numberk], Usemono[5][numberu], Renai[5][numberr], Byouki[5][numberb]);

//            textView1.setText(Unsei[5]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        } else if (90 < number && number <= 100) {

            mySetText(unseiData.get(6), Gakumon[6][numberg], Kinun[6][numberk], Usemono[6][numberu], Renai[6][numberr], Byouki[6][numberb]);

//            textView1.setText(Unsei[6]);
//            textView2.setText(Gakumon[2]);
//            textView3.setText(Kinun[0]);
//            textView4.setText(Usemono[0]);
//            textView5.setText(Renai[0]);
//            textView6.setText(Byouki[0]);

        }


//        mMediaProjectionManager = (MediaProjectionManager)
//                getSystemService(Context.MEDIA_PROJECTION_SERVICE);
//
//        Intent permissionIntent = mMediaProjectionManager.createScreenCaptureIntent();
//        startActivityForResult(permissionIntent, REQUEST_CODE_SCREEN_CAPTURE);


    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    public void sentakugamen(View view) {
        Intent intent = new Intent(KotaeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onemore(View view) {
        mMediaProjectionManager = (MediaProjectionManager)
                getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        Intent permissionIntent = mMediaProjectionManager.createScreenCaptureIntent();
        startActivityForResult(permissionIntent, REQUEST_CODE_SCREEN_CAPTURE);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kotae, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mySetText(String data1, String data2, String data3, String data4, String data5, String data6) {
        //引数：　運勢・学問・金運・失せ物・恋愛・病気
        textView1.setText(data1);
        textView2.setText(data2);
        textView3.setText(data3);
        textView4.setText(data4);
        textView5.setText(data5);
        textView6.setText(data6);

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (REQUEST_CODE_SCREEN_CAPTURE == requestCode) {
            if (resultCode != RESULT_OK) {
                //パーミッションなし
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                return;
            }
            // MediaProjectionの取得
            mMediaProjection =
                    mMediaProjectionManager.getMediaProjection(resultCode, intent);

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
            int density = metrics.densityDpi;

            Log.d(TAG, "setup VirtualDisplay");
            mImageReader = ImageReader
                    .newInstance(mWidth, mHeight, ImageFormat.RGB_565, 2);
            mVirtualDisplay = mMediaProjection
                    .createVirtualDisplay("Capturing Display",
                            mWidth, mHeight, density,
                            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                            mImageReader.getSurface(), null, null);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        if (mVirtualDisplay != null) {
            Log.d(TAG,"release VirtualDisplay");
            mVirtualDisplay.release();
        }
        super.onPause();
    }
}
