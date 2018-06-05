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

import java.beans.PropertyVetoException;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.mchange.v2.c3p0.ComboPooledDataSource;

class DataSource {

    private static DataSource datasource;
    private ComboPooledDataSource cpds;
    
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");    // loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost/hdb?autoReconnect=true&useSSL=false"); //&useSSL=false
        cpds.setUser("root");
        cpds.setPassword("");
        
        cpds.setMinPoolSize(50);
        cpds.setAcquireIncrement(15);
        
        
        cpds.setMaxPoolSize(500);
        cpds.setTestConnectionOnCheckout(true);
        
        cpds.setMaxIdleTime(300);
        cpds.setCheckoutTimeout(800);
        cpds.setUnreturnedConnectionTimeout(1380);
        cpds.setIdleConnectionTestPeriod(1000);
        
        try {
            cpds.setPreferredTestQuery("SELECT 1");
        } catch (Exception ex) {
        }
        
        
    }
    
    public ComboPooledDataSource getComboPooledDataSource() {
        return this.cpds;
    }

    // public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
    // if (datasource == null) {
    // datasource = new DataSource();
    // return datasource;
    // } else {
    // return datasource;
    // }
    // }
    public Connection getConnection() throws SQLException {

        // Log.info("cpds");
        Connection connection = this.cpds.getConnection();

        
        // Log.info("acpds");
        return connection;
    }
    
    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            synchronized (DataSource.class) {
                if (datasource == null) {
                    datasource = new DataSource();
                }
            }
        }
        
        return datasource;
    }
}

/**

 */
public class DatabaseManager {

    static long calls = 0;
    static Lock lock = new ReentrantLock();
    static Connection source = null;
    
    public void heartbeat() {
        Connection conn = null;
        
        ResultSet r = null;
        Statement statement = null;
        try {
            conn = getConnection();
            
            statement = conn.createStatement();
            r = statement.executeQuery("select * from users limit 1;");

            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utility.closeQuietly(conn, statement, r);
        }
    }
    
    public static void wakeUp()
    {
        Connection conn = null;
            while (conn == null) {
                try {
                    conn = DatabaseManager.getConnection();
                    //Utility.snooze();
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    public static Connection getConnection() {
        DataSource ds = null;
            
        try {

            // lock.lock();
            boolean verbose = false;
                
            calls++;
            
            try {
                Exception e = new Exception();
                StackTraceElement[] list = e.getStackTrace();
                String from = list[1].getClassName() + "." + list[1].getMethodName();
                
                if (verbose) {
                    Log.info("Call###" + calls + " to getConnection() from: " + from);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try {
                
                ds = DataSource.getInstance();
                source = ds.getConnection();
                
                if (verbose) {
                    ComboPooledDataSource cpds = ds.getComboPooledDataSource();
                    
                    Log.info("num_connections: " + cpds.getNumConnectionsDefaultUser());
                    Log.info("num_busy_connections: " + cpds.getNumBusyConnectionsDefaultUser());
                    Log.info("num_idle_connections: " + cpds.getNumIdleConnectionsDefaultUser());
                    //return cpds.getConnection();
                    cpds = null;
                }
                
                return source;
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyVetoException ex) {
                ex.printStackTrace();
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            ds = null;
            
            // lock.unlock();
        }
        
        Log.info("No connection made");
        
        return null;
    }
}
