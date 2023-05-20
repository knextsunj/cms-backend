package com.github.knextsunj.cms.builder;

import com.github.knextsunj.cms.domain.*;
import com.github.knextsunj.cms.dto.AddressDTO;
import com.github.knextsunj.cms.internalto.AddressDetailsTO;
import com.google.auto.value.AutoBuilder;

@AutoBuilder(ofClass = AddressDetailsTO.class)
public abstract class AddressDetailsTOBuilder {

    public static AddressDetailsTOBuilder addressDetailsTOBuilder() {
        return new AutoBuilder_AddressDetailsTOBuilder();
    }

    public abstract AddressDetailsTOBuilder setCustomer(Customer customer);

    public abstract AddressDetailsTOBuilder setCity(City city);

    public abstract AddressDetailsTOBuilder setState(State state);

    public abstract AddressDetailsTOBuilder setCountry(Country country);

    public abstract AddressDetailsTOBuilder setAddressType(AddressType addressType);

    public abstract AddressDetailsTOBuilder setAddress(Address address);

    public abstract AddressDetailsTOBuilder setToUpdate(Boolean toUpdate);

    public abstract AddressDetailsTOBuilder setAddressDTO(AddressDTO addressDTO);

    public abstract AddressDetailsTO build();

}
