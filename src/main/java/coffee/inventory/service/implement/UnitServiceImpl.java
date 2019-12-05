package coffee.inventory.service.implement;

import java.util.Collection;

import org.springframework.stereotype.Service;

import coffee.inventory.entity.Unit;
import coffee.inventory.repository.UnitRepository;
import coffee.inventory.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {

    private UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Collection<Unit> findAllUnits() {

        return unitRepository.findAll();
    }
}