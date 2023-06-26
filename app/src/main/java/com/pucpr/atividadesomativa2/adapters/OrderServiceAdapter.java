package com.pucpr.atividadesomativa2.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.items.OrderServiceItem;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

import java.util.ArrayList;

public class OrderServiceAdapter extends RecyclerView.Adapter<OrderServiceItem> {

    public interface OrderServiceAdapterInterface {
        public void didSelectOrder(OrderService orderService);
    }

    private ArrayList<OrderService> orderServices = new ArrayList<>();
    private OrderServiceAdapterInterface orderServiceAdapterInterface;

    public OrderServiceAdapter(ArrayList<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public void setOrderServiceAdapterInterface(OrderServiceAdapterInterface orderServiceAdapterInterface) {
        this.orderServiceAdapterInterface = orderServiceAdapterInterface;
    }

    @NonNull
    @Override
    public OrderServiceItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.order_service_item, parent, false);
        OrderServiceItem item = new OrderServiceItem(listItem);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderServiceItem holder, int position) {
        OrderService orderService = this.orderServices.get(position);
        holder.getClientNameTextView().setText(orderService.getClient());
        holder.getDeviceTextView().setText(orderService.getDevice());
        holder.getDetailsTextView().setText(orderService.getDetail());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderServiceAdapterInterface.didSelectOrder(orderService);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.orderServices.size();
    }
}
