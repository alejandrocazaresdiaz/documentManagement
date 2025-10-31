package com.clara.ops.document_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clara.ops.document_management.entity.DocumentEntity;
import com.clara.ops.document_management.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;

    public DocumentEntity guardar(DocumentEntity doc) {
        return repository.save(doc);
    }

    public List<DocumentEntity> listar() {
        return repository.findAll();
    }

    public DocumentEntity buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }


}
