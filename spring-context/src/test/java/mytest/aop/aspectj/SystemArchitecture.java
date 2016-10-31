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

package mytest.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 参考11.2 AspectJ Support，examples
 * 
 * @author Administrator
 * @since 4.2.1
 */
@Aspect
public class SystemArchitecture {

	/**
	 * A join point is in the web layer if the method is defined in a type in the
	 * com.xyz.someapp.web package or any sub-package under that.
	 */
	@Pointcut("within(com.xyz.someapp.web..*)")
	public void inWebLayer() {
	}

	/**
	 * A join point is in the service layer if the method is defined in a type in the
	 * com.xyz.someapp.service package or any sub-package under that.
	 */
	@Pointcut("within(com.xyz.someapp.service..*)")
	public void inServiceLayer() {
	}

	/**
	 * A join point is in the data access layer if the method is defined in a type in the
	 * com.xyz.someapp.dao package or any sub-package under that.
	 */
	@Pointcut("within(com.xyz.someapp.dao..*)")
	public void inDataAccessLayer() {
	}

	/**
	 * A business service is the execution of any method defined on a service interface.
	 * This definition assumes that interfaces are placed in the "service" package, and
	 * that implementation types are in sub-packages.
	 *
	 * If you group service interfaces by functional area (for example, in packages
	 * com.xyz.someapp.abc.service and com.xyz.someapp.def.service) then the pointcut
	 * expression "execution(* com.xyz.someapp..service.*.*(..))" could be used instead.
	 *
	 * Alternatively, you can write the expression using the 'bean' PCD, like so
	 * "bean(*Service)". (This assumes that you have named your Spring service beans in a
	 * consistent fashion.)
	 */
	@Pointcut("execution(* com.xyz.someapp..service.*.*(..))")
	public void businessService() {
	}

	/**
	 * A data access operation is the execution of any method defined on a dao interface.
	 * This definition assumes that interfaces are placed in the "dao" package, and that
	 * implementation types are in sub-packages.
	 */
	@Pointcut("execution(* mytest.aop.aspectj.*.*(..))")
	public void dataAccessOperation() {
	}

}
