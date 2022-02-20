package io.github.radcraftplay.playeractivity.repositories;

import java.util.Collection;
import java.util.function.Predicate;

public interface Repository<ID, ENTITY> {
    Collection<ENTITY> GetAll();
    ENTITY query(ID id);
    Collection<ENTITY> query(Predicate<ENTITY> condition);

    ID add(ENTITY entity);
    ENTITY update(ID id, ENTITY entity);
    boolean delete(ID id);
}
