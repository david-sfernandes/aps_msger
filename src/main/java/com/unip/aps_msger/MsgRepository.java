package com.unip.aps_msger;

import com.unip.aps_msger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<Message, Long> {
//    @Modifying
//    @Query(value = "INSERT INTO Message (id, msg) VALUES (:id, :msg)", nativeQuery = true)
//    void insertMsg(@Param("id") Long id, @Param("msg") String msg);
}
