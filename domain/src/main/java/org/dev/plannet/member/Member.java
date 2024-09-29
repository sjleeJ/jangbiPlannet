package org.dev.plannet.member;

import java.util.ArrayList;
import java.util.List;

import org.dev.plannet.BaseEntity;
import org.dev.plannet.member.role.MemberRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

	@OneToMany(mappedBy = "member")
	private List<MemberRole> memberRoleList = new ArrayList<>();

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
