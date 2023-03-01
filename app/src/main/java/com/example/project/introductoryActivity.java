package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.project.Fragment.OnBoardingFrag1;
import com.example.project.Fragment.OnBoardingFrag2;
import com.example.project.Fragment.OnBoardingFrag3;

public class introductoryActivity extends AppCompatActivity {
    ImageView img_logo,slashImg;
    LottieAnimationView lottieAnimationView;
    TextView tv_title;

    private  static  final  int NUMBER_PAGES = 3;
    private ViewPager viewPager;
    private SlidePagerAdapter pagerAdapter;

    SharedPreferences shardP;
    private static  int ANIMATE_TIME = 4800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        img_logo = findViewById(R.id.img_logo);
        slashImg = findViewById(R.id.img_bg);
        lottieAnimationView = findViewById(R.id.lottie);
        tv_title = findViewById(R.id.tv_title);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        slashImg.animate().translationY(-5000).setDuration(1000).setStartDelay(3000);
        img_logo.animate().translationY(4000).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(4000).setDuration(1000).setStartDelay(3000);
        tv_title.animate().translationY(4000).setDuration(1000).setStartDelay(3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shardP = getSharedPreferences("firstIntro",MODE_PRIVATE);
                boolean isFrirst  = shardP.getBoolean("first1",true);
                if (isFrirst){
                    SharedPreferences.Editor editor = shardP.edit();
                    editor.putBoolean("first1",false);
                    editor.commit();
                }else{
                    Intent intent = new Intent(introductoryActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        },ANIMATE_TIME);


    }



    private class SlidePagerAdapter extends FragmentStatePagerAdapter{


        public SlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFrag1 t1 = new OnBoardingFrag1();
                    return t1;
                case 1:
                    OnBoardingFrag2 t2 = new OnBoardingFrag2();
                    return t2;
                case 2:
                    OnBoardingFrag3 t3 = new OnBoardingFrag3();
                    return t3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUMBER_PAGES;
        }
    }
}