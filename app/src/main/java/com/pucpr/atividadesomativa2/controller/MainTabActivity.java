package com.pucpr.atividadesomativa2.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.fragments.OrderServiceListFragment;

public class MainTabActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private NavigationBarView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnItemSelectedListener(this);

        Fragment listFragment = OrderServiceListFragment.newInstance(false);
        openFragment(listFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        count = false;
        int itemId = item.getItemId();
        if (itemId == R.id.order_service_list_tab) {
            Fragment listFragment = OrderServiceListFragment.newInstance(false);
            openFragment(listFragment);
        } else if (itemId == R.id.settings_tab2) {
            Fragment listFinishedFragment = OrderServiceListFragment.newInstance(true);
            openFragment(listFinishedFragment);
        }

        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    Boolean count = false;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (!count) {
            Fragment listFragment = OrderServiceListFragment.newInstance(false);
            openFragment(listFragment);
            navigationView.setSelectedItemId(R.id.order_service_list_tab);
            count = true;
        } else {
            finish();
        }


    }
}