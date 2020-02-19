package com.alek.influentialpeople.common.abstraction;

import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService<E, M> {

    Page<E> findPaged(M model);

    List<E> findList(M model);
}
