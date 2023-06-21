package com.consumerApp.repository;

import com.consumerApp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Optional<Session> getBySessionId(String sessionId);

}
