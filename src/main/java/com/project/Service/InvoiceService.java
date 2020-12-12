package com.project.Service;

import com.project.DB.InvoiceDB;
import com.project.entities.Invoice;
import com.project.enums.InvoiceStatus;

import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    public void addInvoice(Invoice invoice){
        InvoiceDB.addInvoice(invoice);
    }

    public List<Invoice> getInvoices(){
        return InvoiceDB.getAllInvoices();
    }

    public List<Invoice> getInvoiceByStatus(InvoiceStatus invoiceStatus, int userId){
        return InvoiceDB.getInByUserIdAndStatus(userId,invoiceStatus);
    }
}
