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
import com.Rate;

/**
 *
 * @author xagau
 */
public abstract class GenericFeed {

    /**
     * @return the rate
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Rate rate) {
        this.rate = rate;
    }

    private Rate rate = null;
    Instrument instrument = null;
    public Instrument getInstrument()
    {
        return instrument;
    }
    
    public void setInstrument(Instrument instr)
    {
        instrument = instr;
    }
    
    public abstract double getPrice(double units);
}
