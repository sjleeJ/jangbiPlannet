package org.dev.plannet.member;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.dev.plannet.BaseEntity;

@Getter
@SuperBuilder
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
                .createdAt(this.getCreatedAt())  // BaseEntity의 createdAt 사용
                .build();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt='" + getCreatedAt() + '\'' +
                '}';
    }
}
