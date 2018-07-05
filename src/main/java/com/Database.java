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
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Sean Beecroft
 */
public class Database {

    public void close() {

    }

    public void save(Instrument instrument, Rate rate, FeedSource source, String range) {
        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();
            statement = conn.createStatement();
            String sql = "INSERT INTO history ( symbol, source, ts, bid, ask, volume, o, h, l, c, mrange ) VALUES ('"
                    + instrument.getSymbol() + "','"
                    + source.getUrl() + "','"
                    + rate.getTimestamp() + "',"
                    + rate.getBid() + ","
                    + rate.getAsk() + ","
                    + rate.getVolume() + ", "
                    + rate.getOpen() + ", "
                    + rate.getHigh() + ", "
                    + rate.getLow() + ", "
                    + rate.getClose() + ", "
                    + "'" + range + "')";

            long snapshot = System.currentTimeMillis();

            System.out.println("Writing:" + instrument.getSymbol() + " @ bid:" + rate.getBid() + " ask:" + rate.getAsk() + " @ " + new Date(snapshot) + " via " + source.getName() + " mrange:" + range);
            int n = statement.executeUpdate(sql);
            System.out.println("Wrote:" + n + " records @ " + new Date(snapshot));
            Utility.closeQuietly(conn, statement, result);
        } catch (Exception ex) {
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }
    }

    public double getOpen(String symbol, Timestamp start, Timestamp end) {

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();
            statement = conn.createStatement();
            String sql = "select o from history where ts between '" + start + "' and '" + end + "' and symbol = '" + symbol + "' and mrange = 'MINUTE' order by ts limit 1";

            System.out.println("Going to try:" + sql);

            long snapshot = System.currentTimeMillis();

            ResultSet r = statement.executeQuery(sql);
            if (r.next()) {
                double retV = r.getDouble("o");
                return retV;
            }

            Utility.closeQuietly(conn, statement, result);
        } catch (Exception ex) {
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }
        System.out.println("OPEN FAILED");
        
        return 0;

    }

    public double getClose(String symbol, Timestamp start, Timestamp end) {

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();
            statement = conn.createStatement();
            String sql = "select c from history where ts between '" + start + "' and '" + end + "' and symbol = '" + symbol + "' and mrange = 'MINUTE' order by ts DESC limit 1";
            System.out.println("Going to try:" + sql);

            ResultSet r = statement.executeQuery(sql);
            if (r.next()) {
                double retV = r.getDouble("c");
                return retV;
            }

            Utility.closeQuietly(conn, statement, result);
        } catch (Exception ex) {
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }

        return 0;

    }

    public double getHigh(String symbol, Timestamp start, Timestamp end) {

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();
            statement = conn.createStatement();
            String sql = "select max(h) from history where ts between '" + start + "' and '" + end + "' and symbol = '" + symbol + "' and mrange = 'MINUTE'";

            System.out.println("Going to try:" + sql);
            ResultSet r = statement.executeQuery(sql);
            if (r.next()) {
                double retV = r.getDouble(1);
                return retV;
            }

            Utility.closeQuietly(conn, statement, result);
        } catch (Exception ex) {
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }

        return 0;

    }

    public double getLow(String symbol, Timestamp start, Timestamp end) {

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            conn = DatabaseManager.getConnection(); // dbm = new DatabaseManager();
            statement = conn.createStatement();
            String sql = "select min(l) from history where ts between '" + start + "' and '" + end + "' and symbol = '" + symbol + "' and mrange = 'MINUTE'";

            System.out.println("Going to try:" + sql);

            ResultSet r = statement.executeQuery(sql);
            if (r.next()) {
                double retV = r.getDouble(1);
                return retV;
            }

            Utility.closeQuietly(conn, statement, result);
        } catch (Exception ex) {
            Utility.closeQuietly(conn, statement, result);
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, result);
        }

        return 0;

    }
}
