package com.github.knextsunj.cms.builder;

import com.github.knextsunj.cms.responsedto.CountryResponseDTO;
import com.google.auto.value.AutoBuilder;

@AutoBuilder(ofClass = CountryResponseDTO.class)
public abstract class CountryResponseDTOBuilder {

    public static CountryResponseDTOBuilder countryResponseDTOBuilder() {
        return new AutoBuilder_CountryResponseDTOBuilder();
    }

    public abstract CountryResponseDTOBuilder setSerialNo(Long serialNo);

    public abstract CountryResponseDTOBuilder setId(Long id);

    public abstract CountryResponseDTOBuilder setName(String name);

    public abstract  CountryResponseDTOBuilder setDeleted(String deleted);

    public abstract CountryResponseDTO build();
}
