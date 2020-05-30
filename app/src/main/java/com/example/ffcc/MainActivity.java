package com.example.ffcc;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText phone,code;
    TextView time;
    String codesent,cod;
    Button login,send;
    SharedPreferences shared;
    String verified;
    static Subjects[] respo;

    static HashMap<String,Integer> cre;
     AlertDialog.Builder builder;
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    public void logo(View v)
    {
        try {
            cod = code.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, cod);
            signInWithPhoneAuthCredential(credential);
        }
        catch (Exception e)
        {
            Log.i("The error:",e.getMessage());
        }
    }
    public void clickStartVerification(View v){
        String z=phone.getText().toString();
        if(z==null)
        {
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(z.charAt(0)=='+' ) {
                if(z.length()==13 || z.length()==16) {
                    getOtp(z);
                    time.setVisibility(View.VISIBLE);
                    CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {

                        @Override
                        public void onTick(long millis) {

                            time.setText("Enter OTP manually in "+ millis / 1000 + " seconds");

                        }

                        @Override
                        public void onFinish() {
                            code.setVisibility(View.VISIBLE);
                            login.setVisibility(View.VISIBLE);
                            time.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }
                else {
                    Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {   if(z.length()==10 || z.length()==13) {
                getOtp("+91" + z);
                time.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {

                    @Override
                    public void onTick(long millis) {

                        time.setText("Enter OTP manually in "+ millis / 1000 + " seconds");

                    }

                    @Override
                    public void onFinish() {
                        code.setVisibility(View.VISIBLE);
                        login.setVisibility(View.VISIBLE);
                        time.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
            else {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
            }
            }

        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            shared.edit().putString("v","YES").apply();
                            start();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(MainActivity.this, "Login UnSuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void getOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private void setMobileDataEnabled(Context context, boolean enabled) {
        try{
            final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
            connectivityManagerField.setAccessible(true);
            final Object connectivityManager = connectivityManagerField.get(conman);
            final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);}
    catch (Exception e)
    {
        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    }
    void maker()
    {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet!").setMessage("Connect to Wifi or Mobile data").setCancelable(false).setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!isConnected())
                {
                    maker();

                }
                else{
                    creator();
                }

            }
        });
        builder.create();
        builder.show();
    }
    void creator()
    {
        try {
            mAuth = FirebaseAuth.getInstance();
            shared = this.getSharedPreferences("com.example.ffcc", Context.MODE_PRIVATE);
            phone = findViewById(R.id.phone);
            code = findViewById(R.id.code);
            time = findViewById(R.id.time);
            login = findViewById(R.id.login);
            try {
                verified = shared.getString("v", "NO");
            } catch (Exception e) {
                Log.i("The error in saving is:", e.getMessage());
            }
            if (verified.equals("YES")) {
                start();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error in creator", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cre=new HashMap<>();
        if (isConnected() == false) {
            maker();
        }
        else
        {
            creator();
        }

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Toast.makeText(MainActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
            shared.edit().putString("v", "YES").apply();
            start();

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(MainActivity.this, "Code has been sent", Toast.LENGTH_SHORT).show();
            Log.i("The s value:", s);
            codesent = s;
        }
    };
    void start()
    {
        Intent I=new Intent(MainActivity.this,Main.class);
        startActivity(I);
    }
}
