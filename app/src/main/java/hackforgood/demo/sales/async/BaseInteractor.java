package hackforgood.demo.sales.async;

import android.content.Context;

import hackforgood.demo.sales.BaseApplication;
import hackforgood.demo.sales.util.ENDPOINTS;
import retrofit.RestAdapter;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file BaseInteractor.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public abstract class BaseInteractor implements Runnable {

    public abstract void runOnBackground();

    public void execute(){
        Executor.execute(this);
    }

    @Override
    public void run() {
        try{
            runOnBackground();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Context getContext() {
        return BaseApplication.get();
    }

    public Request getRequest() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINTS.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();
        Request service = restAdapter
                .create(Request.class);
        return service;
    }

}
