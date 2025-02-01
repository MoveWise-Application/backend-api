package com.movewise.movewise_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movewise.movewise_api.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

}
