package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.builder.AddressDetailsTOBuilder;
import com.github.knextsunj.cms.domain.*;
import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.event.publisher.AddressKYCEventPublisher;
import com.github.knextsunj.cms.exception.BusinessException;
import com.github.knextsunj.cms.internalto.AddressDetailsTO;
import com.github.knextsunj.cms.mapper.AddressMapper;
import com.github.knextsunj.cms.repository.*;
import com.github.knextsunj.cms.service.AddressService;
import com.github.knextsunj.cms.service.messaging.JmsProducer;
import com.github.knextsunj.cms.util.CmsUtil;
import com.github.knextsunj.cms.util.MapperUtil;
import com.github.knextsunj.cms.util.ValidationUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressTypeRepository addressTypeRepository;

    @Autowired
    private AddressMapper addressMapper;

    private ValidationUtil validationUtil;

    @Autowired
    private AddressKYCEventPublisher addressKYCEventPublisher;

    @Override
    @CacheEvict(cacheNames = "allAddresses",allEntries = true)
    public boolean saveAddress(AddressDTO addressDTO,String authHeader) {

        if (validationUtil.checkAddressParams(addressDTO)) {

            processSave(null, addressDTO, false, authHeader);
        }

        return false;
    }

    @Override
    @CacheEvict(cacheNames = "allAddresses",key="#addressDTO.customerId")
    public boolean updateAddress(AddressDTO addressDTO,String authHeader) {

        var status = false;
        if (!CmsUtil.isNull(addressDTO) && !CmsUtil.isNull(addressDTO.getId())) {

            Optional<Address> optionalAddress = addressRepository.findById(addressDTO.getId());
            if (optionalAddress.isPresent()) {
                Address address = optionalAddress.get();
                if (!CmsUtil.isNull(addressDTO.getDeleted()) && addressDTO.getDeleted().equals("Y")) {
                    address.setDeleted("Y");
                    Address updatedAddress = addressRepository.save(address);
                    if (null != updatedAddress) {
                        ImmutablePair pair = ImmutablePair.of(String.valueOf(updatedAddress.getId()),authHeader);
                        addressKYCEventPublisher.publishAdressKYCEvent(pair);
                        status = true;
                    }
                } else {
                    status = processSave(address, addressDTO, true,authHeader);
                }
            }
        }
        return status;
    }

    @Override
    @Cacheable(cacheNames ="allAddresses",key="#customerId")
    public List<AddressDTO> fetchAllAddress(Long customerId) {
        if (!CmsUtil.isNull(customerId)) {
            return addressRepository.findAddressByCustomerIdAndDeleted(customerId,"N").stream().map((address) -> {
                return addressMapper.toAddressDTO(address);
            }).collect(Collectors.toList());
        } else {
            throw new BusinessException("Customer id not available to fetch address");
        }
    }

    @PostConstruct
    public void init() {
        this.validationUtil = new ValidationUtil();
    }

    private boolean processSave(Address address, AddressDTO addressDTO, boolean toUpdate,String authHeader) {

        var status = false;

        Optional<AddressType> optionalAddressType = addressTypeRepository.findById(addressDTO.getAddressTypeId());
        Optional<City> optionalCity = cityRepository.findById(addressDTO.getCityId());
        Optional<State> optionalState = stateRepository.findById(addressDTO.getStateId());
        Optional<Country> optionalCountry = countryRepository.findById(addressDTO.getCountryId());
        Optional<Customer> optionalCustomer = customerRepository.findById(addressDTO.getCustomerId());


        if (optionalAddressType.isPresent() && optionalCity.isPresent() && optionalState.isPresent() && optionalCountry.isPresent() && optionalCustomer.isPresent()) {

            AddressDetailsTO addressDetailsTO;
            if (toUpdate) {

                addressDetailsTO = AddressDetailsTOBuilder.addressDetailsTOBuilder()
                        .setCustomer(optionalCustomer.get())
                        .setAddress(address)
                        .setCity(optionalCity.get())
                        .setState(optionalState.get()).setCountry(optionalCountry.get())
                        .setAddressType(optionalAddressType.get())
                        .setAddressDTO(addressDTO)
                        .build();

            } else {
                addressDetailsTO = AddressDetailsTOBuilder.addressDetailsTOBuilder()
                        .setCustomer(optionalCustomer.get())
                        .setAddress(addressMapper.setDates(addressMapper.fromAddressDTO(addressDTO)))
                        .setCity(optionalCity.get())
                        .setState(optionalState.get()).setCountry(optionalCountry.get())
                        .setAddressType(optionalAddressType.get())
                        .build();

            }
            Address savedAddress = MapperUtil.save(toUpdate, addressDetailsTO, addressRepository);
            if(null!=savedAddress) {
                ImmutablePair pair = ImmutablePair.of(String.valueOf(savedAddress.getId()),authHeader);
                addressKYCEventPublisher.publishAdressKYCEvent(pair);
                return true;
            }
        } else {
            throw new BusinessException(("Unable to save address,mandatory fields not present"));
        }
        return false;
    }
}
