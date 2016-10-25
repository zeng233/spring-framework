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

package mytest.bean.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import static org.junit.Assert.*;

/**
 * 
 * @author Administrator
 * @since 4.2.1
 */
public class MyBeanWrapperImplTest {

	protected BeanWrapperImpl createAccessor(Object target) {
		return new BeanWrapperImpl(target);
	}

	@Test
	public void testSimpleAttrConvert() {
		MyBean target = new MyBean();
		BeanWrapper accessor = createAccessor(target);
		// 如果原始值与属性类型可以匹配，就不用转换器，BeanWrapperImpl的默认转换器也不会执行
		// accessor.setPropertyValue("age", 23);
		accessor.setPropertyValue("age", "23");
		assertTrue("Set age to bean", target.getAge() == 23);
	}

	@Test
	public void testStringAttr() {
		MyBean target = new MyBean();
		BeanWrapper accessor = createAccessor(target);
		accessor.setPropertyValue("name", "hello");
		assertTrue("Set name to bean", target.getName().equals("hello"));
	}

	@Test
	public void testListConvert() {
		MyBean target = new MyBean();
		target.setAges(new ArrayList<Integer>());
		BeanWrapper accessor = createAccessor(target);
		accessor.setPropertyValue("ages[0]", "1");
		assertTrue(target.getAges().get(0) == 1);
	}

	@Test
	public void testDateConvert() {
		MyBean target = new MyBean();
		BeanWrapper accessor = createAccessor(target);
		accessor.setPropertyValue("createDate", "2016");
		System.out.println(target.getCreateDate());
	}
}

class MyBean {

	String name;

	int age;

	List<Integer> ages;

	Date createDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Integer> getAges() {
		return ages;
	}

	public void setAges(List<Integer> ages) {
		this.ages = ages;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
