package org.develcorp.services.transaction.model.error;

import lombok.NoArgsConstructor;
import org.develcorp.services.transaction.error.Error;
import org.develcorp.services.transaction.error.ExceptionError;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class TransactionError {

    private static final String ERROR_CODE_001 = "E001";
    private static final String ERROR_DETAIL_001 = "Transaction not found.";

    private static final String ERROR_CODE_002 = "E002";
    private static final String ERROR_DETAIL_002 = "Transaction already exist.";

    private static final String ERROR_CODE_003 = "E003";
    private static final String ERROR_DETAIL_003 = "Transaction information is incomplete.";

    private static final String ERROR_CODE_004 = "E004";
    private static final String ERROR_DETAIL_004 = "Transaction not can delete, that is actually referred from the database.";

    public static final ExceptionError E001 = new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error(ERROR_CODE_001, ERROR_DETAIL_001));
    public static final ExceptionError E002 = new ExceptionError(HttpStatus.PRECONDITION_FAILED.value(), new Error(ERROR_CODE_002, ERROR_DETAIL_002));

    public static final ExceptionError E003 = new ExceptionError(HttpStatus.PRECONDITION_FAILED.value(), new Error(ERROR_CODE_003, ERROR_DETAIL_003));

    public static final ExceptionError E004 = new ExceptionError(HttpStatus.NOT_FOUND.value(), new Error(ERROR_CODE_004, ERROR_DETAIL_004));
}
