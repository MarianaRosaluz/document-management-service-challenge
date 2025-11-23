package com.clara.ops.challenge.documentManagement.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document_metadata")
public class DocumentMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tags", columnDefinition = "text[]", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    @Column(name = "minio_path", nullable = false)
    private String minioPath;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize = 0;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getMinioPath() { return minioPath; }
    public void setMinioPath(String minioPath) { this.minioPath = minioPath; }

    public Integer getFileSize() { return fileSize; }
    public void setFileSize(Integer fileSize) { this.fileSize = fileSize; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
