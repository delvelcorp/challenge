package org.develcorp.services.account.external.dto.transaction.enums;

import javax.persistence.Convert;

@Convert
public enum TransactionType {

    DEPOSIT ("D"),
    WITHDRAWAL  ("R"),
    OPENING ("A");

    private final String code;

    TransactionType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
