package com.lizyaver.lizyaverorg;

import static android.util.Patterns.EMAIL_ADDRESS;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.lizyaver.lizyaverorg.ui.login.LoginActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signup;
    private EditText email, fullname, phonenumber, pass, confirmPass;
    private ProgressBar progressBar;
    private Button register, btnButton;
    private FirebaseAuth mAuth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);

        btnButton = findViewById(R.id.cirLoginButton);
        btnButton.setOnClickListener(this);

        email = findViewById(R.id.txtemail);
        fullname = findViewById(R.id.fullnames);
        phonenumber = findViewById(R.id.phonenumber);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.cirLoginButton);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.loading);


    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.signup:
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.cirLoginButton:
                    registerUser();
                    break;
            }
    }

    private void registerUser() {
        String txtemail = email.getText().toString().trim();
        String txtfullnames = fullname.getText().toString().trim();
        String txtphonenumber = phonenumber.getText().toString().trim();
        String txtpassword = pass.getText().toString().trim();
        String txtconfirmpass = confirmPass.getText().toString().trim();


        if (txtemail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(txtemail).matches()){
            email.setError("Invalid Email");
            email.requestFocus();
            return;
        }
        if (txtfullnames.isEmpty()){
            fullname.setError("Name is required");
            fullname.requestFocus();
            return;
        }
        if (txtphonenumber.isEmpty()){
            phonenumber.setError("Phone Number is required");
            phonenumber.requestFocus();
            return;
        }
        if (txtpassword.isEmpty()){
            pass.setError("password is required");
            pass.requestFocus();
            return;
        }
        if (txtpassword.length() <= 6){
            pass.setError("Min characters is 6");
            pass.requestFocus();
            return;
        }
        if (txtconfirmpass.isEmpty()){
            confirmPass.setError("Confirm Password is required");
            confirmPass.requestFocus();
            return;
        }
        if (txtconfirmpass.length() <= 6){
            confirmPass.setError("Min characters is 6");
            confirmPass.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);


        if (txtpassword.equals(txtconfirmpass)){
            mAuth.createUserWithEmailAndPassword(txtemail, txtpassword)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()){
                            user = new User(txtfullnames, txtemail, txtphonenumber, txtpassword);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, " User has been Registered Successfully", Toast.LENGTH_LONG).show();
                                                //login page
                                            startActivity(new Intent(this, LoginActivity.class));
                                            progressBar.setVisibility(View.VISIBLE);
                                        }else {
                                            Toast.makeText(RegisterActivity.this, " Failed to register! try again", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });

                        }


                    });
        }else{
            confirmPass.setError("Password does not match");
            confirmPass.requestFocus();
            return;
        }

    }
}