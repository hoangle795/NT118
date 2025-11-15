package com.example.lab04_1_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode;
    private Button btnBlinkXml, btnBlinkCode, btnZoomInXml, btnZoomInCode;
    private Button btnZoomOutXml, btnZoomOutCode, btnRotateXml, btnRotateCode;
    private Button btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode;
    private Button btnBounceXml, btnBounceCode, btnCombineXml, btnCombineCode;

    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();

        // BÀI 1: Animation từ XML
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        // BÀI 2: Animation từ CODE
        handleClickAnimationCode(btnFadeInCode, createFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, createFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, createBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, createZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, createZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, createRotateAnimation());
        handleClickAnimationCode(btnMoveCode, createMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, createSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, createBounceAnimation());
        handleClickAnimationCode(btnCombineCode, createCombineAnimation());


        // BÀI 3: Chuyển Activity mới

        ivUitLogo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            startActivity(intent);

            overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
        });
    }


    private void findViewsByIds() {
        ivUitLogo = findViewById(R.id.iv_uit_logo);

        btnFadeInXml = findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = findViewById(R.id.btn_fade_in_code);

        btnFadeOutXml = findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = findViewById(R.id.btn_fade_out_code);

        btnBlinkXml = findViewById(R.id.btn_blink_xml);
        btnBlinkCode = findViewById(R.id.btn_blink_code);

        btnZoomInXml = findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = findViewById(R.id.btn_zoom_in_code);

        btnZoomOutXml = findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = findViewById(R.id.btn_zoom_out_code);

        btnRotateXml = findViewById(R.id.btn_rotate_xml);
        btnRotateCode = findViewById(R.id.btn_rotate_code);

        btnMoveXml = findViewById(R.id.btn_move_xml);
        btnMoveCode = findViewById(R.id.btn_move_code);

        btnSlideUpXml = findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = findViewById(R.id.btn_slide_up_code);

        btnBounceXml = findViewById(R.id.btn_bounce_xml);
        btnBounceCode = findViewById(R.id.btn_bounce_code);

        btnCombineXml = findViewById(R.id.btn_combine_xml);
        btnCombineCode = findViewById(R.id.btn_combine_code);
    }


    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(),
                        "Animation Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override public void onAnimationRepeat(Animation animation) {}
        };
    }


    private void handleClickAnimationXml(Button btn, final int animId) {
        btn.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, animId);
            animation.setAnimationListener(animationListener);
            ivUitLogo.startAnimation(animation);
        });
    }


    private void handleClickAnimationCode(Button btn, final Animation animation) {
        btn.setOnClickListener(v -> ivUitLogo.startAnimation(animation));
    }


    private Animation createFadeInAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }

    private Animation createFadeOutAnimation() {
        AlphaAnimation anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }

    private Animation createBlinkAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(300);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(3);
        return anim;
    }

    private Animation createZoomInAnimation() {
        ScaleAnimation anim = new ScaleAnimation(
                1f, 3f, 1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }

    private Animation createZoomOutAnimation() {
        ScaleAnimation anim = new ScaleAnimation(
                1f, 0.5f, 1f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }

    private Animation createRotateAnimation() {
        RotateAnimation anim = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        anim.setDuration(600);
        anim.setRepeatCount(2);
        anim.setRepeatMode(Animation.RESTART);
        return anim;
    }

    private Animation createMoveAnimation() {
        TranslateAnimation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 1.2f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f
        );
        anim.setDuration(800);
        anim.setFillAfter(true);
        return anim;
    }


    private Animation createSlideUpAnimation() {
        ScaleAnimation anim = new ScaleAnimation(
                1f, 1f,
                1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        anim.setDuration(500);
        anim.setFillAfter(true);
        return anim;
    }

    private Animation createBounceAnimation() {
        ScaleAnimation anim = new ScaleAnimation(
                1f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        anim.setDuration(500);
        anim.setInterpolator(new BounceInterpolator());
        return anim;
    }

    private Animation createCombineAnimation() {
        AnimationSet set = new AnimationSet(true);

        ScaleAnimation zoom = new ScaleAnimation(
                1f, 3f, 1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoom.setDuration(4000);

        RotateAnimation rotate = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(500);
        rotate.setRepeatCount(2);

        set.addAnimation(zoom);
        set.addAnimation(rotate);
        set.setFillAfter(true);

        return set;
    }
}
