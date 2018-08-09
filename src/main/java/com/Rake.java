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


import com.feed.BTCGoldFeed;
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


enum RangeTime {
    MINUTE,
    MINUTE_5,
    MINUTE_15,
    MINUTE_30,
    HOUR_1,
    DAY_1,
    WEEK_1,
    MONTH_1,
    YEAR_1,
}


public class Rake {
    
    public static long getRangeTime(String range)
    {
        long second = 1000;
        if( range.equals(RangeTime.MINUTE.toString())){
            return second * 60;            
        } else if ( range.equals(RangeTime.MINUTE_5.toString())){
            return second * 60 * 5; 
        } else if ( range.equals(RangeTime.MINUTE_15.toString())){
            return second * 60 * 15;             
        } else if ( range.equals(RangeTime.MINUTE_30.toString())){
            return second * 60 * 30;             
        } else if ( range.equals(RangeTime.HOUR_1.toString())){
            return second * 60 * 60;             
        } else if ( range.equals(RangeTime.DAY_1.toString())){
            return second * 60 * 60 * 24;             
        } else if ( range.equals(RangeTime.WEEK_1.toString())){            
            return second * 60 * 60 * 24 * 7; 
        } else if ( range.equals(RangeTime.MONTH_1.toString())){
            return second * 60 * 60 * 24 * 30;             
        } else if ( range.equals(RangeTime.YEAR_1.toString())){
            return second * 60 * 60 * 24 * 365;             
        }
        return 0;
    }
    
    public static void main(String[] args) {
        
        try { 
            // Range can be:            
            // MINUTE
            // MINUTE_5
            // MINUTE_15
            // MINUTE_30
            // HOUR_1
            // HOUR_3
            // DAY_1
            // WEEK_1
            // MONTH_1
            // YEAR_1
            
            String range = args[0];
            range = Utility.correctRange(range);
            
            System.out.println("Rake called. Building Time-Range Source.");
            
            FeedSource source = new FeedSource();
            source.setName("NONE");
            ArrayList<GenericFeed> feedList = new ArrayList<GenericFeed>();
            try { feedList.add(new BTCGoldFeed());} catch(Exception ex) { ex.printStackTrace(); System.out.println("Failed to add BTCGoldFeed"); }
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
            System.out.println("Creating/Binding Database Interface");
     
            
            while( itr.hasNext() ){
                try { 
                    Database db = new Database();   
                    
                    System.out.println("Loading instrument list...");
                    GenericFeed feed = (GenericFeed)itr.next();
                    Instrument instrument = feed.getInstrument();
                    System.out.println("Instrument:" + instrument.toString());
                    
                    double open,high,low,close;
                    
                    System.out.println("Time Range for:" + range);
                    long RANGE_TIME = getRangeTime(range); // 1 second * 60 * 60
                    System.out.println("Time:" + RANGE_TIME);
                    
                    Timestamp start = new Timestamp(System.currentTimeMillis()-RANGE_TIME);
                    Timestamp end = new Timestamp(System.currentTimeMillis());
                    
                    open = db.getOpen(instrument.getSymbol(), start, end);
                    System.out.println("Open:" + open);
                    
                    high = db.getHigh(instrument.getSymbol(), start, end);
                    System.out.println("High:" + high);
                    
                    low = db.getLow(instrument.getSymbol(), start, end);
                    System.out.println("Low:" + low);
                    
                    close = db.getClose(instrument.getSymbol(), start, end);
                    System.out.println("Close:" + close);
                    
                    Rate rate = new Rate();
                    rate.setBid(open);
                    rate.setAsk(open);

                    rate.setHigh(high);
                    rate.setLow(low);
                    rate.setOpen(open);
                    rate.setClose(close);

                    rate.setTimestamp(new Timestamp(System.currentTimeMillis()));

                    if( Utility.isValidRate(rate) ){
                        System.out.println("write:" + instrument + " " + rate + " mrange:" + range);
                        db.save( instrument, rate, source, range );
                    }
                    
                    db.close();
                    db = null;
            
                } catch(Exception ex) { 
                    
                    ex.printStackTrace();
                }
            }
        

            
        } catch(Exception ex) { 
            ex.printStackTrace();
        } finally { 
            System.out.println("Exit");
            System.exit(0);
        }
        
    }
        
       
}