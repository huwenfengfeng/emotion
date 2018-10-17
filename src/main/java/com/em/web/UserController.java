package com.em.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserController {

	 /**
     * �û���ѯ.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//Ȩ�޹���;
    public String userInfo(){
        return "userInfo";
    }

    /**
     * �û����;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//Ȩ�޹���;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * �û�ɾ��;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//Ȩ�޹���;
    public String userDel(){
        return "userInfoDel";
    }
	
}
