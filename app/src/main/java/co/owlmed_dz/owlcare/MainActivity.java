package co.owlmed_dz.owlcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.owlmed_dz.owlcare.Model.UserAccount;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private UserAccount accountInfo;

    public static final int RC_SIGN_IN = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            mFirebaseAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Toast.makeText(MainActivity.this, "You are signed in", Toast.LENGTH_LONG).show();
                        //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                        //TO-DO Pass-On account information

                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);

                    } else {
                        // User is signed out
                        Toast.makeText(MainActivity.this, "You are NOT signed in", Toast.LENGTH_LONG).show();
                        //Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                }
            };
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Erreur Réseau", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void checkLogin(String email, String password) {

        // Check if they are correct and login
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void login(View view) {
        // Get entered logins
         String email = ((EditText) findViewById(R.id.input_email)).getText().toString();
         String password = ((EditText) findViewById(R.id.input_password)).getText().toString();
         checkLogin(email,password);
    }

    public void createAccount(View view) {

        // Get entered logins
        String email=((EditText)findViewById(R.id.input_email)).getText().toString();
        String password=((EditText)findViewById(R.id.input_password)).getText().toString();

        // Create account
        createAccountEmailPwd(email,password);

    }

    private void createAccountEmailPwd(String email, String password)
    {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
