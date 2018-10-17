package com.em.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//������.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// ���ò��ᱻ���ص����� ˳���ж�
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/jsp/login.jsp", "anon");
		//�����˳� ������,���еľ�����˳�����Shiro�Ѿ�������ʵ����
		filterChainDefinitionMap.put("/logOut", "logout");
		//<!-- ���������壬��������˳��ִ�У�һ�㽫/**������Ϊ�±� -->:����һ�����أ�һ��С�Ĵ���Ͳ���ʹ��;
		//<!-- authc:����url��������֤ͨ���ſ��Է���; anon:����url����������������-->
		filterChainDefinitionMap.put("/**", "authc");
		// ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
		shiroFilterFactoryBean.setLoginUrl("/login");
		// ��¼�ɹ���Ҫ��ת������
		shiroFilterFactoryBean.setSuccessUrl("/index");

		//δ��Ȩ����;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * ƾ֤ƥ����
	 * ���������ǵ�����У�齻��Shiro��SimpleAuthenticationInfo���д�����
	 * ��
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//ɢ���㷨:����ʹ��MD5�㷨;
		hashedCredentialsMatcher.setHashIterations(2);//ɢ�еĴ���������ɢ�����Σ��൱�� md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 *  ����shiro aopע��֧��.
	 *  ʹ�ô���ʽ;������Ҫ��������֧��;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//���ݿ��쳣����
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}
}
