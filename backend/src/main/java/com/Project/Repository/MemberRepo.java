package com.Project.Repository;

import com.Project.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailIgnoreCase(String email);
}
