package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.RemoteApiJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteApiJobRepository extends JpaRepository<RemoteApiJob, Long> {
}
