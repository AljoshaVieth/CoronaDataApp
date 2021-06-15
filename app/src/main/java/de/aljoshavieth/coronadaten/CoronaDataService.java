package de.aljoshavieth.coronadaten;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class CoronaDataService {
    private final CoronaDataCallback coronaDataCallback;

    public CoronaDataService(CoronaDataCallback coronaDataCallback) {
        this.coronaDataCallback = coronaDataCallback;
    }

    public void getDistrictKeys(Context context) {
        String url = "https://api.aj-v.de/germandistricts/districtKeys.json";
        HashMap<String, String> districtKeysMap = new HashMap<>();
        JsonObjectRequest districtDataRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject districtKeys = response.getJSONObject("districtKeys");
                    Iterator<String> keys = districtKeys.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        districtKeysMap.put(districtKeys.getString(key), key);
                    }
                    Log.i("CoronaDataApp", districtKeys.keys().next());
                    coronaDataCallback.onDistrictKeysReceived(districtKeysMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("CoronaDataApp", "Received response for districtKeys");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CoronaDataApp", "An error occured: " + error.getMessage());
            }
        }
        );
        ApiHandler.getInstance(context).addToRequestQueue(districtDataRequest);
    }

    public void getDistrictData(String districtKey, Context context) {
        DistrictData districtData = new DistrictData();

        String url = "https://api.corona-zahlen.org/districts/" + districtKey;
        JsonObjectRequest districtDataRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject districtDataFromJSON = response.getJSONObject("data").getJSONObject(districtKey);

                            districtData.setDistrictName(districtDataFromJSON.getString("name"));
                            districtData.setPopulation(districtDataFromJSON.getInt("population"));
                            districtData.setCases(districtDataFromJSON.getInt("cases"));
                            districtData.setDeaths(districtDataFromJSON.getInt("deaths"));
                            districtData.setCasesPerWeek(districtDataFromJSON.getInt("casesPerWeek"));
                            districtData.setDeathsPerWeek(districtDataFromJSON.getInt("deathsPerWeek"));
                            districtData.setRecovered(districtDataFromJSON.getInt("recovered"));
                            districtData.setWeekIncidence(districtDataFromJSON.getDouble("weekIncidence"));
                            districtData.setCasesPer100k(districtDataFromJSON.getDouble("casesPer100k"));

                            coronaDataCallback.onDistrictDataReceived(districtData);
                            Log.i("CoronaDataApp", districtData.getDistrictName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("CoronaDataApp", "Response received");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CoronaDataApp", "An error occured: " + error.getMessage());
                    }
                });
        Log.i("CoronaDataApp", "Adding to queue");
        ApiHandler.getInstance(context).addToRequestQueue(districtDataRequest);
    }

    public interface CoronaDataCallback {
        void onDistrictKeysReceived(HashMap<String, String> districtKeys);
        void onDistrictDataReceived(DistrictData districtData);
    }
}
