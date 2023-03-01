package com.example.project.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project.Fragment.LoginTabFragment;
import com.example.project.Fragment.SignUpTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int allTabs;

    public  LoginAdapter(FragmentManager fm, Context context,int allTabs){
        super(fm);
        this.context = context;
        this.allTabs = allTabs;
    }
    public Fragment getItem(int position){
        switch (position){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignUpTabFragment signUpTabFragment = new SignUpTabFragment();
                return signUpTabFragment;
            default:
                return null;



        }


    }

    @Override
    public int getCount() {
        return allTabs;
    }
}
