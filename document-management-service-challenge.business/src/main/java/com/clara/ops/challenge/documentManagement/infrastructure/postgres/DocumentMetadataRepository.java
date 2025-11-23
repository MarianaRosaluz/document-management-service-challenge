package com.clara.ops.challenge.documentManagement.infrastructure.postgres;

import com.clara.ops.challenge.documentManagement.infrastructure.entity.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentMetadataRepository extends JpaRepository<DocumentMetadata, UUID> {

    List<DocumentMetadata> findByUser(String user);

    List<DocumentMetadata> findByNameContainingIgnoreCase(String name);

    List<DocumentMetadata> findByFileType(String fileType);
}
