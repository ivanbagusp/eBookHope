package com.example.ivanbaguspinuntun.ebookhope;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private ImageView logoSplash;
    private EditText editTextUsername, editTextPassword;
    private Button btnSubmit;
    private CheckBox showHidePassword;
    private Animation animation;
    private TextView forgetPass;

    SharedPreferences sharedPref;
    ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoSplash = (ImageView) findViewById(R.id.imageview_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.flying_logo);
        animation.setFillAfter(true);
        logoSplash.setAnimation(animation);
        Picasso.with(this).load(R.drawable.yourlogo).into(logoSplash);

        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginProgress = (ProgressBar) findViewById(R.id.loginProgress);
        loginProgress.setVisibility(View.GONE);

        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        PropertyValuesHolder trans = PropertyValuesHolder.ofFloat("translationY", 0, -180);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        ObjectAnimator edUsername = ObjectAnimator.ofPropertyValuesHolder(editTextUsername, alpha, trans);
        edUsername.setDuration(2000);
        edUsername.start();

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        ObjectAnimator edPass = ObjectAnimator.ofPropertyValuesHolder(editTextPassword, alpha, trans);
        edPass.setDuration(2200);
        edPass.start();

        btnSubmit = (Button) findViewById(R.id.button_submit);
        ObjectAnimator d = ObjectAnimator.ofPropertyValuesHolder(btnSubmit, alpha, trans);
        d.setDuration(2400);
        d.start();

        forgetPass = (TextView) findViewById(R.id.forgetPass);
        ObjectAnimator z = ObjectAnimator.ofPropertyValuesHolder(forgetPass, alpha, trans);
        z.setDuration(2600);
        z.start();

        showHidePassword = (CheckBox) findViewById(R.id.checkbox);
        ObjectAnimator f = ObjectAnimator.ofPropertyValuesHolder(showHidePassword, alpha, trans);
        f.setDuration(2200);
        f.start();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSubmit.setVisibility(View.INVISIBLE);
                loginProgress.setVisibility(View.VISIBLE);
                verifikasilogin(editTextUsername.getText().toString(), editTextPassword.getText().toString());
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        showHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showHidePassword.setButtonDrawable(R.drawable.hide);
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    showHidePassword.setButtonDrawable(R.drawable.show);
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    private void  verifikasilogin (String username, String password) {
        String url = "http://cloudofoasis.com/api/Ivan/loginHope.php?username="+ username + "&password=" + password;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.startsWith("sSis") || response.startsWith("sGur")){
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("login", true);
                    editor.putString("id", response);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else if (response.startsWith("g")){
                    Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                    btnSubmit.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Kesalahan koneksi, silahkan coba lagi",
                            Toast.LENGTH_SHORT).show();
                    btnSubmit.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(strRequest);
    }
}