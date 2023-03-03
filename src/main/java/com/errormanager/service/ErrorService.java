package com.errormanager.service;

import com.errormanager.dto.createerror.CreateErrorRequest;
import com.errormanager.dto.createerror.CreateErrorResponse;
import com.errormanager.dto.geterror.GetErrorRequest;
import com.errormanager.dto.geterror.GetErrorResponse;
import com.errormanager.dto.exists.ExistsErrorResponse;

public interface ErrorService {

    GetErrorResponse getError(GetErrorRequest getErrorRequest);
    CreateErrorResponse createError(CreateErrorRequest createErrorRequest);

    ExistsErrorResponse existsError(String service, String endpoint, String id);
}
