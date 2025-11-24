package com.clara.ops.challenge.api;

import com.clara.ops.challenge.documentManagement.domain.vos.SearchDocumentsResponse;
import com.clara.ops.challenge.documentManagement.infrastructure.postgres.DocumentMetadataRepository;
import com.clara.ops.challenge.documentManagement.service.MinioStorageService;
import java.util.Collections;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.clara.ops.challenge.documentManagement.domain.vos.UploadDocumentMetaDataRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;
import com.clara.ops.challenge.documentManagement.infrastructure.entity.DocumentMetadata;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/document-management")
@RestController
public class DocumentManagementController {

    private final MinioStorageService storageService;
    private final DocumentMetadataRepository documentMetadataRepository;


    public DocumentManagementController(
            MinioStorageService storageService,
            DocumentMetadataRepository documentMetadataRepository
    ) {
        this.storageService = storageService;
        this.documentMetadataRepository = documentMetadataRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @ModelAttribute("metadata") final UploadDocumentMetaDataRequest uploadDocumentMetaDataRequest,
            @RequestPart("document") MultipartFile file
            ) throws Exception {


        storageService.upload(
                file.getOriginalFilename(),
                file.getInputStream(),
                file.getContentType()
        );

        documentMetadataRepository.save(
                uploadDocumentMetaDataRequest.toDocumentMetadataEntity(
                        file.getOriginalFilename(),
                        (int) file.getSize(),
                        file.getContentType()
                )
        );

      System.out.println("User: " + uploadDocumentMetaDataRequest.user());
      System.out.println("Document Name: " + uploadDocumentMetaDataRequest.name());
      System.out.println("Tags: " + uploadDocumentMetaDataRequest.tags());
      System.out.println("Original Filename: " + file.getOriginalFilename());

      return ResponseEntity.status(HttpStatus.CREATED).build();
     }

    @GetMapping("/search")
    public ResponseEntity<?> searchDocuments(
            @RequestParam(value = "user", required = false) String user,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        List<String> safeTags = (tags == null) ? Collections.emptyList() : tags;

        List<DocumentMetadata> docs = documentMetadataRepository.findByFilters(user, name, safeTags, page, size);
        List<SearchDocumentsResponse> response = docs.stream()
                .map(doc -> doc.toSearchDocumentsResponse(doc))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> getDownloadUrl(@PathVariable("id") String id) {
        DocumentMetadata metadata = documentMetadataRepository.findById(UUID.fromString(id));
        if (metadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
        String fileName = metadata.getMinioPath() != null ? metadata.getMinioPath() : metadata.getName();
        try {
            // 15 minutes expiry
            String url = storageService.getPresignedUrl(fileName, 900);
            return ResponseEntity.ok(Collections.singletonMap("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate download URL");
        }
    }



}
