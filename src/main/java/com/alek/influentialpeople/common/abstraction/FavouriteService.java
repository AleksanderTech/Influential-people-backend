package com.alek.influentialpeople.common.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouriteService<T, ID> {

    T find(ID ownerId);

    Page<T> find(Pageable pageable);

    void add(ID ownerId);

    void delete(ID ownerId);
}
