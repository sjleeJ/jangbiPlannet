package org.dev.plannet.member.dto.check;

public record MemberCheckResponse(
        Boolean duplicated
) {
    public static MemberCheckResponse of(boolean duplicated) {
        return new MemberCheckResponse(duplicated);
    }
}
