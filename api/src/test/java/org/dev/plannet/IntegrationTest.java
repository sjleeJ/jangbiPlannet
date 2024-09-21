package org.dev.plannet;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.*;

// Annotion 에 대해 불필요한 내용이 포함되거나 필요한 내용이 있는 경우 Cusotom 을 할 경우가 존재
// @Target @Retention
// @Target  Java compiler 가 annotation 이 어디에 적용될지 결정하기 위해 사용(어노테이션이 적용될 위치를 지정)
// @Retetion 은 Annotation 이 실제로 적용되고 유지되는 범위
// @Documented 해당 어노테이션을 javadoc에 포함
// @Tag 테스트 클래스, 메소드에 테스트 구분을 태깅하기 위해 사용
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("integration")
public @interface IntegrationTest {
}
