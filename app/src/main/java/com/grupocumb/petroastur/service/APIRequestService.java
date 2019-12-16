package com.grupocumb.petroastur.service;

import com.grupocumb.petroastur.model.TransactionStatus;

public interface APIRequestService {
    TransactionStatus getTransactionStatus();

    void update(SQLService sqlService);
}
