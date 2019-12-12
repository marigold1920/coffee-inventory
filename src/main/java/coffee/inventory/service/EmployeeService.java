package coffee.inventory.service;

import org.springframework.stereotype.Service;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Employee;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.repository.EmployeeRepository;

import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseModel getWarehouse(Integer employeeId) {
        ResponseModel response = new ResponseModel();

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee e = employee.get();
        Warehouse warehouse = e.getWarehouse();
        response.addData(warehouse);

        return response;
    }
}
