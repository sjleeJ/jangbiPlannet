package org.dev.plannet.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Boolean existsByNickname(String nickname);

	Boolean existsByEmail(String email);

	Optional<Member> findByEmail(String email);
}
