package coffee.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.EmployeeService;
import coffee.inventory.service.JwtService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1.0/employees")
public class EmployeeController {

    private final HttpServletRequest request;
    private final JwtService jwtService;

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, JwtService jwtService, HttpServletRequest request) {
        this.employeeService = employeeService;
        this.jwtService = jwtService;
        this.request = request;
    }

    @GetMapping("/get_warehouse")
    public ResponseModel getWarehouse() {
        String authorization = request.getHeader("Authorization");
        String employeeId = jwtService.getEmployeeIdFromToken(authorization);
        return employeeService.getWarehouse(Integer.valueOf(employeeId));
    }
}
