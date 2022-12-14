package org.develcorp.services.account.model.enums;

import javax.persistence.Convert;

@Convert
public enum AccountType {

    S ("SAVINGS"),
    C ("CURRENT");

    private final String code;

    AccountType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
