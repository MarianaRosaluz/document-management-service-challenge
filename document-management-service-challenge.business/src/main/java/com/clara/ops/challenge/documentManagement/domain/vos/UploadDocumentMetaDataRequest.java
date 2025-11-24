package com.clara.ops.challenge.documentManagement.domain.vos;

import com.clara.ops.challenge.documentManagement.infrastructure.entity.DocumentMetadata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UploadDocumentMetaDataRequest(
        String user,
        String name,
        List<String> tags
) {
    public DocumentMetadata toDocumentMetadataEntity(String minioPath, int fileSize, String fileType) {
        DocumentMetadata entity = new DocumentMetadata();
        entity.setUuid(UUID.randomUUID());
        entity.setUser(this.user());
        entity.setName(this.name());
        entity.setTags(this.tags());
        entity.setMinioPath(minioPath);
        entity.setFileSize(fileSize);
        entity.setFileType(fileType);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
}
