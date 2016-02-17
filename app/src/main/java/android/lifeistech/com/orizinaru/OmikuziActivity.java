package android.lifeistech.com.orizinaru;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class OmikuziActivity extends Activity {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView5;
    Button button;
    // おみくじを引いてるか
    boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omikuzi);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        button = (Button) findViewById(R.id.button);
    }

    public void button(View view) {
        if (!isStarted) {
            isStarted = true;
            button.setText("スキップ");
            imageView1.setImageResource(R.mipmap.omikuzi1);
            imageView2.setImageResource(R.mipmap.arm);
            imageView3.setImageResource(R.mipmap.omikuzi2);
            TranslateAnimation paper1Animation = new TranslateAnimation(0, 0, 0, 400); // (0,0)から(100,100)に
            paper1Animation.setDuration(3000); // 3000msかけてアニメーションする
            imageView5.startAnimation(paper1Animation);
            TranslateAnimation downAnimation = new TranslateAnimation(0, 0, 0, 400); // (0,0)から(100,100)に
            downAnimation.setDuration(3000); // 3000msかけてアニメーションする
            imageView2.startAnimation(downAnimation);
            downAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imageView5.setImageResource(R.mipmap.omikuzipaper);
                    TranslateAnimation upAnimation = new TranslateAnimation(0, 0, 400, 0); // (0,0)から(100,100)に
                    upAnimation.setDuration(3000); // 3000msかけてアニメーションする
                    imageView2.startAnimation(upAnimation);
                    TranslateAnimation paperAnimation = new TranslateAnimation(0, 0, 400, 0); // (0,0)から(100,100)に
                    paperAnimation.setDuration(3000); // 3000msかけてアニメーションする
                    imageView5.startAnimation(paperAnimation);
                    upAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Intent intent = new Intent(OmikuziActivity.this, KotaeActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            // 今おみくじを引いてるときの処理
            Intent intent = new Intent(OmikuziActivity.this, KotaeActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isStarted = false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_omikuzi, menu);
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
}
