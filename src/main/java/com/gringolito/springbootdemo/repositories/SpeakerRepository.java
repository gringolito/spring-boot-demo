package com.gringolito.springbootdemo.repositories;

import com.gringolito.springbootdemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
