package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneralService<T> {

    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    void save(T model);

    void remove(Long id);
}
