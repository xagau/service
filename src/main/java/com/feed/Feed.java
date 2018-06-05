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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;




/**
 *
 * @author beecrofs
 */
public class Feed {

    /**
     * @return the instrument
     */
    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * @param instrument the instrument to set
     */
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    private Instrument instrument = null;
    
    

    public static double getBTCPrice(double units) {
        ConsolidatedFeed c = new ConsolidatedFeed();
        return c.getPrice(units);
    }


    public static double getETHPrice(double units) {
        EthereumFeed c = new EthereumFeed();
        return c.getPrice(units);
    }
    
    public static double getSTORJPrice(double units) {
        StorjFeed c = new StorjFeed();
        return c.getPrice(units);
    }
    
    public static double getGasPrice(double units) {
        GasPriceFeed c = new GasPriceFeed();
        return c.getPrice(units);
    }
    
    public static double getSIAPrice(double units) {
        SiaFeed c = new SiaFeed();
        return c.getPrice(units);
    }
    
    public static double getFCTPrice(double units) {
        FactomFeed c = new FactomFeed();
        return c.getPrice(units);
    }
    public static double getIPFSPrice(double units) {
        IpfsFeed c = new IpfsFeed();
        return c.getPrice(units);
    }
    
    public static double getHERCPricePerBTC(double p, double units, double tranche) {
        //ConsolidatedFeed c = new ConsolidatedFeed();
        double amount = (p / tranche)*units;
        return amount;
    }
    public static double getHERCPricePerETH(double p, double units, double tranche) {
        //EthereumFeed c = new EthereumFeed();
        double amount = (p / tranche)*units;
        return amount;
    }
    
    public static double getHERCPricePerSTORJ(double p, double units, double tranche) {
        //ConsolidatedFeed c = new ConsolidatedFeed();
        double amount = p / tranche;
        return amount;
    }
    public static double getHERCPricePerFCT(double p, double units, double tranche) {
        //EthereumFeed c = new EthereumFeed();
        double amount = (p / tranche)*units;
        return amount;
    }
    public static double getHERCPricePerIPFS(double p, double units, double tranche) {
        //EthereumFeed c = new EthereumFeed();
        double amount = (p / tranche)*units;
        return amount;
    }
    

    public static double getPriceByInstrument(Instrument instrument, boolean raw) {
        // TODO code application logic here
        try {
            if (instrument == null) {
                throw new IllegalArgumentException("Instrument is null");
            }
            if (raw == true) {
                
                if (instrument.getSymbol().equals("BTC")) {
                    double sp = -1;
                    sp = getBTCPrice(1);
                    if (sp != -1) {
                        return sp;
                    }
                    
                }
                
                if (instrument.getSymbol().equals("ETH")) {
                    double sp = -1;
                    sp = getETHPrice(1);
                    if (sp != -1) {
                        return sp;
                    }
                    
                }
                
                if (instrument.getSymbol().equals("FCT")) {
                    double sp = -1;
                    sp = getFCTPrice(1);
                    if (sp != -1) {
                        return sp;
                    }
                    
                }
                
                if (instrument.getSymbol().equals("STORJ")) {
                    double sp = -1;
                    sp = getSTORJPrice(1);
                    if (sp != -1) {
                        return sp;
                    }
                    
                }

                if (instrument.getSymbol().equals("IPFS")) {
                    double sp = -1;
                    sp = getIPFSPrice(1);
                    if (sp != -1) {
                        return sp;
                    }
                    
                }
                
            } else { 
                // NYI
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    
    public static void main(String[] args) {
        
        GenericFeed f1 = new BitcoinChartFeed();
        GenericFeed f3 = new BlockChainInfoFeed();
        GenericFeed f5 = new BitcoinAverageFeed();
        
        Instrument ethereum = new Instrument("ETH");
        Instrument btc = new Instrument("BTC");
        Instrument storj = new Instrument("STORJ");
        Instrument factom = new Instrument("FCT");
        Instrument ipfs = new Instrument("IPFS");
        
        double bp = getPriceByInstrument(btc, true);
        double ep = getPriceByInstrument(ethereum, true);
        double sp = getPriceByInstrument(storj, true);
        double fp = getPriceByInstrument(factom, true);
        double ip = getPriceByInstrument(ipfs, true);
        
        DecimalFormat df = new DecimalFormat("0.00000000");
        System.out.println("Tranche 1 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.5)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 0.5)) + " per BTC");
        System.out.println("Tranche 2 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.7)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 0.7)) + " per BTC");
        System.out.println("Tranche 3 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.75)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 0.75)) + " per BTC");
        System.out.println("Tranche 4 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.90)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 0.90)) + " per BTC");
        System.out.println("Tranche 5 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.95)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 0.95)) + " per BTC");
        System.out.println("Tranche 6 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 1)) + " OR " + df.format(1/getHERCPricePerBTC(bp, 1, 1)) + " per BTC");
        
        System.out.println("Tranche 1 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.5))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 0.5)) + " per ETH");
        System.out.println("Tranche 2 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.7))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 0.7)) + " per ETH");
        System.out.println("Tranche 3 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.75))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 0.75)) + " per ETH");
        System.out.println("Tranche 4 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.90))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 0.90)) + " per ETH");
        System.out.println("Tranche 5 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.95))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 0.95)) + " per ETH");
        System.out.println("Tranche 6 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 1))+ " OR " + df.format(1/getHERCPricePerETH(ep, 1, 1))  + " per ETH");
       
        System.out.println("Tranche 1 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 0.5)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 0.5)) + " per STORJ");
        System.out.println("Tranche 2 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 0.7)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 0.7)) + " per STORJ");
        System.out.println("Tranche 3 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 0.75)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 0.75)) + " per STORJ");
        System.out.println("Tranche 4 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 0.90)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 0.90)) + " per STORJ");
        System.out.println("Tranche 5 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 0.95)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 0.95)) + " per STORJ");
        System.out.println("Tranche 6 - 1 STORJ Purchases:" + df.format(getHERCPricePerSTORJ(sp, 1, 1)) + " OR " + df.format(1/getHERCPricePerSTORJ(sp, 1, 1)) + " per STORJ");
        
        System.out.println("Tranche 1 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 0.5)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 0.5)) + " per FCT");
        System.out.println("Tranche 2 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 0.7)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 0.7)) + " per FCT");
        System.out.println("Tranche 3 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 0.75)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 0.75)) + " per FCT");
        System.out.println("Tranche 4 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 0.90)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 0.90)) + " per FCT");
        System.out.println("Tranche 5 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 0.95)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 0.95)) + " per FCT");
        System.out.println("Tranche 6 - 1 FCT Purchases:" + df.format(getHERCPricePerFCT(fp, 1, 1)) + " OR " + df.format(1/getHERCPricePerFCT(fp, 1, 1)) + " per FCT");
        
        System.out.println("Tranche 1 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 0.5)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 0.5)) + " per IPFS");
        System.out.println("Tranche 2 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 0.7)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 0.7)) + " per IPFS");
        System.out.println("Tranche 3 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 0.75)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 0.75)) + " per IPFS");
        System.out.println("Tranche 4 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 0.90)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 0.90)) + " per IPFS");
        System.out.println("Tranche 5 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 0.95)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 0.95)) + " per IPFS");
        System.out.println("Tranche 6 - 1 IPFS Purchases:" + df.format(getHERCPricePerIPFS(ip, 1, 1)) + " OR " + df.format(1/getHERCPricePerIPFS(ip, 1, 1)) + " per IPFS");
    }
}
