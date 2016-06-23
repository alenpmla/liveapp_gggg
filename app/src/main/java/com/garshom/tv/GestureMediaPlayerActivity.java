package com.garshom.tv;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import com.malmstein.fenster.controller.FensterPlayerControllerVisibilityListener;
import com.malmstein.fenster.play.FensterVideoFragment;



public class GestureMediaPlayerActivity extends Activity implements FensterPlayerControllerVisibilityListener {
    String playurl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_media_player);

        playurl= "http://live.wmncdn.net/pravasi/4962bdff80323f169b3232cc09e90775.sdp/playlist.m3u8";
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initVideo();
    }

    private void initVideo(){
        findVideoFragment().setVisibilityListener(this);
        findVideoFragment().playSetVideourl(playurl);
    }

    private FensterVideoFragment findVideoFragment(){
        return (FensterVideoFragment) getFragmentManager().findFragmentById(R.id.play_demo_fragment);
    }

    @Override
    public void onControlsVisibilityChange(boolean value) {
        setSystemUiVisibility(value);
    }

    private void setSystemUiVisibility(final boolean visible) {
        int newVis = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

        if (!visible) {
            newVis |= View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(newVis);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(final int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
                    findVideoFragment().showFensterController();
                }
            }
        });
    }
}
