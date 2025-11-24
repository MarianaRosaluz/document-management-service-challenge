package com.clara.ops.challenge.documentManagement.domain.vos;

import com.clara.ops.challenge.documentManagement.infrastructure.entity.DocumentMetadata;
import java.util.List;
import java.util.UUID;



public record SearchDocumentsResponse(
        UUID uuid,
        String user,
        String name,
        List<String>tags,
        Integer fileSize,
        String fileType,
        java.time.LocalDateTime createdAt
) {

}
