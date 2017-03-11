package hackforgood.demo.sales.entities;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file Product.java
 * @Author Amine Khadmaoui
 * @date 10/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class Product {

    private int id;
    private String name;
    private String imgUrl;
    private String priceOffer;
    private String price;
    private String offerPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(String priceOffer) {
        this.priceOffer = priceOffer;
    }
}
