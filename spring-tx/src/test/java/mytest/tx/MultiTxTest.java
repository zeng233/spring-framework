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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.tests.transaction.CallCountingTransactionManager;

/**
 * 多线程情况下，事务测试
 * 
 * @author Administrator
 * @since 4.2.1
 */
public class MultiTxTest {

	private ApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("mytest/tx/MultiTxTest.xml");
		System.out.println("===========ClassPathXmlApplicationContext初始化完成==========");
		System.out.println();
	}
	
	@Test
	public void testMultiThreadTx() throws Exception {
		ITxBean myBean = (ITxBean) context.getBean("myTxBean");
		CallCountingTransactionManager ptm = (CallCountingTransactionManager) context.getBean("transactionManager");
				
		ExecutorService executor = Executors.newFixedThreadPool(100);
		CountDownLatch latch = new CountDownLatch(100000);
		
		for(int i = 0; i < 100000; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					myBean.getFoo();
					latch.countDown();
				}
			});
		}
		
		latch.await();
		System.out.println(ptm.commits);
	}
}
