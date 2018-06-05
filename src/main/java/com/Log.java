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

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is a wrapper class for logging.
 *
 * @author Xagau
 * @version 1.4
 */
public class Log {

    /**
     * Suppress the default public constructor to prevent instances of this
     * class.
     */
    private Log() {
    }
    private static boolean debug = true;

    /**
     * This method will allow the logger to be run in debug mode (output) or in
     * regular mode (no output). You can specify the level, with Level
     *
     * @see java.util.logging.Level
     *
     * @param text the text to be logged
     * @param level the log level
     */
    public static void info(String text, Level level) {
        if (isDebug()) {
            Logger.getAnonymousLogger().info(text);
        }
        
    }

    /**
     * Log the text to the logger.
     *
     * @param text the text to be logged
     */
    public static void info(Exception ex) {
        ex.printStackTrace();
        info(ex.toString(), Level.ALL);
    }

    /**
     * Log the text to the logger.
     *
     * @param text the text to be logged
     */
    public static void info(String text) {
        info(text, Level.ALL);
    }

    /**
     * @param debug a boolean value, true to set debug mode on, to turn debug
     * off use false.
     */
    public static void setDebug(boolean debug) {
        Log.debug = debug;
    }

    /**
     * @return true if debug mode set to true, false if not.
     */
    public static boolean isDebug() {
        return debug;
    }
}
