package hackforgood.demo.sales;

import android.content.Context;

import hackforgood.demo.sales.entities.Offer;

/**
 * Copyright (c) 2017  Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file ActivityInterface.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public interface ActivityInterface {
    void onSelectOffer(Offer offer);
    Context getApplicationContext();
    void selectOffer(Offer offer);
}
