package com;
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


import java.io.Serializable;

/**
 *
 * @author Xagau
 */
public class Instrument implements Serializable {

    private String symbol;
    //private String name;
    private String description;

    private Instrument() {
        //this("GOLD");
    }

    public boolean equals(Instrument other) {
        if (this.getSymbol().equals(other.getSymbol())) {
            return true;
        }
        return false;
    }

    public Instrument(String name) {
        //this.name = name;
        setSymbol(name);
    }

    public String toString() {
        return symbol;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param id the id to set
     */
    public void setSymbol(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Symbol ID cannot be null");
        }
        this.symbol = id.toUpperCase();
    }
}
