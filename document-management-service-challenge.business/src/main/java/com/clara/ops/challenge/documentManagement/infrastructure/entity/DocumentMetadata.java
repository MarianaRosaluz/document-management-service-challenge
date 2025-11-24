package com.clara.ops.challenge.documentManagement.infrastructure.entity;

import com.clara.ops.challenge.documentManagement.domain.vos.SearchDocumentsResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadata {
    private UUID uuid;
    private String user;
    private String name;
    private List<String> tags;
    private String minioPath;
    private Integer fileSize = 0;
    private String fileType;
    private LocalDateTime createdAt = LocalDateTime.now();

    public SearchDocumentsResponse toSearchDocumentsResponse(DocumentMetadata doc) {
        return new SearchDocumentsResponse(
                doc.getUuid(),
                doc.getUser(),
                doc.getName(),
                doc.getTags(),
                doc.getFileSize(),
                doc.getFileType(),
                doc.getCreatedAt()
        );
    }
}
