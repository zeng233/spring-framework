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
import org.springframework.beans.factory.parsing.ComponentDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.tests.beans.CollectingReaderEventListener;

/**
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class ConfigTest {
	private static final Resource CONTEXT =  new ClassPathResource("mytest/aop/ConfigTest.xml");
	
	private CollectingReaderEventListener eventListener = new CollectingReaderEventListener();
	
	private XmlBeanDefinitionReader reader;

	private DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
	
	@Before
	public void setUp() throws Exception {
		this.reader = new XmlBeanDefinitionReader(this.beanFactory);
		this.reader.setEventListener(this.eventListener);
	}

	@Test
	public void testAspectEvent() throws Exception {
		this.reader.loadBeanDefinitions(CONTEXT);
		ComponentDefinition[] componentDefinitions = this.eventListener.getComponentDefinitions();
//		assertEquals("Incorrect number of events fired", 5, componentDefinitions.length);
//
//		assertTrue("No holder with nested components", componentDefinitions[0] instanceof CompositeComponentDefinition);
//		CompositeComponentDefinition compositeDef = (CompositeComponentDefinition) componentDefinitions[0];
//		assertEquals("aop:config", compositeDef.getName());
	}
}
