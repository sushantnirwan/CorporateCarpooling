package carpool.corporatecarpooling.home.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import carpool.corporatecarpooling.*;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       // MainController.launchActivityPage(R.layout.activity_home);
        setContentView(R.layout.activity_home);
         }


    public void login_action(View view)
    {
        setContentView(R.layout.activity_login);
    }

    public void register_action(View view)
    {
        setContentView(R.layout.activity_registration);
    }



}
