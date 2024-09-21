package org.dev.plannet.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
    Member findByEmail(String email);
}
