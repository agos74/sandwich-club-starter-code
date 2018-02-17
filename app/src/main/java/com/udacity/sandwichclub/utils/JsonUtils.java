package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject("name");

            String mainName = nameJson.getString("mainName");

            JSONArray alsoKnownAs = nameJson.getJSONArray("alsoKnownAs");
            ArrayList<String> arrayListAlsoKnownAs = getArrayListFromJsonArray(alsoKnownAs);

            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");

            JSONArray ingredients = sandwichJson.getJSONArray("ingredients");
            ArrayList<String> arrayListIngredients = getArrayListFromJsonArray(ingredients);

            sandwich = new Sandwich(mainName, arrayListAlsoKnownAs, placeOfOrigin, description, image, arrayListIngredients);

            //for debug purpose
            Log.d("Sandwich", sandwich.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    //method to get ArrayList<String> from JSONArray, for multiple use (2 times in this case)
    private static ArrayList<String> getArrayListFromJsonArray(JSONArray jsonArray) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(jsonArray.getString(i));
            }
        }
        return arrayList;
    }
}
