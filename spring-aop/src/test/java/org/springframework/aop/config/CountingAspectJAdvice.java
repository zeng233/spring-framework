
/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.config;
import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.tests.aop.advice.MethodCounter;

/**
 * 
 * 测试类，在spring4.0以上版本没有了
 * 参考地址：
 * http://alvinalexander.com/java/jwarehouse/spring-framework-2.5.3/test/org/springframework/aop/framework/CountingBeforeAdvice.java.shtml
 * 
 * Simple before advice example that we can use for counting checks.
 *
 * @author Rod Johnson
 */
public class CountingAspectJAdvice extends MethodCounter implements MethodBeforeAdvice {

	public void before(Method m, Object[] args, Object target) throws Throwable {
		count(m);
	}

}