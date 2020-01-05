package com.alek.influentialpeople.common;

import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchFilterService<E, M> {

    Page<E> findPaged(M model);

    List<E> findList(M model);
}
