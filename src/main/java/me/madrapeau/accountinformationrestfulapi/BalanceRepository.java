package me.madrapeau.accountinformationrestfulapi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "balances", path = "balances")
public interface BalanceRepository extends PagingAndSortingRepository<Balance, Long> {
}