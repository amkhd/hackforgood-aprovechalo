package hackforgood.demo.sales.interactors.events;

import java.util.List;

import hackforgood.demo.sales.entities.Offer;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file OfferListEvent.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class OfferListEvent {

    private List<Offer> offerList;

    public OfferListEvent(List<Offer> offerList) {
        this.offerList = offerList;
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
}
