package servlet;
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
import com.Calculation;
import com.CalculationFactory;
import com.Instrument;
import com.Quote;
import com.QuoteFactory;
import com.Tranche;
import com.feed.BitcoinAverageFeed;
import com.feed.BitcoinChartFeed;
import com.feed.BlockChainInfoFeed;
import com.feed.Feed;
import static com.feed.Feed.getHERCPricePerBTC;
import static com.feed.Feed.getHERCPricePerETH;
import com.feed.GenericFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean Beecroft
 */
public class HERCServlet extends HttpServlet {



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            System.out.println("Create GSON");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            Integer requiredData = null;
            
            try { 
                requiredData = Integer.parseInt(request.getParameter("dataRequiredKB"));
            } catch(Exception ex) {
                requiredData = new Integer(100000);
            }
            
            Calculation calc = CalculationFactory.getCalculation(requiredData);
            
            System.out.println("Return Calculation");
            Type type = new TypeToken<Calculation>(){}.getType();
            String json = gson.toJson(calc, type);
            String formattedJson = json; //Utility.beautify(json);

            System.out.println("Return Calculation:" + json);
            out.println(formattedJson);
            System.out.println("Write Calculation");
            out.flush();
            System.out.println("Flush Calculation");
            out.close();
            System.out.println("Closed");
        } catch(Exception ex) { 
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
