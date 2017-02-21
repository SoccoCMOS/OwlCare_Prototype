package co.owlmed_dz.owlcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import co.owlmed_dz.owlcare.Model.UserAccount;

public class Dashboard extends AppCompatActivity {

    private UserAccount accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.logo_appbar);
        menu.setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbarmenu, menu);
        return true;
    }

    public void fitness(View view) {
        Intent intent = new Intent(this,Fitness.class);
        startActivity(intent);
    }

    public void contacts(View view) {
        Intent intent = new Intent(this,FollowUp.class);
        startActivity(intent);
    }

    public void monitoring(View view) {
        Intent intent = new Intent(this,Monitoring.class);
        startActivity(intent);
    }


    public void help(View view) {
        Intent intent = new Intent(this,Help.class);
        startActivity(intent);
    }
}
