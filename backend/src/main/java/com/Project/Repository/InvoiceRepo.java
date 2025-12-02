package com.Project.Repository;

import com.Project.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    List<Invoice> findByMemberId(Long memberId);
}