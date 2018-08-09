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
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BTCGoldFeed extends GenericFeed {

    public BTCGoldFeed() {
        setInstrument(new Instrument("XAUBTC"));
        setRate(new Rate(getPrice(1)));        
    }
    @Override
    public double getPrice(double units) {
        GoldFeed gf = new GoldFeed();
        BitcoinFeed bf = new BitcoinFeed();
        double price = bf.getPrice(1)/gf.getPrice(1);
        return price;
    }
    
    public static void main(String[] args ){
        BTCGoldFeed feed = new BTCGoldFeed();
        
        System.out.println("Get Gold:" + feed.getPrice(1));
    }
}

