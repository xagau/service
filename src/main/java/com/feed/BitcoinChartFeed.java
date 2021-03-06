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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author xagau
 */
public class BitcoinChartFeed extends GenericFeed {

    @Override
    public double getPrice(double units) {
        BufferedReader reader = null;
        try {
            String u = "http://api.bitcoincharts.com/v1/markets.json";
            URL url = new URL(u);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String buf = "";
            String S = "";
            while ((buf = reader.readLine()) != null) {
                S += buf;
            }

            int indx = S.indexOf("bitstampUSD");
            String sub = S.substring(indx, S.length());
            int eindx = sub.indexOf("ask");
            String nstr = sub.substring(eindx, sub.length());
            int edp = nstr.indexOf(",");
            int bp = nstr.indexOf(":");
            String snippit = nstr.substring(bp + 1, edp);
            snippit = snippit.trim();
            Double d = Double.parseDouble(snippit);
            return d * units;
        } catch (MalformedURLException ex) {
        } catch (Exception ex) {
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
            }
        }
        return -1;
    }
}