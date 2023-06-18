package com.pucpr.atividadesomativa2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pucpr.atividadesomativa2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderServiceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderServiceListFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_order_service_list, container, false);
    }
}