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
public class PaymentManager {
    ArrayList<Payment> payments = new ArrayList<Payment>();
    
    public ArrayList<Payment> getPayments()
    {
        return payments;
    }
    
    public ArrayList<Payment> getIncompletePayments()
    {
        return payments;
    }
    
    public ArrayList<Payment> getExpiredPayments()
    {
        return payments;
    }
    
    public ArrayList<Payment> getReceivedPayments()
    {
        return payments;
    }
    
    public void add(Payment payment)
    {
        payments.add(payment);
    }
    
    
}
