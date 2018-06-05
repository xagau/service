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

import com.feed.BitcoinAverageFeed;
import com.feed.BitcoinChartFeed;
import com.feed.BlockChainInfoFeed;
import com.feed.Feed;
import static com.feed.Feed.getHERCPricePerBTC;
import static com.feed.Feed.getHERCPricePerETH;
import static com.feed.Feed.getPriceByInstrument;
import com.feed.GenericFeed;
import java.text.DecimalFormat;

/**
 *
 * @author xagau
 */
public class QuoteFactory {
    public static Quote getQuote() { 
        
        try { 
        
            GenericFeed f1 = new BitcoinChartFeed();
            GenericFeed f3 = new BlockChainInfoFeed();
            GenericFeed f5 = new BitcoinAverageFeed();

            Instrument storj = new Instrument("STORJ");
            Instrument factom = new Instrument("FCT");
            Instrument ipfs = new Instrument("IPFS");
            Instrument ethereum = new Instrument("ETH");
            Instrument btc = new Instrument("BTC");

            double bp = Feed.getPriceByInstrument(btc, true);
            double ep = Feed.getPriceByInstrument(ethereum, true);
            double sp = Feed.getPriceByInstrument(storj, true);
            double fp = Feed.getPriceByInstrument(factom, true);
            double ip = Feed.getPriceByInstrument(ipfs, true);
            double gp = Feed.getGasPrice(1.0);

            DecimalFormat df = new DecimalFormat("0.00000000");
        
            System.out.println("--");
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
            
            System.out.println("Load Quote");
            Quote quote = new Quote();
            quote.setBitcoinPrice(bp);
            quote.setEthereumPrice(ep);
            quote.setFactomPrice(fp);
            quote.setStorjPrice(sp);
            quote.setGasPrice(gp);
            //quote.setIPFSPrice(ip);
            
            int currentTranche = 0;
            quote.setCurrentTranche(currentTranche);
            System.out.println("Set Tranche");
            Tranche tranche1 = new Tranche("Tranche1", 0.50, bp, ep, sp, fp, ip);
            Tranche tranche2 = new Tranche("Tranche2", 0.70, bp, ep, sp, fp, ip);
            Tranche tranche3 = new Tranche("Tranche3", 0.75, bp, ep, sp, fp, ip);
            Tranche tranche4 = new Tranche("Tranche4", 0.90, bp, ep, sp, fp, ip);
            Tranche tranche5 = new Tranche("Tranche5", 0.95, bp, ep, sp, fp, ip);
            Tranche tranche6 = new Tranche("Tranche6", 1.00, bp, ep, sp, fp, ip);
            
            tranche1.setPricePerBTC(tranche1.getPricePerBTC());
            tranche2.setPricePerBTC(tranche2.getPricePerBTC());
            tranche3.setPricePerBTC(tranche3.getPricePerBTC());
            tranche4.setPricePerBTC(tranche4.getPricePerBTC());
            tranche5.setPricePerBTC(tranche5.getPricePerBTC());
            tranche6.setPricePerBTC(tranche6.getPricePerBTC());

            tranche1.setPricePerSTORJ(tranche1.getPricePerSTORJ());
            tranche2.setPricePerSTORJ(tranche2.getPricePerSTORJ());
            tranche3.setPricePerSTORJ(tranche3.getPricePerSTORJ());
            tranche4.setPricePerSTORJ(tranche4.getPricePerSTORJ());
            tranche5.setPricePerSTORJ(tranche5.getPricePerSTORJ());
            tranche6.setPricePerSTORJ(tranche6.getPricePerSTORJ());
            
            tranche1.setPricePerFCT(tranche1.getPricePerFCT());
            tranche2.setPricePerFCT(tranche2.getPricePerFCT());
            tranche3.setPricePerFCT(tranche3.getPricePerFCT());
            tranche4.setPricePerFCT(tranche4.getPricePerFCT());
            tranche5.setPricePerFCT(tranche5.getPricePerFCT());
            tranche6.setPricePerFCT(tranche6.getPricePerFCT());
            
            tranche1.setPricePerEth(tranche1.getPricePerEth());
            tranche2.setPricePerEth(tranche2.getPricePerEth());
            tranche3.setPricePerEth(tranche3.getPricePerEth());
            tranche4.setPricePerEth(tranche4.getPricePerEth());
            tranche5.setPricePerEth(tranche5.getPricePerEth());
            tranche6.setPricePerEth(tranche6.getPricePerEth());

//            tranche1.setPricePerIPFS(tranche1.getPricePerIPFS());
//            tranche2.setPricePerIPFS(tranche2.getPricePerIPFS());
//            tranche3.setPricePerIPFS(tranche3.getPricePerIPFS());
//            tranche4.setPricePerIPFS(tranche4.getPricePerIPFS());
//            tranche5.setPricePerIPFS(tranche5.getPricePerIPFS());
//            tranche6.setPricePerIPFS(tranche6.getPricePerIPFS());
            
            tranche1.setPricePerHercForFCT(tranche1.getPricePerHercForFCT());
            tranche2.setPricePerHercForFCT(tranche2.getPricePerHercForFCT());
            tranche3.setPricePerHercForFCT(tranche3.getPricePerHercForFCT());
            tranche4.setPricePerHercForFCT(tranche4.getPricePerHercForFCT());
            tranche5.setPricePerHercForFCT(tranche5.getPricePerHercForFCT());
            tranche6.setPricePerHercForFCT(tranche6.getPricePerHercForFCT());
            
            tranche1.setPricePerHercForSTORJ(tranche1.getPricePerHercForSTORJ());
            tranche2.setPricePerHercForSTORJ(tranche2.getPricePerHercForSTORJ());
            tranche3.setPricePerHercForSTORJ(tranche3.getPricePerHercForSTORJ());
            tranche4.setPricePerHercForSTORJ(tranche4.getPricePerHercForSTORJ());
            tranche5.setPricePerHercForSTORJ(tranche5.getPricePerHercForSTORJ());
            tranche6.setPricePerHercForSTORJ(tranche6.getPricePerHercForSTORJ());
            
            tranche1.setPricePerHercForEth(tranche1.getPricePerHercForEth());
            tranche2.setPricePerHercForEth(tranche2.getPricePerHercForEth());
            tranche3.setPricePerHercForEth(tranche3.getPricePerHercForEth());
            tranche4.setPricePerHercForEth(tranche4.getPricePerHercForEth());
            tranche5.setPricePerHercForEth(tranche5.getPricePerHercForEth());
            tranche6.setPricePerHercForEth(tranche6.getPricePerHercForEth());
            
            tranche1.setPricePerHercForBTC(tranche1.getPricePerHercForBTC());
            tranche2.setPricePerHercForBTC(tranche2.getPricePerHercForBTC());
            tranche3.setPricePerHercForBTC(tranche3.getPricePerHercForBTC());
            tranche4.setPricePerHercForBTC(tranche4.getPricePerHercForBTC());
            tranche5.setPricePerHercForBTC(tranche5.getPricePerHercForBTC());
            tranche6.setPricePerHercForBTC(tranche6.getPricePerHercForBTC());

//            tranche1.setPricePerHercForIPFS(tranche1.getPricePerHercForIPFS());
//            tranche2.setPricePerHercForIPFS(tranche2.getPricePerHercForIPFS());
//            tranche3.setPricePerHercForIPFS(tranche3.getPricePerHercForIPFS());
//            tranche4.setPricePerHercForIPFS(tranche4.getPricePerHercForIPFS());
//            tranche5.setPricePerHercForIPFS(tranche5.getPricePerHercForIPFS());
//            tranche6.setPricePerHercForIPFS(tranche6.getPricePerHercForIPFS());

            
            tranche1.setPurchaseQuantityHercForEth(tranche1.getPurchaseQuantityHercForEth());
            tranche2.setPurchaseQuantityHercForEth(tranche2.getPurchaseQuantityHercForEth());
            tranche3.setPurchaseQuantityHercForEth(tranche3.getPurchaseQuantityHercForEth());
            tranche4.setPurchaseQuantityHercForEth(tranche4.getPurchaseQuantityHercForEth());
            tranche5.setPurchaseQuantityHercForEth(tranche5.getPurchaseQuantityHercForEth());
            tranche6.setPurchaseQuantityHercForEth(tranche6.getPurchaseQuantityHercForEth());

            tranche1.setPurchaseQuantityHercForBTC(tranche1.getPurchaseQuantityHercForBTC());
            tranche2.setPurchaseQuantityHercForBTC(tranche2.getPurchaseQuantityHercForBTC());
            tranche3.setPurchaseQuantityHercForBTC(tranche3.getPurchaseQuantityHercForBTC());
            tranche4.setPurchaseQuantityHercForBTC(tranche4.getPurchaseQuantityHercForBTC());
            tranche5.setPurchaseQuantityHercForBTC(tranche5.getPurchaseQuantityHercForBTC());
            tranche6.setPurchaseQuantityHercForBTC(tranche6.getPurchaseQuantityHercForBTC());
            
            tranche1.setPurchaseQuantityHercForFCT(tranche1.getPurchaseQuantityHercForFCT());
            tranche2.setPurchaseQuantityHercForFCT(tranche2.getPurchaseQuantityHercForFCT());
            tranche3.setPurchaseQuantityHercForFCT(tranche3.getPurchaseQuantityHercForFCT());
            tranche4.setPurchaseQuantityHercForFCT(tranche4.getPurchaseQuantityHercForFCT());
            tranche5.setPurchaseQuantityHercForFCT(tranche5.getPurchaseQuantityHercForFCT());
            tranche6.setPurchaseQuantityHercForFCT(tranche6.getPurchaseQuantityHercForFCT());

            tranche1.setPurchaseQuantityHercForSTORJ(tranche1.getPurchaseQuantityHercForSTORJ());
            tranche2.setPurchaseQuantityHercForSTORJ(tranche2.getPurchaseQuantityHercForSTORJ());
            tranche3.setPurchaseQuantityHercForSTORJ(tranche3.getPurchaseQuantityHercForSTORJ());
            tranche4.setPurchaseQuantityHercForSTORJ(tranche4.getPurchaseQuantityHercForSTORJ());
            tranche5.setPurchaseQuantityHercForSTORJ(tranche5.getPurchaseQuantityHercForSTORJ());
            tranche6.setPurchaseQuantityHercForSTORJ(tranche6.getPurchaseQuantityHercForSTORJ());
          
//            tranche1.setPurchaseQuantityHercForIPFS(tranche1.getPurchaseQuantityHercForIPFS());
//            tranche2.setPurchaseQuantityHercForIPFS(tranche2.getPurchaseQuantityHercForIPFS());
//            tranche3.setPurchaseQuantityHercForIPFS(tranche3.getPurchaseQuantityHercForIPFS());
//            tranche4.setPurchaseQuantityHercForIPFS(tranche4.getPurchaseQuantityHercForIPFS());
//            tranche5.setPurchaseQuantityHercForIPFS(tranche5.getPurchaseQuantityHercForIPFS());
//            tranche6.setPurchaseQuantityHercForIPFS(tranche6.getPurchaseQuantityHercForIPFS());
          
            
            Tranche[] list = new Tranche[] { 
                tranche1, //1 
                tranche2, //2 
                tranche3, //3 
                tranche4, //4 
                tranche5, //5 
                tranche6, //6                 
            };
            
            quote.setList(list);
            System.out.println("Return Quote");
            return quote;
            
        } catch(Exception ex) { 
            ex.printStackTrace();
            return null;
        }
    }
}
