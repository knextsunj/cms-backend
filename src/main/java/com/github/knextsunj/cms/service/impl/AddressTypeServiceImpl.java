package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.domain.Address;
import com.github.knextsunj.cms.domain.AddressType;
import com.github.knextsunj.cms.domain.CustomerStatus;
import com.github.knextsunj.cms.dto.AddressTypeDTO;
import com.github.knextsunj.cms.dto.CustomerStatusDTO;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.AddressTypeMapper;
import com.github.knextsunj.cms.repository.AddressTypeRepository;
import com.github.knextsunj.cms.service.AddressTypeService;
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
public class AddressTypeServiceImpl implements AddressTypeService {

    @Autowired
    private AddressTypeRepository addressTypeRepository;

    @Autowired
    private AddressTypeMapper addressTypeMapper;

    @Autowired
    @Qualifier("addressTypeStatusValidationService")
    private GenericValidationService genericValidationService;


    @Override
    @CacheEvict(cacheNames = "allAddressTypes",allEntries=true)
    public boolean saveAddressType(AddressTypeDTO addressTypeDTO) {
        if (!CmsUtil.isNull(addressTypeDTO) && !CmsUtil.isNull(addressTypeDTO.name())) {
            if (addressTypeRepository.count()!=0L && !genericValidationService.deDup(addressTypeDTO.name())) {
                throw new ValidationFailureException("Address Type with given name: " + addressTypeDTO.name() + " already registered");
            }

            AddressType savedAddressType = addressTypeRepository.save(addressTypeMapper.setDates(addressTypeMapper.fromAddressTypeDTO(addressTypeDTO)));
            if (!CmsUtil.isNull(savedAddressType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @CacheEvict(cacheNames = "allAddressTypes",allEntries=true)
    public boolean updateAddressType(AddressTypeDTO addressTypeDTO) {
        if (Optional.ofNullable(addressTypeDTO).isPresent() && CmsUtil.isNumPresent(addressTypeDTO.id())) {
            Optional<AddressType> addressTypeOptional = addressTypeRepository.findById(addressTypeDTO.id());
            if (addressTypeOptional.isPresent()) {
                AddressType addressType = addressTypeOptional.get();
                if (!CmsUtil.isNull(addressTypeDTO.deleted()) && addressTypeDTO.deleted().equals("Y")) {
                    addressType.setDeleted("Y");
                }
                if(!CmsUtil.isNull(addressTypeDTO.name())) {
                    addressType.setName(addressTypeDTO.name());
                }
                AddressType updatedAddressType = addressTypeRepository.save(addressTypeMapper.setDates(addressType));
                if (updatedAddressType != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames ="allAddressTypes")
    public List<AddressTypeDTO> findAllAddressType() {
        Function<AddressType,AddressTypeDTO> function = (addressType)->{
            return addressTypeMapper.toAddressTypeDTO(addressType);
        };
        return addressTypeRepository.findAllAddressTypesByDeleted("N").stream().map(function).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "addressTypeById",key="#id")
    public AddressTypeDTO fetchAddressTypeById(long id) {
        Optional<AddressType> optionalAddressType = addressTypeRepository.findById(id);
        if(optionalAddressType.isEmpty()) {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No address type data found for id: ", id));
        }
        return addressTypeMapper.toAddressTypeDTO(optionalAddressType.get());
    }
}
