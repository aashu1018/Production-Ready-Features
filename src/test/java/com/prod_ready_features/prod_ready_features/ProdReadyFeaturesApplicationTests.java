package com.prod_ready_features.prod_ready_features;

import com.prod_ready_features.prod_ready_features.client.EmployeeClient;
import com.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void getAllEmployees(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	void getEmployeeByID(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	void createNewEmployee(){
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "Rahul", "Rahul@gmail.com", 2, "User", LocalDate.of(2021, 9, 27), true, 100000d);
		EmployeeDTO savedEmployee = employeeClient.createNewEmployee(employeeDTO);
	}

}
