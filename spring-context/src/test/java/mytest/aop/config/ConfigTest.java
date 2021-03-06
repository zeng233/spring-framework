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

package mytest.aop.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 参考：AopNamespaceHandlerEventTests、AopNamespaceHandlerTests
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class ConfigTest {
	private static final Resource CONTEXT =  new ClassPathResource("mytest/aop/ConfigTest.xml");
	
	private XmlBeanDefinitionReader reader;

	private DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
	
	@Before
	public void setUp() throws Exception {
		//只解析传入的xml，默认包的handler是不会处理的
		this.reader = new XmlBeanDefinitionReader(this.beanFactory);
		this.reader.loadBeanDefinitions(CONTEXT);
		System.out.println("ConfigTest-before加载完成");
		System.out.println();
	}

	@Test
	public void testAspectConfig() throws Exception {
		MyAspect myAspect = beanFactory.getBean("myAspect", MyAspect.class);
		System.out.println(myAspect);
	}
	
	@Test
	public void testAround() {
//		MyTestBean myTestBean = beanFactory.getBean(MyTestBean.class);
		ApplicationContext context = new ClassPathXmlApplicationContext("mytest/aop/ConfigTest.xml");
		System.out.println("********************************************");
		
		//如果MyTestBean实现了接口，获取的对象是代理对象，不是原对象，这里就会抛异常
		MyTestBean myTestBean = context.getBean(MyTestBean.class);
		MyAspect myAspect = context.getBean(MyAspect.class);
		
		//如何创建代理对象的 TODO（为什么XmlBeanDefinitionReader方式不行呢），
		//真正触发代理是在AbstractApplicationContext的finishBeanFactoryInitialization
//		System.out.println("ConfigTest-是否是代理类:" + AopUtils.isAopProxy(myTestBean));
		
		myTestBean.print();
		
	}
}
