package com.clara.ops.document_management.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DocumentEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userOwner;

    private String type;

//    @Column(length = 1000)
//    private String descripcion;

    private List<String> tags;

    private long size;
    
    private Date createdAt;
}
