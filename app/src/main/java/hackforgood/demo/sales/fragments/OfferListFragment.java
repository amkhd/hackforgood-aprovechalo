package hackforgood.demo.sales.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import hackforgood.demo.sales.ActivityInterface;
import hackforgood.demo.sales.adapters.OfferListAdapter;
import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.interactors.GetOffersInteractor;
import hackforgood.demo.sales.interactors.events.OfferListEvent;
import hackforgood.demo.sales.myapplication.R;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file CategoriesListFragment.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class OfferListFragment extends BaseFragment{

    private List<Offer> categories = new ArrayList<>();
    private OfferListAdapter offerListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.offerlist_fragment_layout, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        offerListAdapter = new OfferListAdapter((ActivityInterface) getActivity(), categories);
        recyclerView.setAdapter(offerListAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetOffersInteractor().execute();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) return;
        if (offerListAdapter != null) offerListAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OfferListEvent offerListEvent) {
        if (offerListAdapter == null) return;

        offerListAdapter.refreshData(offerListEvent.getOfferList());
        offerListAdapter.notifyDataSetChanged();
    }
}
