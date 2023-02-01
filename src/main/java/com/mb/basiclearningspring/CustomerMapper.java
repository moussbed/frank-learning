package com.mb.basiclearningspring;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "customer.createdDate", target = "createdAt")
    @Mapping(source = "customer.modifiedDate", target = "modifiedAt")
    CustomerResponse customerToCustomerResponse(Customer customer);

    Customer  customerRequestToCustomer(CustomerRequest  request);
}
