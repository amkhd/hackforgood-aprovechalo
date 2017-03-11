package hackforgood.demo.sales.interactors.events;

import java.util.List;

import hackforgood.demo.sales.entities.Store;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file StoreListEvent.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class StoreListEvent {

    private List<Store> storeList;

    public StoreListEvent(List<Store> storeList) {
        this.storeList = storeList;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }
}
