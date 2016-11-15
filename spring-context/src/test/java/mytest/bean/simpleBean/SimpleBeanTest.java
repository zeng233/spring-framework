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

package mytest.bean.simpleBean;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @since 4.2.1
 */
public class SimpleBeanTest {
	private static final Logger log = Logger.getLogger(SimpleBeanTest.class);
	
	private static ApplicationContext context;
	
	@BeforeClass
	public static void before() {
		context = new ClassPathXmlApplicationContext("mytest/SimpleBeanTest.xml");
	}
	
	@Test
	public void testSingletonBean() {
		SimpleBean simpleBean = context.getBean("simple", SimpleBean.class);
		Assert.assertNotNull(simpleBean);
	}
	
	@Test
	public void testPrototypeBean() {
		PrototypeBean prototypeBean = context.getBean("prototypeBean", PrototypeBean.class);
		Assert.assertNotNull(prototypeBean);
	}
	
	@Test
	public void testPrototypeAttr() {
		SimpleBean simpleBean = context.getBean(SimpleBean.class);
		//依赖中的bean是prototype但是是同一个对象？
		log.debug(simpleBean.getPrototypeBean() == simpleBean.getPrototypeBean());
		PrototypeBean p1 = context.getBean("prototypeBean", PrototypeBean.class);
		int oldIndex = p1.getIndex();
		p1.setIndex(2);
		System.out.println();
		PrototypeBean p2 = context.getBean("prototypeBean", PrototypeBean.class);
		log.debug(p1 == p2);
		//原型重新设置后，与新的值对比
		log.debug(oldIndex == p2.getIndex());
	}
	
	/**
	 * 参考6.4章节，Dependency resolution process
	 * set方式注入可以，构造函数不能（源码跟踪下 TODO）
	 */
	@Test
	public void testCircularBean() {
		context.getBean("circularBean");
	}
}
