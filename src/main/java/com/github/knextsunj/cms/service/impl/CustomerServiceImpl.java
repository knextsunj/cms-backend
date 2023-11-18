package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.Customer;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.CustomerDTO;
import com.github.knextsunj.cms.event.publisher.CustomerKYCEventPublisher;
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
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    @Qualifier("customerValidationService")
    private GenericValidationService genericValidationService;

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Autowired
    private CustomerKYCEventPublisher customerKYCEventPublisher;

    @Override
    @CacheEvict(cacheNames = "allCustomers",allEntries = true)
    public boolean saveCustomer(CustomerDTO customerDTO,String authHeader) {
        if (!CmsUtil.isNull(customerDTO) && !CmsUtil.isNull(customerDTO.getName())) {
            if (customerRepository.count() != 0L && !genericValidationService.deDup(customerDTO.getName())) {
                throw new ValidationFailureException("Customer with given name: " + customerDTO.getName() + " already registered");
            }
            CustomerStatus customerStatus = customerStatusRepository.findCustomerStatusByName(customerDTO.getCustomerStatusDescr());
            Customer newCustomer = customerMapper.setDates(customerMapper.fromCustomerDTO(customerDTO));
            newCustomer.setCustomerStatus(customerStatus);
            Customer savedCustomer = customerRepository.save(newCustomer);
            if (!CmsUtil.isNull(savedCustomer)) {
                Pair pair = Pair.of(String.valueOf(savedCustomer.getId()),authHeader);
                customerKYCEventPublisher.publishCustomerKYCEvent(pair);
                return true;
            }

        }
        return false;
    }

    @Override
    @CacheEvict(cacheNames = "allCustomers",allEntries = true)
    public boolean updateCustomer(CustomerDTO customerDTO,String authHeader) {
        if (Optional.ofNullable(customerDTO).isPresent() && CmsUtil.isNumPresent(customerDTO.getId())) {
            Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
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
                if (updatedCustomer != null) {
                    Pair pair = Pair.of(String.valueOf(updatedCustomer.getId()),authHeader);
                    customerKYCEventPublisher.publishCustomerKYCEvent(pair);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "allCustomers")
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAllByActiveStatusAndDeleted("ACTIVE", "N").stream().map(customer -> {
            return customerMapper.toCustomerDTO(customer);
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerMapper.toCustomerDTO(customerOptional.get());
        } else {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No customer data found for id: ", id));
        }
    }
}
