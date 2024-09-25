package org.dev.plannet.member.role;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.dev.plannet.BaseEntity;
import org.dev.plannet.member.Member;
import org.dev.plannet.role.Role;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member_role")
public class MemberRole extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
