package hackforgood.demo.sales.interactors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import hackforgood.demo.sales.async.BaseInteractor;
import hackforgood.demo.sales.async.Request;
import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.entities.OfferDetails;
import hackforgood.demo.sales.interactors.events.OfferDetailsEvent;
import hackforgood.demo.sales.util.ENDPOINTS;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file GetOfferDetails.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class GetOfferDetails extends BaseInteractor {

    private Offer offer;

    public GetOfferDetails(Offer offer) {
        this.offer = offer;
    }

    @Override
    public void runOnBackground() {
        Request request = super.getRequest();

        String query = "?store_id=" + offer.getStore().getId() + "&offer_id=" + offer.getId();
        JsonElement jsonElement = request.startGetService(ENDPOINTS.GET_PRODUCTS_URL + query);
        Gson gson = new GsonBuilder().create();
        OfferDetails storeDetails = gson.fromJson(jsonElement, new TypeToken<OfferDetails>(){}.getType());
        EventBus.getDefault().post(new OfferDetailsEvent(storeDetails));
    }
}