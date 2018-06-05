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
package com;


import com.feed.BitcoinFeed;
import com.feed.EthereumFeed;
import com.feed.Feed;
import com.feed.GasPriceFeed;
import com.feed.FactomFeed;

import com.feed.GenericFeed;
import com.feed.GoldFeed;
import com.feed.IpfsFeed;
import com.feed.PlatinumFeed;
import com.feed.SiaFeed;
import com.feed.SilverFeed;
import com.feed.StorjFeed;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;




class Aggregator {
    
    public static void main(String[] args) {
        
        try { 
            System.out.println("Aggregator called. Building Feed Source.");
            
            FeedSource source = new FeedSource();
            source.setName("NONE");
            ArrayList<GenericFeed> feedList = new ArrayList<GenericFeed>();
            try { feedList.add(new BitcoinFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add BitcoinFeed"); }
            try { feedList.add(new GasPriceFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add GasPriceFeed"); }
            try { feedList.add(new FactomFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add FactomFeed"); }
            try { feedList.add(new EthereumFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add EthereumFeed"); }
            try { feedList.add(new IpfsFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add IpfsFeed"); }
            try { feedList.add(new SiaFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add SiaFeed"); }
            try { feedList.add(new StorjFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add StorjFeed"); }
            try { feedList.add(new GoldFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add GoldFeed"); }
            try { feedList.add(new SilverFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add SilverFeed"); }
            try { feedList.add(new PlatinumFeed()); } catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add PlatinumFeed"); }
            

            Iterator itr = feedList.iterator();    
            System.out.println("Creating Database");
            Database db = new Database();        
            
            while( itr.hasNext() ){
                try { 
                    System.out.println("Loading feed...");
                    GenericFeed feed = (GenericFeed)itr.next();
                    Instrument instrument = feed.getInstrument();
                    System.out.println("Instrument:" + instrument.toString());
                    Rate rate = feed.getRate();
                    rate.setHigh(rate.getBid());
                    rate.setLow(rate.getBid());
                    rate.setOpen(rate.getBid());
                    rate.setClose(rate.getBid());


                    rate.setTimestamp(new Timestamp(System.currentTimeMillis()));

                    System.out.println("write:" + instrument + " " + rate);
                    db.save( instrument, rate, source );
                } catch(Exception ex) { 
                    ex.printStackTrace();
                }
            }
        
            db.close();
            db = null;
            
        } catch(Exception ex) { 
            ex.printStackTrace();
        } finally { 
            System.out.println("Exit");
            System.exit(0);
        }
        
    }
        
       
}