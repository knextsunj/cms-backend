package com.github.knextsunj.cms.service.impl;

import com.github.knextsunj.cms.annotation.AddressTypeValidationService;
import com.github.knextsunj.cms.domain.AddressType;
import com.github.knextsunj.cms.dto.AddressTypeDTO;
import com.github.knextsunj.cms.exception.DataNotFoundException;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.mapper.AddressTypeMapper;
import com.github.knextsunj.cms.repository.AddressTypeRepository;
import com.github.knextsunj.cms.service.AddressTypeService;
import com.github.knextsunj.cms.service.validation.GenericValidationService;
import com.github.knextsunj.cms.util.CmsExceptionUtil;
import com.github.knextsunj.cms.util.CmsUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Stateless
@Local(AddressTypeService.class)
public class AddressTypeServiceImpl implements AddressTypeService {

    @Inject
    private AddressTypeRepository addressTypeRepository;

    @Inject
    private AddressTypeMapper addressTypeMapper;

    @Inject
    @AddressTypeValidationService
    private GenericValidationService genericValidationService;


    @Override
    public boolean saveAddressType(AddressTypeDTO addressTypeDTO) {
        if (!CmsUtil.isNull(addressTypeDTO) && !CmsUtil.isNull(addressTypeDTO.getName())) {
            if (addressTypeRepository.count()!=0L && !genericValidationService.deDup(addressTypeDTO.getName())) {
                throw new ValidationFailureException("Address Type with given name: " + addressTypeDTO.getName() + " already registered");
            }

            AddressType savedAddressType = addressTypeRepository.save(addressTypeMapper.setDates(addressTypeMapper.fromAddressTypeDTO(addressTypeDTO)));
            if (!CmsUtil.isNull(savedAddressType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateAddressType(AddressTypeDTO addressTypeDTO) {
        if (Optional.ofNullable(addressTypeDTO).isPresent() && CmsUtil.isNumPresent(addressTypeDTO.getId())) {
            Optional<AddressType> addressTypeOptional = addressTypeRepository.findById(addressTypeDTO.getId());
            if (addressTypeOptional.isPresent()) {
                AddressType addressType = addressTypeOptional.get();
                if (!CmsUtil.isNull(addressTypeDTO.getDeleted()) && addressTypeDTO.getDeleted().equals("Y")) {
                    addressType.setDeleted("Y");
                }
                if(!CmsUtil.isNull(addressTypeDTO.getName())) {
                    addressType.setName(addressTypeDTO.getName());
                }
                AddressType updatedAddressType = addressTypeRepository.save(addressTypeMapper.setDates(addressType));
                if (updatedAddressType != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<AddressTypeDTO> findAllAddressType() {
        Function<AddressType,AddressTypeDTO> function = (addressType)->{
            return addressTypeMapper.toAddressTypeDTO(addressType);
        };
        return addressTypeRepository.findAllAddressTypesByDeleted("N").stream().map(function).collect(Collectors.toList());
    }

    @Override
    public AddressTypeDTO fetchAddressTypeById(long id) {
        Optional<AddressType> optionalAddressType = addressTypeRepository.findById(id);
        if(optionalAddressType.isEmpty()) {
            throw new DataNotFoundException(CmsExceptionUtil.composeExceptionMessage("No address type data found for id: ", id));
        }
        return addressTypeMapper.toAddressTypeDTO(optionalAddressType.get());
    }
}
