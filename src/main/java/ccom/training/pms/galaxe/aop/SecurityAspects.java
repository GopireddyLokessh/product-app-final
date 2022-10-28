package ccom.training.pms.galaxe.aop;
import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspects {

	@Before(value =  "execution(* ccom.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void loggedIn() {
		System.out.println("### SA--Logged in at :"+new Date()+ " By Aspects");
	}

	@After(value =  "execution(* ccom.training.pms.galaxe.service.ProductServiceImpl.*(..))")
	public void loggedOut() {
		System.out.println("###SA--Logged Out at :"+new Date()+ " By Aspects");
	}
}