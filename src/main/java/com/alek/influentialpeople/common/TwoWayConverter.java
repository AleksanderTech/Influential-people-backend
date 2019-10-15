package com.alek.influentialpeople.common;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TwoWayConverter<E, M> {

    public abstract M convert(E from);

    public abstract E convertBack(M from);

    public List<M> convertMany(Collection<? extends E> collection) {
        return collection.stream().map(element -> convert(element)).collect(Collectors.toList());
    }

    public List<E> convertManyBack(Collection<? extends M> collection) {
        return collection.stream().map(element -> convertBack(element)).collect(Collectors.toList());
    }
}
