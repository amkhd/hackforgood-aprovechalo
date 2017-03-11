package hackforgood.demo.sales.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import hackforgood.demo.sales.ActivityInterface;
import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.interactors.events.OfferEvent;
import hackforgood.demo.sales.myapplication.R;
import hackforgood.demo.sales.util.TimeFormatter;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file CategoryListAdapter.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.CategoriesViewHolder> {

    public static final int NOT_SELECTED = 0;
    public static final int SELECTED = 1;

    ActivityInterface activityInterface;
    List<Offer> offers = new ArrayList<>();
    private int selectedPosition = 0;

    public OfferListAdapter(ActivityInterface activityInterface, List<Offer> offers) {
        this.activityInterface = activityInterface;
        this.offers = offers;
    }

    @Override
    public OfferListAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutId = viewType == SELECTED ? R.layout.offerlist_item_selected : R.layout.offerlist_item;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        return new OfferListAdapter.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferListAdapter.CategoriesViewHolder viewHolder, int position) {
        viewHolder.bind(offers.get(position));
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    @Override
    public int getItemViewType(int position){
        return offers.get(position).isSelected() ? SELECTED : NOT_SELECTED;
    }

    public void refreshData(List<Offer> offerList) {
        this.offers = offerList;
        notifyDataSetChanged();
        EventBus.getDefault().post(new OfferEvent(offerList.get(0)));
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{

        private TextView name;
        private TextView storeName;
        private ImageView imageView;
        private TextView priceText;
        private TextView offerTime;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setFocusable(true);
            itemView.setFocusableInTouchMode(true);
            itemView.setOnTouchListener(this);
            name = (TextView) itemView.findViewById(R.id.name);
            storeName = (TextView) itemView.findViewById(R.id.store_name);
            priceText = (TextView) itemView.findViewById(R.id.offer_price);
            offerTime = (TextView) itemView.findViewById(R.id.offer_time);
            imageView = (ImageView) itemView.findViewById(R.id.offer_imageview);
        }

        public void bind(Offer offer) {
            name.setText(offer.getProduct().getName());
            storeName.setText(offer.getStore().getName());
            priceText.setText(offer.getOfferPrice());
            offerTime.setText(TimeFormatter.getTimeDiffDescription(offer.getWhen()));
            Picasso.with(activityInterface.getApplicationContext())
                    .load(offer.getProduct().getImgUrl())
                    .into(imageView);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_UP) return false;
            int position = getAdapterPosition();
            Offer offer = offers.get(position);

            offer.setSelected(!offer.isSelected());
            notifyItemChanged(position);

            if (position != selectedPosition) {
                offers.get(selectedPosition).setSelected(false);
                notifyItemChanged(selectedPosition);
            }
            selectedPosition = position;
            activityInterface.onSelectOffer(offers.get(getAdapterPosition()));
            EventBus.getDefault().post(new OfferEvent(offer));
            return false;
        }
    }


}