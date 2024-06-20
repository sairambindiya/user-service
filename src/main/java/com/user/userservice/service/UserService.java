package com.user.userservice.service;

import com.github.javafaker.Faker;
import com.user.userservice.entity.Address;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.repository.UserRepository;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    Faker faker=new Faker();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private UserRepository userRepository;

    public void createMillionUsers(){
        logger.info("Service start time {}", LocalDateTime.now());
        for(int i=0;i<1000000;i++){
            UserEntity user=new UserEntity();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            List<Address> addressList=new ArrayList<>();
            for(int j=0;j<7;j++){
                Address address=new Address();
                address.setAddress1(String.valueOf(faker.address().fullAddress()));
                address.setHouseNumber(faker.address().buildingNumber());
                address.setCity(faker.address().city());
                address.setState(faker.address().state());
                address.setPostalCode(faker.address().zipCode());
                addressList.add(address);
            }
            user.setAddressList(addressList);
            userRepository.save(user);
        }
        logger.info("Service end time {}", LocalDateTime.now());

    }

    public UserEntity createUser(UserEntity userEntity){
        UserEntity user=new UserEntity();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setUserId(userEntity.getUserId());
       return userRepository.save(userEntity);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public Optional<UserEntity> getUser(Long userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId).orElseThrow());
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User record "+userId +" deleted";
    }

    public UserEntity updateUser(Long userId, Optional<UserEntity> userEntity) {
        Optional<UserEntity> userEntity1= getUser(userId);
        if(userEntity1.isPresent())
        {
            UserEntity extractUserEntity=userEntity1.get();
            extractUserEntity.setFirstName(userEntity.get().getFirstName());
            extractUserEntity.setLastName(userEntity.get().getLastName());
            extractUserEntity.setUserId(userEntity.get().getUserId());
            return userRepository.save(extractUserEntity);
        }
    else{
     throw new RuntimeException("User Not found");
        }

    }

    public UserEntity addAddressToUser(Long userId, Address address) {
        Optional<UserEntity> user=userRepository.findByUserId(userId);
        if(user.isPresent()){
            UserEntity userEntity=user.get();
            if(userEntity.getAddressList().size()<7){
                userEntity.setAddressList((List<Address>) address);
            }
            else{
                throw new RuntimeException("User already has 7 addresses. Cannot add more.");
            }
            return userRepository.save(userEntity);
        }
        else{
            throw new RuntimeException("User not found");
        }
    }
}
