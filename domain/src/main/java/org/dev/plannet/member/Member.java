package org.dev.plannet.member;


import jakarta.persistence.*;
import lombok.*;
import org.dev.plannet.BaseEntity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String phone;

    public Member changePassword(String newPassword) {
        return Member.builder()
                .id(this.id)
                .email(this.email)
                .password(newPassword)
                .nickname(this.nickname)
                .phone(this.phone)
                .build();
    }

}
