package com.pucpr.atividadesomativa2.items;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.atividadesomativa2.R;

public class OrderServiceItem extends RecyclerView.ViewHolder {

    private TextView clientNameTextView, deviceTextView, detailsTextView;
    private View.OnClickListener onClickListener;

    public OrderServiceItem(@NonNull View itemView) {
        super(itemView);

        this.clientNameTextView = (TextView) itemView.findViewById(R.id.name_client_order);
        this.deviceTextView = (TextView) itemView.findViewById(R.id.device_service_order);
        this.detailsTextView = (TextView) itemView.findViewById(R.id.detail_service_order);
        this.onClickListener = onClickListener;
    }

    public TextView getClientNameTextView() {
        return clientNameTextView;
    }

    public TextView getDetailsTextView() {
        return detailsTextView;
    }

    public TextView getDeviceTextView() {
        return deviceTextView;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;

        this.itemView.setOnClickListener(onClickListener);
    }
}
