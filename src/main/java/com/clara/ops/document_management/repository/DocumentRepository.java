package com.clara.ops.document_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clara.ops.document_management.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    
}
