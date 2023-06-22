package com.consumerApp.repository;

import com.consumerApp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Optional<Session> getBySessionId(String sessionId);
    Optional<List<Session>> getByMachineId(String machineId);
    Optional<Session> getByEndDate(Date endDate);

}
