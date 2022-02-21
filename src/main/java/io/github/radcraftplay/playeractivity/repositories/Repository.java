package io.github.radcraftplay.playeractivity.repositories;

import java.util.Collection;
import java.util.function.Predicate;

public interface Repository<ID, ENTITY> {
    Collection<ENTITY> getAll();
    ENTITY get(ID id);
    ID add(ENTITY entity);
    ENTITY update(ID id, ENTITY entity);
    boolean delete(ID id);
}
