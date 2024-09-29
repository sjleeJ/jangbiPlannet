package org.dev.plannet;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

//Mock이라는 단어를 사전에서 찾아보면 '테스트를 위해 만든 모형'을 의미
//테스트를 위해 실제 객체와 비슷한 모의 객체를 만드는 것을 모킹(Mocking)   모킹한 객체를 메모리에서 얻어내는 과정을 목업(Mock-up)
// 서블릿 컨테이너를 모킹하기 위해 @AutoConfigureMockMvc 사용

//@SpringBootTest 스프링에서  통합 테스트를 위한 환경을 준비해주는 어노테이션
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@IntegrationTest
@SpringBootTest
@AutoConfigureMockMvc
public @interface IntegrationMockMvcTest {
}
