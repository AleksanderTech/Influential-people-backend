package com.alek.influentialpeople.common.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {

    T findOne(ID id);

    Page<T> findAll(Pageable pageable);

    T create(T hero);

    T update(ID id, T changes);

    void delete(ID id);
}
