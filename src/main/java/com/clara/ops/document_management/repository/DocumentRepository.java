package com.clara.ops.document_management.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clara.ops.document_management.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
 
    @Query("SELECT d FROM DocumentEntity d " 
            +"WHERE (:userOwner IS NULL OR d.userOwner = :userOwner) " 
            +"OR (:name IS NULL OR d.name LIKE %:name%) " 
            +"OR (:tags IS NULL OR EXISTS (SELECT t FROM d.tags t WHERE t IN :tags)) " 
            //+"ORDER BY d.createdAt DESC")
            )
     List<DocumentEntity> searchDocuments(
         @Param("userOwner") String userOwner,
         @Param("name") String name,
         @Param("tags") List<String> tags,
         Pageable pageable
     );
    
    
    @Query("SELECT COUNT(d) FROM DocumentEntity d " +
            "WHERE (:userOwner IS NULL OR d.userOwner = :userOwner) " +
            "OR (:name IS NULL OR d.name LIKE %:name%) " +
            "OR (:tags IS NULL OR EXISTS (SELECT t FROM d.tags t WHERE t IN :tags)) ")
     long countDocumentsByFilters(
         @Param("userOwner") String userOwner,
         @Param("name") String name,
         @Param("tags") List<String> tags
     );

    
}
