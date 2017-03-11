package hackforgood.demo.sales.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import hackforgood.demo.sales.adapters.ProductListAdapter;
import hackforgood.demo.sales.entities.OfferDetails;
import hackforgood.demo.sales.entities.Product;
import hackforgood.demo.sales.interactors.GetOfferDetails;
import hackforgood.demo.sales.interactors.events.OfferDetailsEvent;
import hackforgood.demo.sales.interactors.events.OfferEvent;
import hackforgood.demo.sales.myapplication.R;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file CardListFragment.java
 * @Author Amine Khadmaoui
 * @date 10/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class ProductListFragment extends BaseFragment {

    private List<Product> productList = new ArrayList<>();
    private ProductListAdapter productListAdapter;
    private ImageView mainOfferImageView;
    private TextView mainOfferPrice;
    private TextView mainOfferPriceOffer;
    private TextView mainOfferName;
    private TextView mainOfferStoreName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.offer_details_fragment_layout, container, false);

        mainOfferImageView = (ImageView) rootView.findViewById(R.id.offer_details_imageview);
        mainOfferName = (TextView) rootView.findViewById(R.id.offer_details_name);
        mainOfferPrice = (TextView) rootView.findViewById(R.id.offer_details_price);
        mainOfferPriceOffer = (TextView) rootView.findViewById(R.id.offer_details_priceOffer);
        mainOfferStoreName = (TextView) rootView.findViewById(R.id.offer_details_store_name);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        productListAdapter = new ProductListAdapter(getActivity().getApplicationContext(), productList);
        recyclerView.setAdapter(productListAdapter);
        return rootView;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) return;
        if (productListAdapter != null) productListAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OfferDetailsEvent offerDetailsEvent) {
        if (productListAdapter == null) return;

        OfferDetails offerDetails = offerDetailsEvent.getOfferDetails();
        Product mainProduct = offerDetails.getStore().getProducts().get(0);
        mainOfferStoreName.setText(offerDetails.getStore().getName());
        mainOfferName.setText(mainProduct.getName());
        mainOfferPrice.setText(mainProduct.getPrice());
        mainOfferPriceOffer.setText(offerDetails.getOffer().getOfferPrice());
        Picasso.with(getActivity().getApplicationContext())
                .load(mainProduct.getImgUrl())
                .into(mainOfferImageView);

        offerDetails.getStore().getProducts().remove(0);
        productListAdapter.refreshData(offerDetails.getStore().getProducts());
        productListAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OfferEvent offerEvent) {
        if (productListAdapter == null) return;
        new GetOfferDetails(offerEvent.getOffer()).execute();
    }
}
