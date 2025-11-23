package com.clara.ops.challenge.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.clara.ops.challenge.documentManagement.domain.vos.UploadDocumentMetaDataRequest;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/document-management/upload")
@RestController
public class DocumentManagementController {

  @PostMapping
    public ResponseEntity<?> uploadDocument(
            @ModelAttribute("metadata") final UploadDocumentMetaDataRequest uploadDocumentMetaDataRequest,
            @RequestPart("document") MultipartFile file
            ){

      System.out.println("User: " + uploadDocumentMetaDataRequest.user());
      System.out.println("Document Name: " + uploadDocumentMetaDataRequest.name());
      System.out.println("Tags: " + uploadDocumentMetaDataRequest.tags());
      System.out.println("Original Filename: " + file.getOriginalFilename());

      // validar se é um pdf
      //validar tamanho máximo 500MB
      return ResponseEntity.status(HttpStatus.CREATED).build();
     }
}
