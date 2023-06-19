package com.pucpr.atividadesomativa2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.controller.HomeActivity;
import com.pucpr.atividadesomativa2.controller.NewOrderService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderServiceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderServiceListFragment extends Fragment {

    private FloatingActionButton fab;
    public OrderServiceListFragment() {
        // Required empty public constructor
    }

    public static OrderServiceListFragment newInstance() {
        OrderServiceListFragment fragment = new OrderServiceListFragment();
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

        return view;
    }
}