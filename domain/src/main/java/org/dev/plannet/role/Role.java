package org.dev.plannet.role;

import jakarta.persistence.*;
import lombok.*;
import org.dev.plannet.BaseEntity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
