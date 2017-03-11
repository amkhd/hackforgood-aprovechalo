package hackforgood.demo.sales.util;

import android.os.Handler;
/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file DelayUtil.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class DelayUtil {

    public interface OnDelayListener {
        void onDelayEnd();
    }

    public static final void postDelay(final OnDelayListener onDelayListener, long millis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onDelayListener.onDelayEnd();
            }
        }, millis);
    }
}
