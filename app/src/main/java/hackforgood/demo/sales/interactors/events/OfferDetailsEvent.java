package hackforgood.demo.sales.interactors.events;

import hackforgood.demo.sales.entities.OfferDetails;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file OfferDetailsEvent.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class OfferDetailsEvent {

    private OfferDetails offerDetails;

    public OfferDetailsEvent(OfferDetails offerDetails) {
        this.offerDetails = offerDetails;
    }

    public OfferDetails getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(OfferDetails offerDetails) {
        this.offerDetails = offerDetails;
    }
}
