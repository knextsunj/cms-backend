package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.Country;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.CustomerStatusMapper;
import com.github.knextsunj.cms.repository.CustomerStatusRepository;
import com.github.knextsunj.cms.service.CustomerStatusService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsExceptionUtil;
import com.github.knextsunj.cms.util.CmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {

    @Autowired
    private CustomerStatusMapper customerStatusMapper;

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Autowired
    @Qualifier("customerStatusValidationService")
    private GenericValidationService genericValidationService;


    @Override
    @CacheEvict(cacheNames = "allCustomerStatus",allEntries = true)
    public boolean saveCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        if (!CmsUtil.isNull(customerStatusDTO) && !CmsUtil.isNull(customerStatusDTO.name())) {
            if (customerStatusRepository.count()!=0L && !genericValidationService.deDup(customerStatusDTO.name())) {
                throw new ValidationFailureException("Customer Status with given name: " + customerStatusDTO.name() + " already registered");
            }

            CustomerStatus savedCustomerStatus = customerStatusRepository.save(customerStatusMapper.setDates(customerStatusMapper.fromCustomerStatusDTO(customerStatusDTO)));
            if (!CmsUtil.isNull(savedCustomerStatus)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @CacheEvict(cacheNames = "allCustomerStatus",allEntries = true)
    public boolean updateCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        if (Optional.ofNullable(customerStatusDTO).isPresent() && CmsUtil.isNumPresent(customerStatusDTO.id())) {
            Optional<CustomerStatus> customerStatusOptional = customerStatusRepository.findById(customerStatusDTO.id());
            if (customerStatusOptional.isPresent()) {
                CustomerStatus customerStatus = customerStatusOptional.get();
                if (!CmsUtil.isNull(customerStatusDTO.deleted()) && customerStatusDTO.deleted().equals("Y")) {
                    customerStatus.setDeleted("Y");
                }
                if(!CmsUtil.isNull(customerStatusDTO.name())) {
                    customerStatus.setName(customerStatusDTO.name());
                }
                CustomerStatus updatedCustomerStatus = customerStatusRepository.save(customerStatusMapper.setDates(customerStatus));
                if (updatedCustomerStatus != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "allCustomerStatus")
    public List<CustomerStatusDTO> findAllCustomerStatus() {

        Function<CustomerStatus,CustomerStatusDTO> function = (customerStatus)->{
            return customerStatusMapper.toCustomerStatusDTO(customerStatus);
        };
       return customerStatusRepository.findAllCustomerStatusByDeleted("N").stream().map(function).collect(Collectors.toList());
    }

    @Override
    public CustomerStatusDTO fetchCustomerStatusById(long id) {
        Optional<CustomerStatus> optionalCustomerStatus = customerStatusRepository.findById(id);
        if(optionalCustomerStatus.isEmpty()) {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No customer status data found for id: ", id));
        }
        return customerStatusMapper.toCustomerStatusDTO(optionalCustomerStatus.get());
    }
}
