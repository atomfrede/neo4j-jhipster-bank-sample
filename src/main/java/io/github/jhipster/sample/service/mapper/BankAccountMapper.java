package io.github.jhipster.sample.service.mapper;


import io.github.jhipster.sample.domain.*;
import io.github.jhipster.sample.service.dto.BankAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankAccount} and its DTO {@link BankAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BankAccountMapper extends EntityMapper<BankAccountDTO, BankAccount> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    BankAccountDTO toDto(BankAccount bankAccount);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "operations", ignore = true)
    @Mapping(target = "removeOperation", ignore = true)
    BankAccount toEntity(BankAccountDTO bankAccountDTO);
}
