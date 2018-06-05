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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class SiaFeed extends GenericFeed {

    public SiaFeed() {
        setInstrument(new Instrument("SC"));
        setRate(new Rate(getPrice(1)));        
    }
    @Override
    public double getPrice(double units) {
        BufferedReader reader = null;
        try {
            String u = "https://min-api.cryptocompare.com/data/price?fsym=SC&tsyms=USD";
            URL url = new URL(u);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String buf = "";
            String S = "";
            while ((buf = reader.readLine()) != null) {
                S += buf;
            }

            int indx = S.indexOf("USD");
            String sub = S.substring(indx+5, S.length());
            int eindx = sub.lastIndexOf(":");
            String snippit = sub.substring(0, sub.length()-1);
            snippit = snippit.trim();
            Double d = Double.parseDouble(snippit);
            DecimalFormat df = new DecimalFormat("0.00000000");
            //System.out.println("SIA D:" + df.format(d));
            return d * units;
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
}

