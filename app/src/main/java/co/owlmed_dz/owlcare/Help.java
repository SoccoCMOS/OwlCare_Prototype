package co.owlmed_dz.owlcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    ImageButton buttoninfo =null;
    ImageButton buttonbackground =null;
    ImageButton buttonmeasues =null;
    ImageButton buttonfollowUp =null;
    ImageButton buttonAgenda =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayUseLogoEnabled(true);

        TextView noteView = (TextView) findViewById(R.id.linkwebsite);
        Linkify.addLinks(noteView, Linkify.ALL);
        WebView view = (WebView) findViewById(R.id.textcontent);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "Owl MED is healthcare application that will montor you and help youto be safe all the time effecient service and flexible mobile app\n" +
                "        Owl MED is healthcare application that will montor you and help youto be safe all the time effecient service and flexible mobile app";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");
        view.setBackgroundColor(Color.parseColor("#dddddd"));



        buttoninfo = (ImageButton) findViewById(R.id.info);
        buttoninfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, information_help.class);
                startActivity(intent);
            }
        });

        buttonbackground = (ImageButton) findViewById(R.id.background);
        buttonbackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, background_help.class);
                startActivity(intent);
            }
        });
        buttonmeasues = (ImageButton) findViewById(R.id.measure);
        buttonmeasues.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, Measures_help.class);
                startActivity(intent);
            }
        });
        buttonfollowUp = (ImageButton) findViewById(R.id.followup);
        buttonfollowUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, followUp_help.class);
                startActivity(intent);
            }
        });


        buttonAgenda = (ImageButton) findViewById(R.id.agenda);
        buttonAgenda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, Agenda_help.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.appbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
