package com.grupocumb.petroastur.service;

import com.grupocumb.petroastur.model.TransactionStatus;

public interface UpdateService {
    void update();

    TransactionStatus getStatus();
}
