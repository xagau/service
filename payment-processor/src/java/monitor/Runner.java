/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.ArrayList;

/**
 *
 * @author Sean Beecroft
 */
public class Runner {
    
    
    public static void main(String[] args)
    {
        PaymentManager manager = new PaymentManager();
        PaymentMonitor monitor = new PaymentMonitor(manager);
        monitor.update();
        
        
    }
}
