/*

Copyright (c) 2018 HERC SEZC


Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at


    http://www.apache.org/licenses/LICENSE-2.0


Unless required by applicable law or agreed to in writing, software

distributed under the License is distributed on an "AS IS" BASIS,

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and

limitations under the License.

*/

package com.feed;

import com.Instrument;
import com.Rate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CADFeed extends GenericFeed {

    public CADFeed() {
        setInstrument(new Instrument("CAD"));
        setRate(new Rate(getPrice(1)));        
    }
    
    static double value = 0;
    static double lastUpdate = 0;

    @Override
        
    public double getPrice(double units) {
        BufferedReader reader = null;
        try {
            
            if( (lastUpdate + 60000 ) > System.currentTimeMillis()){
                System.out.println("Use cached value");
                return value;
            }
            else {
                System.out.println("Use new value");
                
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String _date = df.format(new Date(System.currentTimeMillis()));
            String theurl = "https://free.currencyconverterapi.com/api/v5/convert?q=USD_CAD&compact=y";
            System.out.println(theurl);
            URL url = new URL(theurl);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String buf = "";
            String S = "";
            while ((buf = reader.readLine()) != null) {
                S += buf;
            }
            
            Gson gson = new Gson();
            JsonObject jobj = new Gson().fromJson(S, JsonObject.class);   
            JsonElement jee = jobj.get("USD_CAD");
            JsonElement je = jee.getAsJsonObject(); //getAsDouble();
            JsonObject jo = je.getAsJsonObject(); //getAsDouble();
            JsonElement jeee = jo.get("val");
            
            double d = jeee.getAsDouble();
            lastUpdate = System.currentTimeMillis();
            value = d;
            
            //Double rate = je.get("FXUSDCAD").getAsDouble();
            return d;//rate * units ;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
                ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
            }
        }
        return -1;
    }
    
    public static void main(String[] args){
        CADFeed cf = new CADFeed();
        System.out.println("CAD:" + cf.getPrice(1));
        System.out.println("CAD:" + cf.getPrice(1));
        System.out.println("CAD:" + cf.getPrice(1));
        try {
            Thread.sleep(65000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CADFeed.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CAD:" + cf.getPrice(1));
        
    }
}

