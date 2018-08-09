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

/**
 *
 * @author Sean Beecroft
 */
public class Calculation {

    /**
     * @return the storjNeeded
     */
    public double getStorjNeeded() {
        return storjNeeded;
    }

    /**
     * @param storjNeeded the storjNeeded to set
     */
    public void setStorjNeeded(double storjNeeded) {
        this.storjNeeded = storjNeeded;
    }

    private double ethNeeded;
    private double hercBurned;
    private double siaNeeded;
    private double hercNeeded;
    private double factoidNeeded;
    private double storjNeeded;
    
//    ethNeeded = calc.calculateEthereumNeeded(gasPrice, ethPrice);
//        hercBurned = ethNeeded;
//        factoidNeeded = calc.calculateFactoidNeeded(fctDataRequiredKB, factoidPriceInUSDPerKB, factoidPrice);
//        siaNeeded = calc.calculateSiaNeeded(siaDataRequiredKB, siaPriceInUSDPerKB, siaPrice);
//        hercNeeded = calc.calculateHercNeeded(siaNeeded, factoidNeeded, ethNeeded, hercBurned, hercPrice);
//        storjNeeded = calc.calculateStorjNeeded(storjDataRequiredKB, storjPriceInUSDPerKB, storjPrice);
//

    /**
     * @return the storjDataRequiredKB
     */
    public long getStorjDataRequiredKB() {
        return storjDataRequiredKB;
    }

    /**
     * @param storjDataRequiredKB the storjDataRequiredKB to set
     */
    public void setStorjDataRequiredKB(long storjDataRequiredKB) {
        this.storjDataRequiredKB = storjDataRequiredKB;
    }

    private double hercPrice = 0;
    private double siaPrice = 0;

    private double factoidPrice = 0;
    private long siaDataRequiredKB = 0;
    private long fctDataRequiredKB = 0;
    private long storjDataRequiredKB = 0;
    private double ethPrice = 0;
    private double gasPrice = 0;
    
    /**
     * @return the hercNeeded
     */
    public double getHercNeeded() {
        return hercNeeded;
    }

    /**
     * @param hercNeeded the hercNeeded to set
     */
    public void setHercNeeded(double hercNeeded) {
        this.hercNeeded = hercNeeded;
    }

    /**
     * @return the hercPrice
     */
    public double getHercPrice() {
        return hercPrice;
    }

    /**
     * @param hercPrice the hercPrice to set
     */
    public void setHercPrice(double hercPrice) {
        this.hercPrice = hercPrice;
    }

    /**
     * @return the siaNeeded
     */
    public double getSiaNeeded() {
        return siaNeeded;
    }

    /**
     * @param siaNeeded the siaNeeded to set
     */
    public void setSiaNeeded(double siaNeeded) {
        this.siaNeeded = siaNeeded;
    }

    /**
     * @return the siaPrice
     */
    public double getSiaPrice() {
        return siaPrice;
    }

    /**
     * @param siaPrice the siaPrice to set
     */
    public void setSiaPrice(double siaPrice) {
        this.siaPrice = siaPrice;
    }

    /**
     * @return the factoidNeeded
     */
    public double getFactoidNeeded() {
        return factoidNeeded;
    }

    /**
     * @param factoidNeeded the factoidNeeded to set
     */
    public void setFactoidNeeded(double factoidNeeded) {
        this.factoidNeeded = factoidNeeded;
    }

    /**
     * @return the factoidPrice
     */
    public double getFactoidPrice() {
        return factoidPrice;
    }

    /**
     * @param factoidPrice the factoidPrice to set
     */
    public void setFactoidPrice(double factoidPrice) {
        this.factoidPrice = factoidPrice;
    }

    /**
     * @return the siaDataRequiredKB
     */
    public long getSiaDataRequiredKB() {
        return siaDataRequiredKB;
    }

    /**
     * @param siaDataRequiredKB the siaDataRequiredKB to set
     */
    public void setSiaDataRequiredKB(long siaDataRequiredKB) {
        this.siaDataRequiredKB = siaDataRequiredKB;
    }

    /**
     * @return the fctDataRequiredKB
     */
    public long getFctDataRequiredKB() {
        return fctDataRequiredKB;
    }

    /**
     * @param fctDataRequiredKB the fctDataRequiredKB to set
     */
    public void setFctDataRequiredKB(long fctDataRequiredKB) {
        this.fctDataRequiredKB = fctDataRequiredKB;
    }

    /**
     * @return the ethPrice
     */
    public double getEthPrice() {
        return ethPrice;
    }

    /**
     * @param ethPrice the ethPrice to set
     */
    public void setEthPrice(double ethPrice) {
        this.ethPrice = ethPrice;
    }

    /**
     * @return the gasPrice
     */
    public double getGasPrice() {
        return gasPrice;
    }

    /**
     * @param gasPrice the gasPrice to set
     */
    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    /**
     * @return the ethNeeded
     */
    public double getEthNeeded() {
        return ethNeeded;
    }

    /**
     * @param ethNeeded the ethNeeded to set
     */
    public void setEthNeeded(double ethNeeded) {
        this.ethNeeded = ethNeeded;
    }

    /**
     * @return the hercBurned
     */
    public double getHercBurned() {
        return hercBurned;
    }

    /**
     * @param hercBurned the hercBurned to set
     */
    public void setHercBurned(double hercBurned) {
        this.hercBurned = hercBurned;
    }
        
    public double calculateSiaNeeded(long siaDataRequiredKB, double siaPriceInUSDPerKB, double siaPrice) {
        return (siaDataRequiredKB * siaPriceInUSDPerKB) / siaPrice;
    }

    public double calculateFactoidNeeded(long fctDataRequiredKB, double fctPriceInUSDPerKB, double fctPrice) {
        return (fctDataRequiredKB * fctPriceInUSDPerKB) / fctPrice;
    }

    public double calculateStorjNeeded(long storjDataRequiredKB, double storjPriceInUSDPerKB, double storjPrice) {
        return (storjDataRequiredKB * storjPriceInUSDPerKB) / storjPrice;
    }

    public double calculateEthereumNeeded(double gasPrice, double ethPrice) {
        return gasPrice / ethPrice;
    }
    
    public double calculateHercBurned(double gasPrice, double ethPrice) {
        return gasPrice / ethPrice;
    }
    
    public double calculateHercNeeded(double storjNeeded, double _storjPriceInUSD, double factoidNeeded, double _factoidPriceInUSD, double ethNeeded, double _ethPriceInUSD, double hercBurned, double hercPrice, double hercPriceInUSD) {
        return ((storjNeeded * _storjPriceInUSD) + (factoidNeeded * _factoidPriceInUSD) + (ethNeeded * _ethPriceInUSD)) / hercPriceInUSD;
    }
}