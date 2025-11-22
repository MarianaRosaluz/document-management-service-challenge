package com.clara.ops.challenge.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clara.ops.challenge.documentManagement.domain.vos.UploadDocumentRequest;

@RequestMapping("/document-management/upload")
@RestController
public class DocumentManagementController {

  @PostMapping
    public ResponseEntity<?> uploadDocument(@RequestBody final UploadDocumentRequest uploadDocumentRequest){

      return ResponseEntity.status(HttpStatus.CREATED).build();
     }


}
