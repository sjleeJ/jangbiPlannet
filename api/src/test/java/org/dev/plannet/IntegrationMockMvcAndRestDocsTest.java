package org.dev.plannet;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@IntegrationTest
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // RestDocs 문서생성을 위해 필요함
public @interface IntegrationMockMvcAndRestDocsTest {
}
