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

package mytest.tx;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.tests.transaction.CallCountingTransactionManager;

/**
 * 
 * @author zenghua233
 * @since 4.2.1
 */
public class AnnotationTxAdviceTest {
private ApplicationContext context;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("mytest/tx/AnnotationTxAdviceTest.xml");
		System.out.println("===========ClassPathXmlApplicationContext初始化完成==========");
		System.out.println();
	}
	
	/**
	 * 同事包含AspectJAwareAdvisorAutoProxyCreator、InfrastructureAdvisorAutoProxyCreator（事务）这两个创建代理对象只有前者有效
	 * TODO
	 */
	@Test
	public void testGet() {
		//获取到的是JDK的代理对象
		ITxBean myBean = (ITxBean) context.getBean("annoTxBean");
		CallCountingTransactionManager ptm = (CallCountingTransactionManager) context.getBean("txManager");
		System.out.println(ptm.begun);
		//代理对象执行方法，调用JdkDynamicAopProxy.invoke()方法（实现了InvocationHandler接口）
		//代理对象执行时，DefaultAdvisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice使用ClassFilter过滤表达式
		//AspectJExpressionPointcut也是ClassFilter的一个子类
		myBean.getFoo();
		System.out.println("begun：" + ptm.begun);
		System.out.println("commits：" + ptm.commits);
	}
}
