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

package mytest.editor;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class CustomerEditorBeanTest {
	private static final Logger mylog = Logger.getLogger(CustomerEditorBeanTest.class);
	
	private static ApplicationContext context;
	
	@BeforeClass
	public static void before() {
		context = new ClassPathXmlApplicationContext("mytest/CustomerEditorBeanTest.xml");
	}
	
	/**
	 * 直接用Map对应editor，参考mapCustomerEditorConfigurer
	 */
	@Test
	public void testSingletonBean() {
		MyBean myBean = context.getBean("myBean", MyBean.class);
		mylog.debug(myBean.getFoo());
	}
}
