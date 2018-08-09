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
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import servlet.HERCServlet;

/**
 *
 * @author xagau
 */
public class Utility {

    public static void log(String msg) {
        long memory = Runtime.getRuntime().freeMemory();
        long total = Runtime.getRuntime().totalMemory();
        Date dt = new Date(System.currentTimeMillis());
        Log.info(msg);
        dt = null;
        System.gc();

    }
    
    public static String correctRange(String range)
    {
        if( range == null || range.equals("") || range.equalsIgnoreCase("null") ) { 
                range = "MINUTE";
        }
        return range;
    }
    public static final String UTF8_BOM = "\uFEFF";

    public static void discardBOM(String inFile, String outFile) {
        try {

            boolean firstLine = true;
            FileInputStream fis = new FileInputStream(inFile);
            BufferedReader r = new BufferedReader(new InputStreamReader(fis,
                    "UTF8"));

            FileOutputStream fos = new FileOutputStream(outFile);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos, "Cp1252"));
            for (String s = ""; (s = r.readLine()) != null;) {
                if (firstLine) {
                    s = Utility.removeUTF8BOM(s);
                    firstLine = false;
                }
                w.write(s + System.getProperty("line.separator"));
                w.flush();
            }

            w.close();
            r.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

    public static String santizeForSQL(String in) {
        if (in == null) {
            return null;
        }
        String out = in;
        out = out.trim();
        out = out.replaceAll("'", "`");
        return out;
    }

    public static String formatTitleForCalendar(String in) {
        String out = in;
        in = in.replaceAll("\n", " ");

        return out;
    }

    public static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

    public static void closeQuietly(Connection conn, Statement statement, ResultSet result) {

        try {

            if (result != null) {
                try {
                    result.close();
                    result = null;
                } catch (Exception ex) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;

                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ex) {
                }
            }

        } catch (Exception ex) {

        } finally {

            try {
                result.close();
            } catch (Exception ex) {
            }
            try {
                statement.close();
            } catch (Exception ex) {
            }
            try {
                conn.close();
            } catch (Exception ex) {
            }

            result = null;
            statement = null;
            conn = null;

        }

        //System.gc();
    }

    public static void closeQuietly(Connection conn, PreparedStatement statement, ResultSet result) {

        try {
            if (result != null) {
                try {
                    result.close();
                    result = null;
                } catch (Exception ex) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception ex) {
                }
            }

        } catch (Exception ex) {

        } finally {
            try {
                result.close();
            } catch (Exception ex) {
            }
            try {
                statement.close();
            } catch (Exception ex) {
            }
            try {
                conn.close();
            } catch (Exception ex) {
            }

            result = null;
            statement = null;
            conn = null;

        }

        //System.gc();
    }

    public static Date getOneWeekAgo() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        return new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    }

    public static java.util.Date getTomorrow() {
        java.util.Date dt = new java.util.Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        return dt;
    }

    public static boolean parseBoolean(String s) {
        if (s == null) {
            return false;
        }
        s = s.toLowerCase();
        if (s.equals("true")) {
            return true;
        } else if (s.equals("yes")) {
            return true;
        } else if (s.equals("on")) {
            return true;
        }
        return false;

    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getTwoWeeksAgo() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        return new Date(System.currentTimeMillis() - (14 * DAY_IN_MS));
    }

    public static java.util.Date getOneMonthAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        java.util.Date result = cal.getTime();
        return result;
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return "";
        }
        phoneNumber = phoneNumber.trim();
        phoneNumber = phoneNumber.replaceAll("-", "");
        phoneNumber = phoneNumber.replaceAll("\\(", "");
        phoneNumber = phoneNumber.replaceAll("\\)", "");
        phoneNumber = phoneNumber.replaceAll(" ", "");
        phoneNumber = phoneNumber.replaceAll("\\.", "");

        phoneNumber = phoneNumber.replaceAll("\\+", "");
        return phoneNumber;
    }
    static String key = "das08523jh785654kgjhghjggfjg896864jghjjhgjhgui67653454z887678hgfgfhf657865875857865tLKJLKNBBNMMBNNMSABNMBSDMNBVMANMAmnadlkasd";

    public static void main(String[] args) throws Exception {

        boolean b = true;
        while (b) {
            String pwd = "";
            Scanner scanner = new Scanner(System.in);
            pwd = scanner.nextLine();
            Log.info(Utility.hashString(pwd));
        }

//        String encrypted = encrypt("texttoencrypt");
//        Log.info("Encrypted:" + encrypted);
//        Log.info("Decrypted:(" + decrypt(encrypted) + ")");
//        Log.info("clean:" + cleanPhoneNumber("+1(416)-878-5282"));
//        Log.info("clean:" + cleanPhoneNumber("1(416)-878-5282"));
//        Log.info("clean:" + cleanPhoneNumber("1 416 878 5282"));
//        Log.info("clean:" + cleanPhoneNumber("1.416.878.5282"));
//        
//        Log.info(Utility.truncateString("Truncate this long string which has too many characters"));
//        Log.info(Utility.truncateString("Truncate this short"));
//        Log.info(Utility.truncateString("Truncate this null"));
    }

    public static String encrypt(String in) {
        return encode(encode(in, key), key);
    }

    public static String decrypt(String in) {
        return decode(decode(in, key), key);
    }

    public static String encode(String s, String key) {
        return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
    }

    public static String decode(String s, String key) {
        return new String(xorWithKey(base64Decode(s), key.getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    private static byte[] base64Decode(String s) {
        Base64 d = new Base64();
        return d.decode(s);
    }

    private static String base64Encode(byte[] bytes) {
        Base64 enc = new Base64();
        return enc.encode(bytes).replaceAll("\\s", "");

    }

    static long m = 0;

    public static synchronized long generateId() {
        //UUID uuid = new UUID(Long.MIN_VALUE, Long.MAX_VALUE);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        //return uuid.node();
        return System.currentTimeMillis() + m++;
    }

    // NOT CORRECT
    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static boolean isValidUserID(String userID) {
        if (userID == null) {
            return false;
        } else if (userID.length() < 6) {
            return false;
        } else if (userID.length() > 64) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidRate(Rate rate) {
        if (rate == null) {
            return false;
        } else if (rate.getOpen() == 0) {
            return false;
        } else if (rate.getClose() == 0) {
            return false;
        } else if (rate.getHigh() == 0) {
            return false;
        } else if (rate.getLow() == 0) {
            return false;
        } 
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        if (password.length() > 21) {
            return false;
        }
        if (!password.matches("[a-zA-Z0-9]*")) {
            return false;
        }
        if (password.matches("[a-zA-Z]*99")) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * isPhoneNumberValid: Validate phone number using Java reg ex. This method
     * checks if the input string is a valid phone number.
     *
     * @param email String. Phone number to validate
     * @return boolean: true if phone number is valid, false otherwise.
     */
    public static boolean isValidTelephone(String phoneNumber) {
        boolean isValid = false;
        /* Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn
         ^\\(? : May start with an option "(" .
         (\\d{3}): Followed by 3 digits.
         \\)? : May have an optional ")"
         [- ]? : May have an optional "-" after the first 3 digits or after optional ) character.
         (\\d{3}) : Followed by 3 digits.
         [- ]? : May have another optional "-" after numeric digits.
         (\\d{4})$ : ends with four digits.

         Examples:
         (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890

         */
        //Initialize reg ex for phone number.
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static Color getColor(String hex) {
        hex = hex.trim();
        hex = hex.toLowerCase();
        hex = hex.replace("#", "");
        int intValue = Integer.parseInt(hex, 16);
        Color aColor = new Color(intValue);
        return aColor;
    }

    public static double addPercent(double amount, double percent) {
        double retVal = 0;
        retVal = (amount / 100) * percent;
        retVal = retVal + amount;
        return retVal;
    }

    public static double subtractPercent(double amount, double percent) {
        double retVal = 0;
        retVal = (amount / 100) * percent;
        retVal = retVal - amount;
        return retVal;
    }

    public static Date sqlDateNow() {
        java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public static String hashString(String s) {

        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(s.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                sb.append(0);
                sb.append(hex.charAt(hex.length() - 1));
            } else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public static String truncateString(String str, int len, String elipse) {
        if (str == null) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        }
        str = str.substring(0, len - elipse.length());
        str = str + elipse;
        return str;
    }

    public static String truncateString(String str) {
        return truncateString(str, 25, "...");
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String randomPassword() {
        return randomString(8);
    }

    public static String replaceLastEight(String s) {
        int length = s.length();
        int n = 8;
        //Check whether or not the string contains at least four characters; if not, this method is useless
        if (length < n) {
            return "Error: The provided string is not greater than " + n + " characters long.";
        }
        return s.substring(0, length - n) + "********";
    }
}
