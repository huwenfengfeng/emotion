package com.em.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.em.entity.SysPermission;
import com.em.entity.SysRole;
import com.em.entity.User;
import com.em.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Resource
    private UserService userService;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Ȩ������-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)principals.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*��Ҫ���������������֤�ģ�Ҳ����˵��֤�û�������˺ź������Ƿ���ȷ��*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //��ȡ�û���������˺�.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //ͨ��username�����ݿ��в��� User��������ҵ���û�ҵ�.
        //ʵ����Ŀ�У�������Ը���ʵ����������棬���������Shiro�Լ�Ҳ����ʱ�������ƣ�2�����ڲ����ظ�ִ�и÷���
        User userInfo = userService.findUserByName(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //�û���
                userInfo.getPassword(), //����
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
