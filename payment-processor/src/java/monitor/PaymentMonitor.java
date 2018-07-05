/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Sean Beecroft
 */
public class PaymentMonitor {
    PaymentManager manager = null;
    public PaymentMonitor(PaymentManager manager)
    {
        this.manager = manager;
    }
    public void update()
    {
        ArrayList<Payment> incompleteList = manager.getIncompletePayments();
        updateReceivedPayments(incompleteList);
        ArrayList<Payment> receivedList = manager.getReceivedPayments();
        updateCompletedPayments(receivedList);
        
        
        
    }

    public void updateCompletedPayments(ArrayList<Payment> list)
    {
        Iterator itr = list.iterator();
        while(itr.hasNext()) { 
            Payment payment = (Payment)itr.next();
            boolean pr = isPaymentCompleted(payment);
            if( pr ){
                payment.setPaymentCompleted(pr);
                payment.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                payment.setTimePaymentCompleted(new Timestamp(System.currentTimeMillis()));
            }
        }        
    }
    
    
    public void updateReceivedPayments(ArrayList<Payment> list)
    {
        Iterator itr = list.iterator();
        while(itr.hasNext()) { 
            Payment payment = (Payment)itr.next();
            boolean pr = isPaymentReceived(payment);
            if( pr ){
                payment.setPaymentReceived(pr);
                payment.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                payment.setTimePaymentReceived(new Timestamp(System.currentTimeMillis()));
            } else {
                // if the date on the payment is after expiry, expire the payment.
                if( payment.getTimePaymentExpires().after(new Timestamp(System.currentTimeMillis()))){
                    payment.setPaymentCompleted(false);
                    payment.setPaymentReceived(false);
                    payment.setPaymentExpired(true);
                }
            }
        }        
    }
    
    public boolean isPaymentReceived(Payment payment)
    {
        return false;
    }
    
    public boolean isPaymentCompleted(Payment payment)
    {
        return false;
    }
}
