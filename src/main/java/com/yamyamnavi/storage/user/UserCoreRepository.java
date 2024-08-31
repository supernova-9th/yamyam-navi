package com.yamyamnavi.storage.user;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserRepository;
import com.yamyamnavi.support.error.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void update(User user) {
        UserEntity entity = userJpaRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);
        userConverter.updateEntityFromDomain(user, entity);
    }
}