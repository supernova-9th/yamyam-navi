package com.yamyamnavi.storage.user;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserConverter userConverter;

    @Override
    public User save(User user) {
        UserEntity entity = userConverter.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return userConverter.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userConverter::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}