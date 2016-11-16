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

package mytest.bean.advancedBean;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class AdvancedBeanTest {
	private ApplicationContext context;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("mytest/AdvancedBeanTest.xml");
	}
	
	@Test
	public void testInit() {
		Apple apple = context.getBean("apple", Apple.class);
		System.out.println(apple);
	}
	
	/**
	 * 参考ClassPathXmlApplicationContextTests.testAliasThatOverridesParent()
	 */
	@Test
	public void testInheritBean() {
		
	}
	
	/**
	 * 子类容器与父类容器的bean存储的缓存是隔离的，id可以一样，
	 * 在WebApplicationContext容器事务失效问题，就是web容器扫描的包，已经把service、dao加载到子容器中了，
	 * 但web容器中并没有配置事务（父级容器有事务配置），所以事务失效了
	 */
	@Test
	public void testInheritContainer() {
		ConfigurableApplicationContext childContext = new ClassPathXmlApplicationContext("mytest/AdvancedBeanTest-child.xml");
		childContext.setParent(context);
		childContext.getBean("apple");
	}
}
