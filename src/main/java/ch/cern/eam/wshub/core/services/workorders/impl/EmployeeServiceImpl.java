package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.workorders.EmployeeService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.Employee;
import ch.cern.eam.wshub.core.repositories.EmployeeRepository;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private Tools tools;

    private ApplicationData applicationData;

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public EmployeeServiceImpl(ApplicationData applicationData, Tools tools, EmployeeRepository employeeRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.employeeRepository = employeeRepository;
    }

    public BatchResponse<String> createEmployeeBatch(InforContext context, List<Employee> workOrderParam) {
        return tools.batchOperation(context, this::createEmployee, workOrderParam);
    }

    public BatchResponse<String> updateEmployeeBatch(InforContext context, List<Employee> workOrders) {
        return tools.batchOperation(context, this::updateEmployee, workOrders);
    }

    public Employee readEmployee(InforContext context, String employeeCode) throws InforException {
        return employeeRepository.findById(employeeCode).orElse(null);
    }

    public String createEmployee(InforContext context, Employee employee) throws InforException {
        Employee saved = employeeRepository.save(employee);
        return saved.getCode();
    }

    public String updateEmployee(InforContext context, Employee employee) throws InforException {
        Employee saved = employeeRepository.save(employee);
        return saved.getCode();
    }

    public String deleteEmployee(InforContext context, String employeeCode) throws InforException {
        employeeRepository.deleteById(employeeCode);
        return employeeCode;
    }
}
