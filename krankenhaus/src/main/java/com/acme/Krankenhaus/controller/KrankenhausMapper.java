package com.acme.Krankenhaus.controller;

import com.acme.Krankenhaus.entity.Krankenhaus;
import com.acme.Krankenhaus.entity.Lageort;
import com.acme.Krankenhaus.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;


@Mapper(nullValueIterableMappingStrategy = RETURN_DEFAULT, componentModel = "spring")
interface KrankenhausMapper {

    @Mapping(target = "id", ignore = true)
    Krankenhaus toKrankenhaus(KrankenhausDTO dto);

    Lageort toLageort(LageortDTO dto);

    Patient toPatient(PatientDTO dto);
}
