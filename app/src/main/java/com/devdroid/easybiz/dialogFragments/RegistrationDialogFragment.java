package com.devdroid.easybiz.dialogFragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.databaseParameters.FirebaseNodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegistrationDialogFragment extends AppCompatDialogFragment {

    Button button;
    FirebaseAuth auth;
    EditText email,password,shopName;
    Context context;
    FirebaseDatabase database;
    DatabaseReference providerNode,registeredUsersNode;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        registeredUsersNode=FirebaseDatabase.getInstance().getReference().child(FirebaseNodes.USERS_NODE);
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        providerNode= database.getReference("Providers");
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.registration_layout,null);
        shopName= view.findViewById(R.id.etRegistrationName);
        email= view.findViewById(R.id.etRegistrationEmail);
        password= view.findViewById(R.id.etRegistrationPassword);
        builder.setView(view);
        button= view.findViewById(R.id.registrationSubmitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shopNameTxt=shopName.getText().toString();
                String emailTxt= email.getText().toString();
                String passwordTxt= password.getText().toString();
//                providerNode.child(shopNameTxt).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }

                auth.createUserWithEmailAndPassword(emailTxt,passwordTxt)
                        .addOnSuccessListener((Activity) context, new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Map<String,Object> loginCredentials= new HashMap<>();
                                loginCredentials.put(FirebaseNodes.EMAIL,emailTxt);
                                loginCredentials.put(FirebaseNodes.SHOP_NAME,shopNameTxt);
                                registeredUsersNode.child(shopNameTxt).setValue(loginCredentials);
                                Toast.makeText(context, "Shop Successfully Registered", Toast.LENGTH_SHORT).show();
                                email.getText().clear();
                                password.getText().clear();
                                shopName.getText().clear();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context= context;
    }
}