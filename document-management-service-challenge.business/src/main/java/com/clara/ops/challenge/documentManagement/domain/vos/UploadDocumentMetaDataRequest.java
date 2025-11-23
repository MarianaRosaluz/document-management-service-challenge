package com.clara.ops.challenge.documentManagement.domain.vos;

import java.util.List;

public record UploadDocumentMetaDataRequest(
        String user,
        String name,
        List<String> tags
) {

}
