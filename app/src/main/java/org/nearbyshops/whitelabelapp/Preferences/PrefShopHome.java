package org.nearbyshops.whitelabelapp.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import org.nearbyshops.whitelabelapp.Model.Shop;
import org.nearbyshops.whitelabelapp.MyApplication;
import org.nearbyshops.whitelabelapp.R;
import org.nearbyshops.whitelabelapp.Utility.UtilityFunctions;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sumeet on 19/10/16.
 */



public class PrefShopHome {



    public static void saveShop(Shop shop, Context context)
    {
        context = MyApplication.getAppContext();

        if(context == null)
        {
            return;
        }

        //Creating a shared preference

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_name), MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPref.edit();


        Gson gson = UtilityFunctions.provideGson();
        String json = gson.toJson(shop);
        prefsEditor.putString("shop", json);

        prefsEditor.apply();
    }


    public static Shop getShop(Context context)
    {
        context = MyApplication.getAppContext();


        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_name), MODE_PRIVATE);

        Gson gson = UtilityFunctions.provideGson();
        String json = sharedPref.getString("shop", null);

        return gson.fromJson(json, Shop.class);
    }


    public static String getCurrencySymbolForShop(Context context)
    {
        Shop shop = getShop(context);
        return UtilityFunctions.getCurrencySymbolFromISOCountryCode(shop.getRt_iso_country_code());
    }

}
