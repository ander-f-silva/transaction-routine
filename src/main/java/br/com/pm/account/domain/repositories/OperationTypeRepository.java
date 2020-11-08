package br.com.pm.account.domain.repositories;

import br.com.pm.account.infrastructure.jpa.entities.OperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Long> {
}
