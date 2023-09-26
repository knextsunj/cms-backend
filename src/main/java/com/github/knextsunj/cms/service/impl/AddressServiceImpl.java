package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.builder.AddressDetailsTOBuilder;
import com.github.knextsunj.cms.domain.*;
import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.exception.BusinessException;
import com.github.knextsunj.cms.internalto.AddressDetailsTO;
import com.github.knextsunj.cms.mapper.AddressMapper;
import com.github.knextsunj.cms.repository.*;
import com.github.knextsunj.cms.service.AddressService;
import com.github.knextsunj.cms.util.CmsUtil;
import com.github.knextsunj.cms.util.MapperUtil;
import com.github.knextsunj.cms.util.ValidationUtil;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Local(AddressService.class)
public class AddressServiceImpl implements AddressService {

    @Inject
    private AddressRepository addressRepository;

    @Inject
    private CountryRepository countryRepository;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private StateRepository stateRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private AddressTypeRepository addressTypeRepository;

    @Inject
    private AddressMapper addressMapper;

    private ValidationUtil validationUtil;

    @Override
    public boolean saveAddress(AddressDTO addressDTO) {

        if (validationUtil.checkAddressParams(addressDTO)) {

            processSave(null, addressDTO, false);
        }

        return false;
    }

    @Override
    public boolean updateAddress(AddressDTO addressDTO) {

        var status = false;
        if (!CmsUtil.isNull(addressDTO) && !CmsUtil.isNull(addressDTO.getId())) {

            Optional<Address> optionalAddress = addressRepository.findById(addressDTO.getId());
            if (optionalAddress.isPresent()) {
                Address address = optionalAddress.get();
                if (!CmsUtil.isNull(addressDTO.getDeleted()) && addressDTO.getDeleted().equals("Y")) {
                    address.setDeleted("Y");
                    Address updatedAddress = addressRepository.save(address);
                    if (null != updatedAddress) {
                        status = true;
                    }
                } else {
                    status = processSave(address, addressDTO, true);
                }
            }
        }
        return status;
    }

    @Override
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

    private boolean processSave(Address address, AddressDTO addressDTO, boolean toUpdate) {

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
            status = MapperUtil.save(toUpdate, addressDetailsTO, addressRepository);
        } else {
            throw new BusinessException(("Unable to save address,mandatory fields not present"));
        }
        return false;
    }
}
