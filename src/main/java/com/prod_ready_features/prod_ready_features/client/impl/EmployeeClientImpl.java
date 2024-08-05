package com.prod_ready_features.prod_ready_features.client.impl;

import com.prod_ready_features.prod_ready_features.client.EmployeeClient;
import com.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger logger = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        logger.error("error log");

         try{
             List<EmployeeDTO> employeeDTOList = restClient.get()
                     .uri("employees")
                     .retrieve()
                     .body(new ParameterizedTypeReference<>() {
                     });
             return employeeDTOList;
         } catch (Exception e){
             throw new RuntimeException(e);
         }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            EmployeeDTO employeeDTO = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            EmployeeDTO employeeDTO1 = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
