package com.xgit.boot.service.impl;

import com.xgit.boot.dao.StorageDao;
import com.xgit.boot.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianxuanxuan
 * On 2020-09-29 14:32
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }
}
