/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.sql.Timestamp;
import java.util.UUID;

/**
 *
 * @author Sean Beecroft
 */
public class Payment {

    /**
     * @return the paymentExpired
     */
    public boolean isPaymentExpired() {
        return paymentExpired;
    }

    /**
     * @param paymentExpired the paymentExpired to set
     */
    public void setPaymentExpired(boolean paymentExpired) {
        this.paymentExpired = paymentExpired;
    }
    private String id = UUID.randomUUID().toString();
    private String paymentCurrency;
    private double paymentAmount;
    
    private double settlentAmount;
    private String settlmentCurrency;
    
    private Timestamp timePaymentInitiated;
    private Timestamp timePaymentReceived;
    private Timestamp timePaymentCompleted;
    
    private Timestamp timePaymentExpires;
    private Timestamp timeSettlementOccured;
    private Timestamp timeSettlementReceived;
    private Timestamp timeSettlementCompleted;
    
    private Timestamp lastUpdated;
    
    private boolean paymentCompleted;
    private boolean paymentReceived;
    private boolean settlementCompleted;
    private boolean settlementReceived;
    private boolean paymentExpired = false;
    
    private String paymentTxid;
    private String paymentAddress;
    
    private String settlementTxid;
    private String settlementAddress;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the paymentCurrency
     */
    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    /**
     * @param paymentCurrency the paymentCurrency to set
     */
    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    /**
     * @return the paymentAmount
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * @return the settlentAmount
     */
    public double getSettlentAmount() {
        return settlentAmount;
    }

    /**
     * @param settlentAmount the settlentAmount to set
     */
    public void setSettlentAmount(double settlentAmount) {
        this.settlentAmount = settlentAmount;
    }

    /**
     * @return the settlmentCurrency
     */
    public String getSettlmentCurrency() {
        return settlmentCurrency;
    }

    /**
     * @param settlmentCurrency the settlmentCurrency to set
     */
    public void setSettlmentCurrency(String settlmentCurrency) {
        this.settlmentCurrency = settlmentCurrency;
    }

    /**
     * @return the timePaymentInitiated
     */
    public Timestamp getTimePaymentInitiated() {
        return timePaymentInitiated;
    }

    /**
     * @param timePaymentInitiated the timePaymentInitiated to set
     */
    public void setTimePaymentInitiated(Timestamp timePaymentInitiated) {
        this.timePaymentInitiated = timePaymentInitiated;
    }

    /**
     * @return the timePaymentExpires
     */
    public Timestamp getTimePaymentExpires() {
        return timePaymentExpires;
    }

    /**
     * @param timePaymentExpires the timePaymentExpires to set
     */
    public void setTimePaymentExpires(Timestamp timePaymentExpires) {
        this.timePaymentExpires = timePaymentExpires;
    }

    /**
     * @return the timeSettlementOccured
     */
    public Timestamp getTimeSettlementOccured() {
        return timeSettlementOccured;
    }

    /**
     * @param timeSettlementOccured the timeSettlementOccured to set
     */
    public void setTimeSettlementOccured(Timestamp timeSettlementOccured) {
        this.timeSettlementOccured = timeSettlementOccured;
    }

    /**
     * @return the lastUpdated
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the paymentCompleted
     */
    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    /**
     * @param paymentCompleted the paymentCompleted to set
     */
    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    /**
     * @return the paymentReceived
     */
    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    /**
     * @param paymentReceived the paymentReceived to set
     */
    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    /**
     * @return the settlementCompleted
     */
    public boolean isSettlementCompleted() {
        return settlementCompleted;
    }

    /**
     * @param settlementCompleted the settlementCompleted to set
     */
    public void setSettlementCompleted(boolean settlementCompleted) {
        this.settlementCompleted = settlementCompleted;
    }

    /**
     * @return the settlementReceived
     */
    public boolean isSettlementReceived() {
        return settlementReceived;
    }

    /**
     * @param settlementReceived the settlementReceived to set
     */
    public void setSettlementReceived(boolean settlementReceived) {
        this.settlementReceived = settlementReceived;
    }

    /**
     * @return the paymentTxid
     */
    public String getPaymentTxid() {
        return paymentTxid;
    }

    /**
     * @param paymentTxid the paymentTxid to set
     */
    public void setPaymentTxid(String paymentTxid) {
        this.paymentTxid = paymentTxid;
    }

    /**
     * @return the paymentAddress
     */
    public String getPaymentAddress() {
        return paymentAddress;
    }

    /**
     * @param paymentAddress the paymentAddress to set
     */
    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    /**
     * @return the settlementTxid
     */
    public String getSettlementTxid() {
        return settlementTxid;
    }

    /**
     * @param settlementTxid the settlementTxid to set
     */
    public void setSettlementTxid(String settlementTxid) {
        this.settlementTxid = settlementTxid;
    }

    /**
     * @return the settlementAddress
     */
    public String getSettlementAddress() {
        return settlementAddress;
    }

    /**
     * @param settlementAddress the settlementAddress to set
     */
    public void setSettlementAddress(String settlementAddress) {
        this.settlementAddress = settlementAddress;
    }

    /**
     * @return the timePaymentReceived
     */
    public Timestamp getTimePaymentReceived() {
        return timePaymentReceived;
    }

    /**
     * @param timePaymentReceived the timePaymentReceived to set
     */
    public void setTimePaymentReceived(Timestamp timePaymentReceived) {
        this.timePaymentReceived = timePaymentReceived;
    }

    /**
     * @return the timePaymentCompleted
     */
    public Timestamp getTimePaymentCompleted() {
        return timePaymentCompleted;
    }

    /**
     * @param timePaymentCompleted the timePaymentCompleted to set
     */
    public void setTimePaymentCompleted(Timestamp timePaymentCompleted) {
        this.timePaymentCompleted = timePaymentCompleted;
    }

    /**
     * @return the timeSettlementReceived
     */
    public Timestamp getTimeSettlementReceived() {
        return timeSettlementReceived;
    }

    /**
     * @param timeSettlementReceived the timeSettlementReceived to set
     */
    public void setTimeSettlementReceived(Timestamp timeSettlementReceived) {
        this.timeSettlementReceived = timeSettlementReceived;
    }

    /**
     * @return the timeSettlementCompleted
     */
    public Timestamp getTimeSettlementCompleted() {
        return timeSettlementCompleted;
    }

    /**
     * @param timeSettlementCompleted the timeSettlementCompleted to set
     */
    public void setTimeSettlementCompleted(Timestamp timeSettlementCompleted) {
        this.timeSettlementCompleted = timeSettlementCompleted;
    }
    
          
}
