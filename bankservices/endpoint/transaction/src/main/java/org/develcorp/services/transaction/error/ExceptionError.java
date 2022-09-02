package org.develcorp.services.transaction.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionError extends RuntimeException {

    private final int code;
    private final Error error;

}
