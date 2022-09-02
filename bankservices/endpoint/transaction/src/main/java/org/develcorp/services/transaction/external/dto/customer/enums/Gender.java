package org.develcorp.services.transaction.external.dto.customer.enums;

import javax.persistence.Convert;

@Convert
public enum Gender {

    MAN ("H"),
    WOMAN ("M");

    private final String code;

    Gender(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
