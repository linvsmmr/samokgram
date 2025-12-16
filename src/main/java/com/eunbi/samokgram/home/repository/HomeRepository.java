package com.eunbi.samokgram.home.repository;

import com.eunbi.samokgram.home.domain.Contents;
import com.eunbi.samokgram.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Contents, Long> {
    public List<Contents> findByUserId(long userId, Sort sort);

//    public int deleteContents(@Param("id") int id);

    public int countLikes(@Param("id") int id);

}
