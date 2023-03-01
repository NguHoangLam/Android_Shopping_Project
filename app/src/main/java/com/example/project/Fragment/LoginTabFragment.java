package com.example.project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.Database.DBHelper;
import com.example.project.Entity.Account;
import com.example.project.MainPageActivity;
import com.example.project.R;

public class LoginTabFragment extends Fragment {

    EditText editusername,edit_password;
    Button btnLogin;
    TextView tv_errorLogin;
    CheckBox chk_remember;
    float alpha = 0;
    DBHelper DB;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String USERNAME_KEY = "user";
    String PASSWORD_KEY = "pass";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        ViewGroup root = (ViewGroup)   inflater.inflate(R.layout.login_tab_fragment,container,false);

        editusername = root.findViewById(R.id.editusername);
        edit_password = root.findViewById(R.id.edit_password);
        btnLogin = root.findViewById(R.id.btnLogin);
        chk_remember = root.findViewById(R.id.chk_remember);
        tv_errorLogin= root.findViewById(R.id.tv_errorLogin);

        editusername.setTranslationX(800);
        edit_password.setTranslationX(800);
        btnLogin.setTranslationX(800);
        chk_remember.setTranslationX(800);


        editusername.setAlpha(alpha);
        edit_password.setAlpha(alpha);
        btnLogin.setAlpha(alpha);
        chk_remember.setAlpha(alpha);

        editusername.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        edit_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        chk_remember.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();

        DB = new DBHelper(getActivity());
        sharedPreferences =  this.getActivity().getSharedPreferences("LoginShPr", Context.MODE_PRIVATE);

        editusername.setText(sharedPreferences.getString(USERNAME_KEY,""));
        edit_password.setText(sharedPreferences.getString(PASSWORD_KEY,""));
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editusername.getText().toString();
                String pass = edit_password.getText().toString();

                if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass)){
                    tv_errorLogin.setText("All fields Required");
                }else if (DB.checkUsernamePassword(user,pass)){

                    if (chk_remember.isChecked()){
                        editor = sharedPreferences.edit();
                        editor.putString(USERNAME_KEY,user);
                        editor.putString(PASSWORD_KEY,pass);
                        editor.commit();
                    }
                    Intent intent = new Intent(getActivity(), MainPageActivity.class);
                    Bundle bundle =new Bundle();
                    Account account = DB.getAccount(user,pass);
                    bundle.putSerializable("account",account);
                    intent.putExtra("MyPackage",bundle);
                    startActivity(intent);
                }else {
                    tv_errorLogin.setText("Invalid Account!");
                }
            }
        });


        return root;
    }

}
