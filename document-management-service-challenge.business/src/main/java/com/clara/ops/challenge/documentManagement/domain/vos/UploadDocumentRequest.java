package com.clara.ops.challenge.documentManagement.domain.vos;

import java.util.List;

public record UploadDocumentRequest(
        String user,
        String name,
        List<String> tags
) {

}
