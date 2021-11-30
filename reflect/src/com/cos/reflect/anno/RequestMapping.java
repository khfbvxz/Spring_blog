package com.cos.reflect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//동작하는 시점 결정할 수 있음 
// 컴파일 시점 런타임 시점 등 
//2번째 Target을 정해야함
// 필트 Type 메소드 등 
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value(); // 추상적으로 그러면 Controller쪽 에러 
}
