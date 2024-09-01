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

    /**
     * 사용자를 저장합니다.
     *
     * @param user 저장할 사용자 객체
     * @return 저장된 사용자 객체
     */
    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = userConverter.convertToEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return userConverter.convertToDomain(savedEntity);
    }

    /**
     * 이메일로 사용자를 찾습니다.
     *
     * @param email 찾을 사용자의 이메일
     * @return 찾은 사용자 객체 (Optional)
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userConverter::convertToDomain);
    }

    /**
     * 이메일이 존재하는지 확인합니다.
     *
     * @param email 확인할 이메일
     * @return 이메일 존재 여부
     */
    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param user 업데이트할 사용자 정보
     */
    @Override
    @Transactional
    public void update(User user) {
        UserEntity entity = userJpaRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);
        userConverter.updateEntityFromDomain(user, entity);
    }
}