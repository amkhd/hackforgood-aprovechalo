package hackforgood.demo.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import hackforgood.demo.sales.myapplication.R;
import hackforgood.demo.sales.util.AnimatedTextView;
import hackforgood.demo.sales.util.DelayUtil;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file SplashActivity.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class SplashActivity extends FragmentActivity {

    private AnimatedTextView animatedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        animatedTextView = (AnimatedTextView) findViewById(R.id.app_name);
        animatedTextView.setCharacterDelay(150);
        animatedTextView.animateText(getResources().getString(R.string.app_name));

        DelayUtil.postDelay(new DelayUtil.OnDelayListener() {
            @Override
            public void onDelayEnd() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2500);
    }
}
