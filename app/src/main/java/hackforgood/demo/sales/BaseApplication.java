package hackforgood.demo.sales;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file BaseApplication.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class BaseApplication extends Application {

    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context get() {
        return instance;
    }
}
