package com.kb.goaler.account_history.constant;

import lombok.Getter;

@Getter
public enum Type {

    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    Type(String value) {
        this.value = value;
    }
}
