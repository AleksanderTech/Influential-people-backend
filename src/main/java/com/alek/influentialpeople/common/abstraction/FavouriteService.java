package com.alek.influentialpeople.common.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouriteService<T, ID> {

    T find(ID id);

    Page<T> findAll(Pageable pageable);

    void add(ID id);

    void delete(ID id);
}
