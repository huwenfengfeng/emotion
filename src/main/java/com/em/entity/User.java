package com.em.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User implements Serializable{

	@Id
    @GeneratedValue
	private String userId; //�û�id
	private String nickname; //�û��ǳ�
	@Column(unique =true)
	private String account; //�û��˺ţ�֧���ֻ����룬�����Զ�������λ���ֵ���267890
	private String password; //����
	private String salt;//�����������
	private byte state;//�û�״̬,0:����δ��֤������û�м��û��������֤��ȵȣ�--�ȴ���֤���û� , 1:����״̬,2���û�������.
	private String userPic; //�û�ͷ��
	private Date createTime; //��������
	private Date updateTime; //��������
	
	@ManyToMany(fetch= FetchType.EAGER)//���������ݿ��н��м�������;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// һ���û����ж����ɫ

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	 /**
     * ������.���¶������½����˶��壬�û���+salt�������͸��Ӳ����ױ��ƽ�
     * @return
     */
    public String getCredentialsSalt(){
        return this.account+this.salt;
    }
	
}
