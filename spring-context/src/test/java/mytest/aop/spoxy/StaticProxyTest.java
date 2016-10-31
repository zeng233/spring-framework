/*
 * Copyright 2002-2016 the original author or authors.
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

package mytest.aop.spoxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 参考：Proxying mechanisms
 * 
 * @author Administrator
 * @since 4.2.1
 */
public class StaticProxyTest {
	
	@Test
	public void testSimple() {
		ProxyFactory factory = new ProxyFactory(new SimplePojo());
		factory.setInterfaces(Pojo.class);
		factory.addAdvice(new SimpleMethodBeforeAdvice());
		//Spring针对内嵌方法中，使用this调用目标函数，实际没有走代理对象，使用的本地对象
		//要使内部嵌套调用函数使用代理，这里要设置true，放到ThreadLocal中去
		factory.setExposeProxy(true);
		
		Pojo pojo = (Pojo) factory.getProxy();
		pojo.foo();
	}
}
