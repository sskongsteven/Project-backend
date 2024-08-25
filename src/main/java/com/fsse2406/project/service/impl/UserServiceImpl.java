package com.fsse2406.project.service.impl;

import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.data.user.entity.UserEntity;
import com.fsse2406.project.repository.UserRepository;
import com.fsse2406.project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
//        Optional<UserEntity> optionalUserEntity = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());
//        if(optionalUserEntity.isEmpty()){
//            UserEntity userEntity = new UserEntity(firebaseUserData);
//            return userRepository.save(userEntity);
//        } else {
//            return optionalUserEntity.get();
//        }
        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).orElseGet(
                () -> userRepository.save(new UserEntity(firebaseUserData))
        );
    }


}
