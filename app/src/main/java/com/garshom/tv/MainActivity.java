package com.garshom.tv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;




public class MainActivity extends AppCompatActivity {
ProgressBar pgbar;
    TextView errorview;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        pgbar=(ProgressBar)findViewById(R.id.pgbar);
        errorview=(TextView)findViewById(R.id.errorview);
        pgbar.setVisibility(View.VISIBLE);
        errorview.setVisibility(View.GONE);
        videoView=(VideoView)findViewById(R.id.videoview);




        videoView.setMediaController(new android.widget.MediaController(this){
            @Override
            public void hide() {
                super.hide();

            }

            @Override
            public void show() {
                super.show();

            }
        });

     final    Uri uri=Uri.parse("http://livestream.5centscdn.com/medianet/e0bb98eac1adf1bd43abea23800a9978.sdp/index.m3u8");

        new Handler(Looper.myLooper()).post(new Runnable(){
            @Override
            public void run(){
                videoView.setVideoURI(uri);
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                pgbar.setVisibility(View.GONE);
                errorview.setVisibility(View.GONE);
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                errorview.setVisibility(View.VISIBLE);
                pgbar.setVisibility(View.GONE);

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        videoView.pause();
        finish();
        Intent intent=new Intent(MainActivity.this,FrontActivity.class);
        startActivity(intent);

    }
}
