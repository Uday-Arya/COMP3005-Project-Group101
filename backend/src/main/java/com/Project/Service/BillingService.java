package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BillingService {
    private final InvoiceRepo invoiceRepo;
    private final PaymentRepo paymentRepo;
    private final MemberRepo memberRepo;

    public BillingService(InvoiceRepo invoiceRepo,
                          PaymentRepo paymentRepo,
                          MemberRepo memberRepo) {
        this.invoiceRepo = invoiceRepo;
        this.paymentRepo = paymentRepo;
        this.memberRepo = memberRepo;
    }

    @Transactional
    public Invoice createInvoice(Long memberId, Invoice invoice) {
        Member m = memberRepo.findById(memberId).orElseThrow();
        invoice.setMember(m);
        invoice.setIssuedAt(LocalDateTime.now());
        invoice.setStatus("PENDING");
        return invoiceRepo.save(invoice);
    }

    @Transactional
    public Payment payInvoice(Long invoiceId, Payment payment) {
        Invoice inv = invoiceRepo.findById(invoiceId).orElseThrow();
        payment.setInvoice(inv);
        payment.setPaidAt(LocalDateTime.now());
        Payment saved = paymentRepo.save(payment);

        double totalPaid = inv.getPayments() == null ? 0.0 :
                inv.getPayments().stream().mapToDouble(Payment::getAmount).sum();
        totalPaid += payment.getAmount();
        if (totalPaid >= inv.getTotalAmount()) inv.setStatus("PAID");
        invoiceRepo.save(inv);
        return saved;
    }
}
