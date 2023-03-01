package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.project.Adapter.LoginAdapter;
import com.example.project.Database.DBHelper;
import com.example.project.R;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
    TabLayout tab_layout;
    ViewPager view_pager;
    float alpha = 0;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =new DBHelper(this);
        db.initProductPopulation();




        // tab login and signup
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);

        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.login)));
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.signUp)));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);

        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tab_layout.getTabCount());
        view_pager.setAdapter(adapter);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Opacity
        tab_layout.setAlpha(alpha);

        tab_layout.animate().translationY(0).alpha(1).setDuration(300).setStartDelay(300).start();

    }
}