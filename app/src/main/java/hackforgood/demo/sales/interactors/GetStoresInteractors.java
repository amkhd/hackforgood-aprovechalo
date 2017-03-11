package hackforgood.demo.sales.interactors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import hackforgood.demo.sales.async.BaseInteractor;
import hackforgood.demo.sales.async.Request;
import hackforgood.demo.sales.entities.Store;
import hackforgood.demo.sales.interactors.events.StoreListEvent;
import hackforgood.demo.sales.util.ENDPOINTS;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file GetStoresInteractors.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class GetStoresInteractors extends BaseInteractor {

    @Override
    public void runOnBackground() {
        Request request = super.getRequest();
        JsonElement jsonElement = request.startGetService(ENDPOINTS.GET_STORES_URL);
        Gson gson = new GsonBuilder().create();
        List<Store> storeList = gson.fromJson(jsonElement, new TypeToken<List<Store>>(){}.getType());
        EventBus.getDefault().post(new StoreListEvent(storeList));
    }
}
