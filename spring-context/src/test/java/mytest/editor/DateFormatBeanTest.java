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

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class DateFormatBeanTest {
	private static final Logger mylog = Logger.getLogger(DateFormatBeanTest.class);
	
	private static ApplicationContext context;
	
	@BeforeClass
	public static void before() {
		context = new ClassPathXmlApplicationContext("mytest/DateFormatBeanTest.xml");
	}
	
	@Test
	public void testSingletonBean() {
//		DateBean dateBean = context.getBean("dateBean", DateBean.class);
		FormattingConversionServiceFactoryBean conversionServiceBean = context.getBean(FormattingConversionServiceFactoryBean.class);
		FormattingConversionService conversionService = conversionServiceBean.getObject();
		DateBean bean = new DateBean();
		mylog.debug(conversionService.convert(bean.getPattern(), String.class));
	}
}
