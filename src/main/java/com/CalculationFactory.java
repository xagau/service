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
import com.feed.GenericFeed;
import java.text.DecimalFormat;

/**
 *
 * @author Sean Beecroft
 */
public class CalculationFactory {
    public static Calculation getCalculation()
    {
        
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

        DecimalFormat df = new DecimalFormat("0.00000000");

        System.out.println("--");
        System.out.println("Tranche 1 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.5)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 0.5)) + " per BTC");
        System.out.println("Tranche 2 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.7)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 0.7)) + " per BTC");
        System.out.println("Tranche 3 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.75)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 0.75)) + " per BTC");
        System.out.println("Tranche 4 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.90)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 0.90)) + " per BTC");
        System.out.println("Tranche 5 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 0.95)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 0.95)) + " per BTC");
        System.out.println("Tranche 6 - 1 BTC Purchases:" + df.format(getHERCPricePerBTC(bp, 1, 1)) + " OR " + df.format(1 / getHERCPricePerBTC(bp, 1, 1)) + " per BTC");

        System.out.println("Tranche 1 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.5)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 0.5)) + " per ETH");
        System.out.println("Tranche 2 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.7)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 0.7)) + " per ETH");
        System.out.println("Tranche 3 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.75)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 0.75)) + " per ETH");
        System.out.println("Tranche 4 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.90)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 0.90)) + " per ETH");
        System.out.println("Tranche 5 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 0.95)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 0.95)) + " per ETH");
        System.out.println("Tranche 6 - 1 ETH Purchases:" + df.format(getHERCPricePerETH(ep, 1, 1)) + " OR " + df.format(1 / getHERCPricePerETH(ep, 1, 1)) + " per ETH");

        System.out.println("Load Quote");
        Quote quote = new Quote();
        quote.setBitcoinPrice(bp);
        quote.setEthereumPrice(ep);
        quote.setFactomPrice(fp);
        quote.setStorjPrice(sp);
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

        double btcPrice = Feed.getBTCPrice(1);
        double hercNeeded = 0;
        //Tranche t = new Tranche();
        double hercPrice = Feed.getHERCPricePerBTC(btcPrice, 1, tranche6.getRatio());
        double siaNeeded = 0;
        double siaPrice = Feed.getSIAPrice(1.0); //0.00000253;
        double factoidNeeded = 0;
        double storjNeeded = 0;
        double storjPrice = Feed.getSTORJPrice(1.0); //0.00000002; // USD
        double factoidPrice = Feed.getFCTPrice(1.0);

        long dataRequired = 100000;

        long siaDataRequiredKB = dataRequired;
        long fctDataRequiredKB = dataRequired;
        long storjDataRequiredKB = dataRequired;

        double factoidPriceInUSDPerKB = 0.0000001;
        double siaPriceInUSDPerKB = 0.0000001;
        double storjPriceInUSDPerKB = 0.0000001;

        double ethPrice = Feed.getETHPrice(1.0);
        double gpunits = 1;
        double gasPrice = Feed.getGasPrice(gpunits);
        double ethNeeded = 0;
        double hercBurned = 0; //ethNeeded;

        Calculation calc = new Calculation();
        calc.setEthPrice(ethPrice);
        calc.setFactoidPrice(factoidPrice);
        calc.setGasPrice(gasPrice);
        calc.setHercBurned(hercBurned);
        calc.setFctDataRequiredKB(fctDataRequiredKB);
        calc.setSiaDataRequiredKB(siaDataRequiredKB);
        calc.setStorjDataRequiredKB(storjDataRequiredKB);
        calc.setHercPrice(hercPrice);

        ethNeeded = calc.calculateEthereumNeeded(gasPrice, ethPrice);
        hercBurned = ethNeeded;
        factoidNeeded = calc.calculateFactoidNeeded(fctDataRequiredKB, factoidPriceInUSDPerKB, factoidPrice);
        siaNeeded = calc.calculateSiaNeeded(siaDataRequiredKB, siaPriceInUSDPerKB, siaPrice);
        hercNeeded = calc.calculateHercNeeded(siaNeeded, factoidNeeded, ethNeeded, hercBurned, hercPrice);
        storjNeeded = calc.calculateStorjNeeded(storjDataRequiredKB, storjPriceInUSDPerKB, storjPrice);

        System.out.println("Data Required:" + dataRequired);
        System.out.println("GAS Price:" + df.format(gasPrice) + " USD (Standard Transfer)");
        System.out.println("SIA/SC Price:" + df.format(siaPrice) + " USD");
        System.out.println("FCT Price:" + df.format(factoidPrice) + " USD");
        System.out.println("STORJ Price:" + df.format(storjPrice) + " USD");
        System.out.println("Eth Price:" + df.format(ethPrice) + " USD");

        

        System.out.println("Eth Needed:" + df.format(ethNeeded));
        System.out.println("Factoid Needed:" + df.format(factoidNeeded));
        System.out.println("SIA Needed:" + df.format(siaNeeded));
        System.out.println("STORJ Needed:" + df.format(storjNeeded));
        System.out.println("HERC Burned:" + df.format(hercBurned));
 
        calc.setEthNeeded(ethNeeded);
        calc.setFactoidNeeded(factoidNeeded);
        calc.setFactoidNeeded(factoidNeeded);
        calc.setGasPrice(gasPrice);
        calc.setHercBurned(hercBurned);
        calc.setSiaNeeded(siaNeeded);
        calc.setStorjNeeded(storjNeeded);
        
        return calc;
    }
}
