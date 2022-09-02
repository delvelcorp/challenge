package org.develcorp.services.account.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionError extends RuntimeException {

    private final int code;
    private final Error error;

}
