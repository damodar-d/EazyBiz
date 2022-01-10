package com.devdroid.easybiz.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devdroid.easybiz.MainActivity;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.codes.RequestCodes;
import com.devdroid.easybiz.databaseParameters.FirebaseNodes;
import com.devdroid.easybiz.dialogFragments.RegistrationDialogFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseExceptionMapper;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;



public class StartActivity extends AppCompatActivity {

    private AlertDialog.Builder signUpDialog;
    private Button loginSubmit;
    private DatabaseReference registeredUsersNode;
    private EditText loginEmail,loginPassword,loginShopName;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private RegistrationDialog registrationDialog;
    private TextView tvForgotPassword,tvNotRegisteredYet;
    private String shopNameToPassed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setupDatabase();
        initViews();
        registrationDialog= new RegistrationDialog(this);
        registrationDialog.buildDialog();
        signUpDialog= buildDoYouWantToSignUpDialog();

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shopNAmeData=loginShopName.getText().toString();
                String emailData= loginEmail.getText().toString();
                String passwordData=loginPassword.getText().toString();

                if(TextUtils.isEmpty(emailData) || TextUtils.isEmpty(passwordData))
                {
                    Toast.makeText(StartActivity.this, "Incomplete Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login(shopNAmeData.trim(),emailData.trim(),passwordData.trim());
                }
            }
        });

        tvNotRegisteredYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        RegistrationDialogFragment fragment= new RegistrationDialogFragment();
        fragment.show(getSupportFragmentManager(),fragment.getTag());
    }


    private void login(String shopName,String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
//                Map<String,Object> loginCredentials= new HashMap<>();
//                loginCredentials.put(FirebaseNodes.EMAIL,email);
//                loginCredentials.put(FirebaseNodes.SHOP_NAME,shopName);
//                registeredUsersNode.setValue(loginCredentials);
                Toast.makeText(StartActivity.this, "Welcome! "+email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                intent.putExtra(RequestCodes.INTENT_EXTRA_SHOP_NAME,shopName);
                startActivity(intent);
            }
        })
                .addOnFailureListener(StartActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(StartActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder= new AlertDialog.Builder(StartActivity.this);
                        View view=LayoutInflater.from(StartActivity.this).inflate(R.layout.registration_layout,null);
                        AlertDialog alertDialog= builder.create();
                        alertDialog.setContentView(view);
                        alertDialog.show();
                    }
                });
    }

    private AlertDialog.Builder buildDoYouWantToSignUpDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Sign Up ?")
                .setMessage("It looks like you haven't Signed Up With Us\n" +
                        "Do You Want to Sign Up ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   //TODO: Show the Registration Fragment

                        registrationDialog.showDialog();
                    }
                });

    }


    private void initViews(){

        loginEmail= findViewById(R.id.etLoginEmail);
        loginPassword= findViewById(R.id.etLoginPassword);
        loginShopName= findViewById(R.id.etLoginShopName);
        loginSubmit= findViewById(R.id.loginSubmit);
        tvForgotPassword=findViewById(R.id.tvForgotPassword);
        tvNotRegisteredYet= findViewById(R.id.tvNotRegisteredYet);
    }

    private void setupDatabase()
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        registeredUsersNode= firebaseDatabase.getReference().child(FirebaseNodes.USERS_NODE);
        auth= FirebaseAuth.getInstance();

    }
}