package coffee.inventory.service;

import java.util.Collection;

import coffee.inventory.entity.Unit;

public interface UnitService {
    Collection<Unit> findAllUnits();
}