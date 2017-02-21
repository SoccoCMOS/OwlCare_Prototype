package co.owlmed_dz.owlcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import co.owlmed_dz.owlcare.ApiElement.FitnessFeedback;
import co.owlmed_dz.owlcare.Model.FitnessInformation;
import co.owlmed_dz.owlcare.Model.UserAccount;

public class Fitness extends AppCompatActivity {

    private UserAccount accountInfo;
    private FitnessInformation fitnessInformation;
    private FitnessFeedback fitnessFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);
        //menu.setDisplayShowHomeEnabled(true);
        //menu.setLogo(R.drawable.logo_appbar);
        //menu.setDisplayUseLogoEnabled(true);

        loadGeneralInfo();
        fitnessFeedback=calculateFeedback(fitnessInformation);
        showFeedback(fitnessFeedback);
    }



    private void loadGeneralInfo() {
        // TO-DO: Load general information from UserAccount & FitnessInformation objects
    }

    private void showFeedback(FitnessFeedback fitnessFeedback) {
        //TO-DO: Shows the actual feedback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbarmenu, menu);
        return true;
    }

    public void showHistory(View view) {

        //TO-DO: Opens an activity where it shows the graph history of fitness information
    }

    public void refreshFeedback(View view) {

        //TO-DO: Changes the image of the feedback zone and refreshes the BMI & WtWr ratio values --> on listener
        //MAJ fitnessInformation
        //Calculate Feedback
        //Show Feedback
    }

                                /**** API Element ****/

    private FitnessFeedback calculateFeedback(FitnessInformation fitnessInformation) {
        //TO-DO: calculates BMI & WtWR & loads corresponding feedback
        FitnessFeedback processedFeedback=null;

        return processedFeedback;
    }
}
