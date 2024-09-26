package org.dev.plannet.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
