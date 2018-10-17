package com.em.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Map<String, Object> map)
			throws Exception {
		System.out.println("HomeController.login()");
		// ��¼ʧ�ܴ�request�л�ȡshiro������쳣��Ϣ��
		// shiroLoginFailure:����shiro�쳣���ȫ����.
		String exception = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("exception=" + exception);
		String msg = "";
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				System.out.println("UnknownAccountException -- > �˺Ų����ڣ�");
				msg = "UnknownAccountException -- > �˺Ų����ڣ�";
			} else if (IncorrectCredentialsException.class.getName().equals(
					exception)) {
				System.out.println("IncorrectCredentialsException -- > ���벻��ȷ��");
				msg = "IncorrectCredentialsException -- > ���벻��ȷ��";
			} else if ("kaptchaValidateFailed".equals(exception)) {
				System.out.println("kaptchaValidateFailed -- > ��֤�����");
				msg = "kaptchaValidateFailed -- > ��֤�����";
			} else {
				msg = "else >> " + exception;
				System.out.println("else -- >" + exception);
			}
		}
		map.put("msg", msg);
		// �˷����������¼�ɹ�,��shiro���д���
		return "login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		System.out.println("------û��Ȩ��-------");
		return "403";
	}

}