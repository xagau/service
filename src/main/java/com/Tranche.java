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

import com.feed.Feed;
import static com.feed.Feed.getHERCPricePerETH;
import java.text.DecimalFormat;

/**
 *
 * @author xagau
 */
public class Tranche {

    /**
     * @return the pricePerHercForSTORJ
     */
    public String getPricePerHercForSTORJ() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        pricePerHercForSTORJ = df.format((1 / Feed.getHERCPricePerSTORJ(pricePerSTORJ, 1, ratio)));
        return pricePerHercForSTORJ;
    }

    /**
     * @param pricePerHercForSTORJ the pricePerHercForSTORJ to set
     */
    public void setPricePerHercForSTORJ(String pricePerHercForSTORJ) {
        this.pricePerHercForSTORJ = pricePerHercForSTORJ;
    }

    /**
     * @return the pricePerHercForFCT
     */
    public String getPricePerHercForFCT() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        pricePerHercForFCT = df.format((1 / Feed.getHERCPricePerFCT(pricePerFCT, 1, ratio)));
        return pricePerHercForFCT;
    }

    /**
     * @param pricePerHercForFCT the pricePerHercForFCT to set
     */
    public void setPricePerHercForFCT(String pricePerHercForFCT) {
        this.pricePerHercForFCT = pricePerHercForFCT;
    }

    /**
     * @return the purchaseQuantityHercForSTORJ
     */
    public String getPurchaseQuantityHercForSTORJ() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        purchaseQuantityHercForSTORJ = df.format(Feed.getHERCPricePerSTORJ(pricePerSTORJ, 1, ratio));
        return purchaseQuantityHercForSTORJ;
    }

    /**
     * @param purchaseQuantityHercForSTORJ the purchaseQuantityHercForSTORJ to set
     */
    public void setPurchaseQuantityHercForSTORJ(String purchaseQuantityHercForSTORJ) {
        this.purchaseQuantityHercForSTORJ = purchaseQuantityHercForSTORJ;
    }

    /**
     * @return the purchaseQuantityHercForFCT
     */
    public String getPurchaseQuantityHercForFCT() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        purchaseQuantityHercForFCT = df.format(Feed.getHERCPricePerFCT(pricePerFCT, 1, ratio));
        return purchaseQuantityHercForFCT;
    
    }

    /**
     * @param purchaseQuantityHercForFCT the purchaseQuantityHercForFCT to set
     */
    public void setPurchaseQuantityHercForFCT(String purchaseQuantityHercForFCT) {
        this.purchaseQuantityHercForFCT = purchaseQuantityHercForFCT;
    }

    public Tranche(String name, double ratio, double ppB, double ppE, double ppS, double ppF, double ppI) {
        this.name = name;
        this.ratio = ratio;
        this.pricePerBTC = ppB;
        this.pricePerETH = ppE;
        this.pricePerSTORJ = ppS;
        this.pricePerFCT = ppF;
        //this.pricePerIPFS = ppI;

    }

    private String name;
    private double ratio = 0;
    private double pricePerBTC = 0;
    private double pricePerETH = 0;
    private double pricePerSTORJ = 0;
    private double pricePerFCT = 0;
    //private double pricePerIPFS = 0;
    
    
    private String pricePerHercForEth = "";
    private String pricePerHercForBTC = "";
    private String pricePerHercForSTORJ = "";
    private String pricePerHercForFCT = "";
    private String pricePerHercForIPFS = "";
    private String purchaseQuantityHercForEth = "";
    private String purchaseQuantityHercForBTC = "";
    private String purchaseQuantityHercForSTORJ = "";
    private String purchaseQuantityHercForFCT = "";
    //private String purchaseQuantityHercForIPFS = "";
    
    public void setPricePerHercForEth(String s) {
        pricePerHercForEth = s;
    }

    
    public String getPricePerHercForEth() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        pricePerHercForEth = df.format((1 / Feed.getHERCPricePerETH(pricePerETH, 1, ratio)));
        return pricePerHercForEth;
    }
    
//    public void setPricePerHercForIPFS(String s) {
//        pricePerHercForIPFS = s;
//    }
//
//    
//    public String getPricePerHercForIPFS() {
//        DecimalFormat df = new DecimalFormat("0.00000000");
//        pricePerHercForIPFS = df.format((1 / Feed.getHERCPricePerIPFS(pricePerIPFS, 1, ratio)));
//        return pricePerHercForIPFS;
//    }

    ////////////////////////////////////////////////////
    public void setPurchaseQuantityHercForEth(String s) { 
        purchaseQuantityHercForEth = s;
    }
    public String getPurchaseQuantityHercForEth() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        purchaseQuantityHercForEth =  df.format(Feed.getHERCPricePerETH(pricePerETH, 1, ratio));
        return purchaseQuantityHercForEth;
    }
    ////////////////////////////////////////////////////
//    public void setPurchaseQuantityHercForIPFS(String s) { 
//        purchaseQuantityHercForEth = s;
//    }
//    public String getPurchaseQuantityHercForIPFS() {
//        DecimalFormat df = new DecimalFormat("0.00000000");
//        purchaseQuantityHercForIPFS =  df.format(Feed.getHERCPricePerIPFS(pricePerIPFS, 1, ratio));
//        return purchaseQuantityHercForIPFS;
//    }    

    ////////////////////////////////////////////////////

    public void setPricePerHercForBTC(String s) { 
        pricePerHercForBTC = s;
    }
    
    public String getPricePerHercForBTC() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        pricePerHercForBTC = df.format((1 / Feed.getHERCPricePerBTC(pricePerBTC, 1, ratio)));
        return pricePerHercForBTC;
    }
    ////////////////////////////////////////////////////

    public void setPurchaseQuantityHercForBTC(String s) { 
        purchaseQuantityHercForBTC = s;
    }
    public String getPurchaseQuantityHercForBTC() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        purchaseQuantityHercForBTC = df.format(Feed.getHERCPricePerBTC(pricePerBTC, 1, ratio));
        return purchaseQuantityHercForBTC;
    }

    /**
     * @return the ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * @param ratio the ratio to set
     */
    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pricePerBTC
     */
    public double getPricePerBTC() {
        return pricePerBTC;
    }

    /**
     * @param pricePerBTC the pricePerBTC to set
     */
    public void setPricePerBTC(double pricePerBTC) {
        this.pricePerBTC = pricePerBTC;
    }

    /**
     * @return the pricePerEth
     */
    public double getPricePerEth() {
        return pricePerETH;
    }

    /**
     * @param pricePerEth the pricePerEth to set
     */
    public void setPricePerEth(double pricePerEth) {
        this.pricePerETH = pricePerEth;
    }
    
//     /**
//     * @return the pricePerEth
//     */
//    public double getPricePerIPFS() {
//        return pricePerIPFS;
//    }
//
//    /**
//     * @param pricePerEth the pricePerEth to set
//     */
//    public void setPricePerIPFS(double pricePerIPFS) {
//        this.pricePerIPFS = pricePerIPFS;
//    }
//    
    //////////////////////
    
     /**
     * @return the pricePerSTORJ
     */
    public double getPricePerSTORJ() {
        return pricePerSTORJ;
    }

    /**
     * @param pricePerSTORJ the pricePerSTORJ to set
     */
    public void setPricePerSTORJ(double pricePerSTORJ) {
        this.pricePerETH = pricePerSTORJ;
    }
    
     //////////////////////
    
     /**
     * @return the pricePerFCT
     */
    public double getPricePerFCT() {
        return pricePerFCT;
    }

    /**
     * @param pricePerFCT the pricePerFCT to set
     */
    public void setPricePerFCT(double pricePerFCT) {
        this.pricePerFCT = pricePerFCT;
    }
}
