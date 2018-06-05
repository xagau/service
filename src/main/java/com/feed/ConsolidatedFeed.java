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

import java.util.ArrayList;

class ConsolidatedFeed extends GenericFeed {

    public static Double nearestToAverage(double[] res) {
        if (res.length < 1) {
            return null; //if there is no array return null;
        }
        double sum = 0; //variable to sum up the array
        for (int i = 0; i < res.length; i++) {
            double act = res[i];
            sum += act; //adding elements of array to sum
        }
        double avg = sum / res.length; //computing the average value
        double minDistance = Double.MAX_VALUE; //set distance to integer max so it is higher than any of values in array
        Double ret = null; //setting return value to null it will be replaced with value from array
        for (int i = 0; i < res.length; i++) {
            double act = res[i];
            double actDistance = Math.abs(act - avg); //computing distance of actual value and average
            if ((actDistance < minDistance) || ((actDistance == minDistance) && (act < ret))) { //if it is less than actual minimal distance or it is the same and act number is lower than return value
                minDistance = actDistance; //the distance is set to new
                ret = act; //also is return value
            }
        }
        return ret;
    }

    
    
    @Override
    public double getPrice(double units) {
        try {
            GenericFeed f1 = new BitcoinChartFeed();
            GenericFeed f3 = new BlockChainInfoFeed();
            GenericFeed f5 = new BitcoinAverageFeed();

            ArrayList list1 = new ArrayList();
            ArrayList list2 = new ArrayList();

            try {
                list1.add(f1.getPrice(1));
            } catch (Exception ex) {
            }
            try {
                list1.add(f3.getPrice(1));
            } catch (Exception ex) {
            }
            try {
                list1.add(f5.getPrice(1));
            } catch (Exception ex) {
            }
            for (int i = 0; i < list1.size(); i++) {
                if (((Double) list1.get(i)) > 0) {
                    list2.add(list1.get(i));
                }
            }
            double array[] = new double[list2.size()];
            for (int i = 0; i < list2.size(); i++) {
                array[i] = (Double) list2.get(i);
            }
            return nearestToAverage(array);
            
        } catch (Exception ex) {
        }
        return -1;
    }

}
