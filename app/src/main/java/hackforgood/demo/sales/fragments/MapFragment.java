package hackforgood.demo.sales.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.entities.Store;
import hackforgood.demo.sales.interactors.GetOfferDetails;
import hackforgood.demo.sales.interactors.GetStoresInteractors;
import hackforgood.demo.sales.interactors.events.OfferEvent;
import hackforgood.demo.sales.interactors.events.StoreListEvent;
import hackforgood.demo.sales.myapplication.R;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file MapFragment.java
 * @Author Amine Khadmaoui
 * @date 10/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;
    private List<Store> storeList;
    private LatLng VALLADOLID_LOCATION = new LatLng(41.6522966, -4.7285413);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.map_fragment_layout, container, false);

        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        new GetStoresInteractors().execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        MapsInitializer.initialize(this.getActivity());

        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newLatLngZoom(VALLADOLID_LOCATION, 14);
        map.animateCamera(cameraUpdate);
        map.addCircle(new CircleOptions()
                .center(VALLADOLID_LOCATION)
                .radius(1000));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StoreListEvent storeListEvent) {
        this.storeList = storeListEvent.getStoreList();
        map.clear();

        for(Store store: storeList) {
            map.addMarker(new MarkerOptions()
                    .position(store.getLocation())
                    .title(store.getName()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OfferEvent offerEvent) {
        map.clear();
        Store store = offerEvent.getOffer().getStore();
        map.addMarker(new MarkerOptions()
                .position(store.getLocation())
                .title(store.getName()));
    }
}
