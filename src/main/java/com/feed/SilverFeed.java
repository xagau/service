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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SilverFeed extends GenericFeed {

    public SilverFeed() {
        setInstrument(new Instrument("XAG"));
        setRate(new Rate(getPrice(1)));        
    }
    @Override
    public double getPrice(double units) {
        BufferedReader reader = null;
        try {
            String u = "https://www.bellaverage.com/api/baRates";
            URL url = new URL(u);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String buf = "";
            String S = "";
            while ((buf = reader.readLine()) != null) {
                S += buf;
            }
            
            Gson gson = new Gson();
            JsonObject jobj = new Gson().fromJson(S, JsonObject.class);            
            Double rate = jobj.get("silverSellRate").getAsDouble();
            return rate * units ;
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
    
    public static void main(String[] args ){
        SilverFeed feed = new SilverFeed();
        
        System.out.println("Get Silver:" + feed.getPrice(1));
    }
}

