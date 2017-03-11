package hackforgood.demo.sales.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hackforgood.demo.sales.entities.Product;
import hackforgood.demo.sales.myapplication.R;

/**
 * Copyright (c) 2017 Todos los derechos reservados.
 * Documento Confidencial.
 *
 * @file ProductListAdapter.java
 * @Author Amine Khadmaoui
 * @date 11/3/17,
 * @brief Breve descripción del archivo
 * <p>
 * Descripción detallada del archivo
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder> {

    Context mContext;
    List<Product> productList = new ArrayList<>();

    public ProductListAdapter(Context context, List<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    public void refreshData(List<Product> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        notifyDataSetChanged();
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productlist_item, viewGroup, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder viewHolder, int position) {
        viewHolder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productOfferPrice;
        private TextView productPrice;
        private String oldPriceText;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.product_imageView);
            productOfferPrice = (TextView) itemView.findViewById(R.id.product_offer_price);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            oldPriceText = mContext.getString(R.string.old_price_desc);
        }

        public void bind(Product product) {
            productOfferPrice.setText(product.getOfferPrice());
            productPrice.setText(oldPriceText + product.getPrice());
            Picasso.with(mContext)
                    .load(product.getImgUrl())
                    .into(productImage);
        }
    }


}