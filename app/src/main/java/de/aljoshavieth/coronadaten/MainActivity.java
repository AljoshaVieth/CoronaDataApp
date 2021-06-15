package de.aljoshavieth.coronadaten;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CoronaDataService.CoronaDataCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoronaDataService coronaDataService = new CoronaDataService(this);
        coronaDataService.getDistrictKeys(this);
        coronaDataService.getDistrictData("10042",this);
    }

    @Override
    public void onDistrictKeysReceived(HashMap<String, String> districtKeys) {

    }

    @Override
    public void onDistrictDataReceived(DistrictData districtData) {

    }
}