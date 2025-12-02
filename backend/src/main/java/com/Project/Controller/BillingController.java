package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "*")
public class BillingController {
    private final BillingService billingService;

    public BillingController(BillingService billingService) { this.billingService = billingService; }

    @PostMapping("/members/{memberId}/invoices")
    public ResponseEntity<Invoice> createInvoice(@PathVariable Long memberId, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(billingService.createInvoice(memberId, invoice));
    }

    @PostMapping("/invoices/{invoiceId}/payments")
    public ResponseEntity<Payment> payInvoice(@PathVariable Long invoiceId, @RequestBody Payment payment) {
        return ResponseEntity.ok(billingService.payInvoice(invoiceId, payment));
    }
}