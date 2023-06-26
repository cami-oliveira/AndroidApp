package com.pucpr.atividadesomativa2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.adapters.OrderServiceAdapter;
import com.pucpr.atividadesomativa2.controller.HomeActivity;
import com.pucpr.atividadesomativa2.controller.MainActivity;
import com.pucpr.atividadesomativa2.controller.MainTabActivity;
import com.pucpr.atividadesomativa2.controller.NewOrderService;
import com.pucpr.atividadesomativa2.controller.OrderDetailsActivity;
import com.pucpr.atividadesomativa2.database.OrderServiceDataBase;
import com.pucpr.atividadesomativa2.model.Constants;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderServiceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderServiceListFragment extends Fragment implements OrderServiceAdapter.OrderServiceAdapterInterface {

    private boolean shouldShowFinishedOrders;
    private ArrayList<OrderService> orderServices = new ArrayList();
    private OrderServiceAdapter orderServiceAdapter;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    public OrderServiceListFragment(boolean shouldShowFinishedOrders) {
        this.shouldShowFinishedOrders = shouldShowFinishedOrders;
    }

    public static OrderServiceListFragment newInstance(boolean shouldShowFinishedOrders) {
        OrderServiceListFragment fragment = new OrderServiceListFragment(shouldShowFinishedOrders);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_service_list, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewOrderService.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.order_service_recycler_view);

        if (this.shouldShowFinishedOrders) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fab.setVisibility(FloatingActionButton.GONE);
                }
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.findAllOrderService();
        this.bindOrderServiceAdapter();
    }

    private void findAllOrderService() {
        if (this.shouldShowFinishedOrders) {
            this.setOrderServices(DataModel.getInstance().getFinishedOrderServices());
        } else {
            this.setOrderServices(DataModel.getInstance().getOrderServices());
        }
    }

    public void setOrderServices(ArrayList<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public ArrayList<OrderService> getOrderServices() {
        return orderServices;
    }

    public void bindOrderServiceAdapter(){
        orderServiceAdapter =  new OrderServiceAdapter(this.orderServices);
        orderServiceAdapter.setOrderServiceAdapterInterface(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(orderServiceAdapter);
    }

    @Override
    public void didSelectOrder(OrderService orderService) {
        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
        intent.putExtra(Constants.ORDER_SERVICE, orderService);
        startActivity(intent);
    }
}