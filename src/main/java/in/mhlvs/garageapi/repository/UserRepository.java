package in.mhlvs.garageapi.repository;

import in.mhlvs.garageapi.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends GenericRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
