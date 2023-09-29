package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.annotation.CustomerValidationService;
import com.github.knextsunj.cms.domain.Customer;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.CustomerDTO;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.CustomerMapper;
import com.github.knextsunj.cms.repository.CustomerRepository;
import com.github.knextsunj.cms.repository.CustomerStatusRepository;
import com.github.knextsunj.cms.service.CustomerService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsExceptionUtil;
import com.github.knextsunj.cms.util.CmsUtil;
import com.github.knextsunj.cms.util.MapperUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Local(CustomerService.class)
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerMapper customerMapper;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    @CustomerValidationService
    private GenericValidationService genericValidationService;

    @Inject
    private CustomerStatusRepository customerStatusRepository;


    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        if (!CmsUtil.isNull(customerDTO) && !CmsUtil.isNull(customerDTO.getName())) {
            if (customerRepository.count() != 0L && !genericValidationService.deDup(customerDTO.getName())) {
                throw new ValidationFailureException("Customer with given name: " + customerDTO.getName() + " already registered");
            }
            CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(customerDTO.getCustomerStatusDescr());
            Customer newCustomer = customerMapper.setDates(customerMapper.fromCustomerDTO(customerDTO));
            newCustomer.setCustomerStatus(customerStatus);
            Customer savedCustomer = customerRepository.save(newCustomer);
            if (!CmsUtil.isNull(savedCustomer)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        if (Optional.ofNullable(customerDTO).isPresent() && CmsUtil.isNumPresent(customerDTO.getId())) {
            Optional<Customer> customerOptional = customerRepository.findOptionalBy(customerDTO.getId());
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                if (!CmsUtil.isNull(customerDTO.getDeleted()) && customerDTO.getDeleted().equals("Y")) {
                    customer.setDeleted("Y");
                }

                if (!CmsUtil.isNull(customerDTO.getCustomerStatusDescr()) && customerDTO.getCustomerStatusDescr().equals("INACTIVE")) {
                    CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName("INACTIVE");
                    if (CmsUtil.isNull(customerStatus)) {
                        customer.setCustomerStatus(customerStatus);
                    }
                }
                if (!CmsUtil.isNull(customerDTO.getName())) {
                    customer.setName(customerDTO.getName());
                }

                Customer updatedCustomer = customerRepository.save(customerMapper.setDates(MapperUtil.updateCustomerDetails(customerDTO,customer)));
                if (updatedCustomer != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAllByActiveStatusAndDeleted("ACTIVE", "N").stream().map(customer -> {
            return customerMapper.toCustomerDTO(customer);
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findOptionalBy (id);
        if (customerOptional.isPresent()) {
            return customerMapper.toCustomerDTO(customerOptional.get());
        } else {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No customer data found for id: ", id));
        }
    }
}
