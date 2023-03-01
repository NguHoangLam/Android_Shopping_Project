package com.example.project.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.Database.DBHelper;
import com.example.project.R;

public class SignUpTabFragment extends Fragment {
    EditText editusername,edit_phone,edit_password,edit_repassword;
    Button btnSignUp;
    TextView tv_error;
    float alpha = 0;
    DBHelper DB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        ViewGroup root = (ViewGroup)   inflater.inflate(R.layout.signup_tab_fragment,container,false);
        DB = new DBHelper(getActivity());
        editusername = root.findViewById(R.id.editusername);
        edit_password = root.findViewById(R.id.edit_password);
        edit_phone = root.findViewById(R.id.edit_phone);
        edit_repassword = root.findViewById(R.id.edit_repassword);
        btnSignUp = root.findViewById(R.id.btnSignUp);
        tv_error =root.findViewById(R.id.tv_error);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editusername.getText().toString();
                String pass = edit_password.getText().toString();
                String repass = edit_repassword.getText().toString();
                String phone = edit_phone.getText().toString();

                if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass)||TextUtils.isEmpty(phone)){
                    tv_error.setText("All fields Required");

                }else if (!pass.equals(repass)) {

                    tv_error.setText("password and repassword must be the same!");
                }else if (DB.checkUsername(user)){

                    tv_error.setText("Account Existed");
                }else{
                    Boolean insert = DB.insertAccountData(user,phone, pass);
                    if (insert) {
                        tv_error.setTextColor(Color.rgb(255, 0, 0));
                        tv_error.setText("Create Account Successful!");
                    }else{
                        tv_error.setText("Create Account Unsuccessfully!");
                    }

                }
            }

        });

        return root;
    }
}
