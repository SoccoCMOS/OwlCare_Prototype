package co.owlmed_dz.owlcare;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Widad on 13-12-2016.
 */
public class Agenda_help extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_help);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.logo_appbar);
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





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbarmenu, menu);
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
