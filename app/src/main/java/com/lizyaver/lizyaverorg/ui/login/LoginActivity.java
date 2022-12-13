package com.lizyaver.lizyaverorg.ui.login;


import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.lizyaver.lizyaverorg.Dashboard;
import com.lizyaver.lizyaverorg.R;
import com.lizyaver.lizyaverorg.RegisterActivity;
import com.lizyaver.lizyaverorg.User;
import com.lizyaver.lizyaverorg.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private LoginViewModel loginViewModel;
    private TextView register;
    private EditText email, password;
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private Button login;
    private User user;
    private ProgressBar progressBar;
    private TextInputLayout usernameEditText, passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        mAuth = FirebaseAuth.getInstance();

         usernameEditText = findViewById(R.id.username);
         passwordEditText = findViewById(R.id.password);

         email = findViewById(R.id.txt_email);
         password = findViewById(R.id.txtpassword);


        progressBar = findViewById(R.id.loading);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);


        register = findViewById(R.id.new_register);
        register.setOnClickListener(this);


        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

//        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
//            if (loginFormState == null) {
//                return;
//            }
//            loginButton.setEnabled(loginFormState.isDataValid());
//            if (loginFormState.getUsernameError() != null) {
//                usernameEditText.setError(getString(loginFormState.getUsernameError()));
//            }
//            if (loginFormState.getPasswordError() != null) {
//                passwordEditText.setError(getString(loginFormState.getPasswordError()));
//            }
//        });
//
//        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
//            @Override
//            public void onChanged(@Nullable LoginResult loginResult) {
//                if (loginResult == null) {
//                    return;
//                }
//                loadingProgressBar.setVisibility(View.GONE);
//                if (loginResult.getError() != null) {
//                    showLoginFailed(loginResult.getError());
//                }
//                if (loginResult.getSuccess() != null) {
//                    updateUiWithUser(loginResult.getSuccess());
//                }
//                setResult(Activity.RESULT_OK);
//
//                //Complete and destroy login activity once successful
//                finish();
//            }
//        });
//
//        usernameEditText.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //ignore
//                if (charSequence.length() == 0 ) {
//                    usernameEditText.setError("Username is required");
//                    usernameEditText.setErrorEnabled(true);
//                } else if (charSequence.length() < 5 ) {
//                    usernameEditText.setError("Username is required and length must be >= 5");
//                    usernameEditText.setErrorEnabled(true);
//                } else {
//                    usernameEditText.setErrorEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                loginViewModel.loginDataChanged(usernameEditText.getEditText().toString(),
//                        passwordEditText.getEditText().toString());
//            }
//        });
//
//        passwordEditText.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                loginViewModel.loginDataChanged(usernameEditText.getEditText().toString(),
//                        passwordEditText.getEditText().toString());
//            }
//        });
//
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getEditText().toString(),
//                        passwordEditText.getEditText().toString());
//            }
//        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + binding.username.getContext();
        // TODO : initiate successful logged in experience

            Login();

        finish();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.new_register:
                    startActivity(new Intent(this, RegisterActivity.class));
                    break;
                case R.id.login:
                    Login();
                    break;
            }
    }

    private void Login() {
        String  txtemail = email.getText().toString().trim();
        String txtpassword = password.getText().toString().trim();

        if (txtemail.isEmpty()){
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        if (txtpassword.isEmpty()){
            password.setError("Enter Password");
            password.requestFocus();
            return;
        }
        if (txtpassword.length() <= 6){
            password.setError("Min characters is 6");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(txtemail, txtpassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        startActivity(new Intent(this, Dashboard.class));
                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}