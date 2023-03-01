package com.example.project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.LoginActivity;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OnBoardingFrag3 extends Fragment  {
    FloatingActionButton next_action;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.onboarding_fragment3,container,false);

        next_action = root.findViewById(R.id.next_action);

        next_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);
            }
        });

        return root;
    }
}
