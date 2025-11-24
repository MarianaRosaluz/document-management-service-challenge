package com.clara.ops.challenge.documentManagement.infrastructure.postgres;

import com.clara.ops.challenge.documentManagement.infrastructure.entity.DocumentMetadata;
import java.time.LocalDateTime;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.EmptyHandling;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DocumentMetadataRepository {
    private final Jdbi jdbi;

    public DocumentMetadataRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void save(DocumentMetadata documentMetadata) {
        String sql = "INSERT INTO document_metadata (uuid, \"user\", name, tags, minio_path, file_size, file_type, created_at) " +
                "VALUES (:uuid, :user, :name, :tags, :minioPath, :fileSize, :fileType, :createdAt)";

        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(documentMetadata)
                        .execute()
        );
    }

    public List<DocumentMetadata> findByFilters(
            String user,
            String name,
            List<String> tags,
            int offset,
            int limit
    ) {
        String sql = "SELECT * FROM document_metadata WHERE " +
                "(:user is null OR \"user\" = :user) AND " +
                "(:name is null OR name ILIKE '%' || :name || '%') AND " +
                "(:tagsIsEmpty OR (tags @> ARRAY[:tags]::varchar[])) " +
                "ORDER BY created_at DESC " +
                "LIMIT :limit OFFSET :offset";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bindArray("tags", String.class, tags)
                        .bind("tagsIsEmpty", tags.isEmpty())
                        .bind("user", user)
                        .bind("name", name)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(DocumentMetadata.class)
                        .list()
        );
    }

    public DocumentMetadata findById(UUID uuid) {
        String sql = "SELECT * FROM document_metadata WHERE uuid = :uuid";
        return jdbi.withHandle(handle ->
            handle.createQuery(sql)
                .bind("uuid", uuid)
                .mapToBean(DocumentMetadata.class)
                .findOne()
                .orElse(null)
        );
    }
}
