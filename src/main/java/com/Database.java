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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Sean Beecroft
 */
public 
class Database { 
    public void close() {
        
    }
    
    public void save( Instrument instrument, Rate rate, FeedSource source ) { 
        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
                
                
        try { 
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();            
            statement = conn.createStatement();
            String sql = "INSERT INTO history ( symbol, source, ts, bid, ask, volume, o, h, l, c ) VALUES ('" + 
                    instrument.getSymbol() + "','" + 
                    source.getUrl() + "','" + 
                    rate.getTimestamp() + "'," + 
                    rate.getBid() + "," + 
                    rate.getAsk() + "," + 
                    rate.getVolume() + ", " + 
                    rate.getOpen() + ", " + 
                    rate.getHigh() + ", " + 
                    rate.getLow() + ", " + 
                    rate.getClose() + " )";
            
            long snapshot = System.currentTimeMillis();
            
            System.out.println("Writing:" + instrument.getSymbol() + " @ bid:" + rate.getBid() + " ask:" + rate.getAsk() + " @ " + new Date(snapshot) + " via " + source.getName());
            int n = statement.executeUpdate(sql);
            System.out.println("Wrote:" + n + " records @ " + new Date(snapshot) );
            Utility.closeQuietly(conn, statement, result);
        } catch(Exception ex) { 
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }
    }
}
