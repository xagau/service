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
 * @author xagau
 */
public class Quote { 

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

//    /**
//     * @return the ipfsPrice
//     */
//    public double getIPFSPrice() {
//        return ipfsPrice;
//    }
//
//    /**
//     * @param ipfsPrice the ipfsPrice to set
//     */
//    public void setIPFSPrice(double ipfsPrice) {
//        this.ipfsPrice = ipfsPrice;
//    }
    public static final int NUMBER_TRANCHES = 6;
    private double ethereumPrice;
    private double bitcoinPrice;
    private double storjPrice;
    private double factomPrice;
    private double gasPrice;
    //private double ipfsPrice;
    
    private int currentTranche = 0;
    private Tranche[] list = new Tranche[NUMBER_TRANCHES];

  
    /**
     * @return the ethereumPrice
     */
    public double getEthereumPrice() {
        return ethereumPrice;
    }

    /**
     * @param ethereumPrice the ethereumPrice to set
     */
    public void setEthereumPrice(double ethereumPrice) {
        this.ethereumPrice = ethereumPrice;
    }

    /**
     * @return the bitcoinPrice
     */
    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    /**
     * @param bitcoinPrice the bitcoinPrice to set
     */
    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    /**
     * @return the currentTranche
     */
    public int getCurrentTranche() {
        return currentTranche;
    }

    /**
     * @param currentTranche the currentTranche to set
     */
    public void setCurrentTranche(int currentTranche) {
        this.currentTranche = currentTranche;
    }

   
    /**
     * @return the list
     */
    public Tranche[] getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(Tranche[] list) {
        this.list = list;
    }

    /**
     * @return the storjPrice
     */
    public double getStorjPrice() {
        return storjPrice;
    }

    /**
     * @param storjPrice the storjPrice to set
     */
    public void setStorjPrice(double storjPrice) {
        this.storjPrice = storjPrice;
    }

    /**
     * @return the factomPrice
     */
    public double getFactomPrice() {
        return factomPrice;
    }

    /**
     * @param factomPrice the factomPrice to set
     */
    public void setFactomPrice(double factomPrice) {
        this.factomPrice = factomPrice;
    }
    
    
    
}