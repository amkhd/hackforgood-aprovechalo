package hackforgood.demo.sales.interactors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import hackforgood.demo.sales.async.BaseInteractor;
import hackforgood.demo.sales.async.Request;
import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.interactors.events.OfferListEvent;
import hackforgood.demo.sales.util.ENDPOINTS;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file GetOffersInteractor.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class GetOffersInteractor extends BaseInteractor {

    @Override
    public void runOnBackground() {
        Request request = super.getRequest();
        JsonElement jsonElement = request.startGetService(ENDPOINTS.GET_OFFERS_URL);
        Gson gson = new GsonBuilder().create();
        List<Offer> offerList = gson.fromJson(jsonElement, new TypeToken<List<Offer>>(){}.getType());
        EventBus.getDefault().post(new OfferListEvent(offerList));
    }
}
