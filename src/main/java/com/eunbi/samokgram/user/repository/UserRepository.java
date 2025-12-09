package com.eunbi.samokgram.user.repository;


import com.eunbi.samokgram.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insertUser (
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("loginId") String loginId,
            @Param("password") String password
    );

    public int countByLoginId(@Param("loginId") String loginId);


    public User selectUser(
            @Param("loginId") String loginId,
            @Param("password") String password
    );


}
