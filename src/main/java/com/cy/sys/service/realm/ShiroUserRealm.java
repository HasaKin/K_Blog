package com.cy.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.sys.dao.SysMenuDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.dao.SysUserDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysUser;


@Service
public class ShiroUserRealm
extends AuthorizingRealm{
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher)
	{
		HashedCredentialsMatcher hcm = 
				new HashedCredentialsMatcher("MD5");

		hcm.setHashIterations(1);
		super.setCredentialsMatcher(hcm);
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException
	{
		UsernamePasswordToken upToken = 
				(UsernamePasswordToken)token;
		String username = upToken.getUsername();

		SysUser user = this.sysUserDao.findUserByUserName(username);

		if (user == null) {
			throw new UnknownAccountException();
		}

		if (user.getValid().intValue() == 0) {
			throw new LockedAccountException();
		}

		ByteSource credentialsSalt = 
				ByteSource.Util.bytes(user.getSalt());

		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(
						user, 
						user.getPassword(), 
						credentialsSalt, 
						getName());
		return info;
	}

	private Map<String, SimpleAuthorizationInfo> pCache = new ConcurrentHashMap();



	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SysUser user = (SysUser)principals.getPrimaryPrincipal();
		if (this.pCache.containsKey(user.getUsername())) {
			return (AuthorizationInfo)this.pCache.get(user.getUsername());
		}
		System.out.println("doGetAuthorizationInfo");

		List<Integer> roleIds = 
				this.sysUserRoleDao.findRoleIdsByUserId(user.getId());
		if ((roleIds == null) || (roleIds.size() == 0)) {
			throw new UnauthorizedException();
		}
		List<Integer> menuIds = 
				this.sysRoleMenuDao.findMenuIdsByRoleIds(
						(Integer[])roleIds.toArray(new Integer[0]));
		if ((menuIds == null) || (menuIds.size() == 0)) {
			throw new UnauthorizedException();
		}
		List<String> permissions = 
				this.sysMenuDao.findPermissions(
						(Integer[])menuIds.toArray(new Integer[0]));
		if ((permissions == null) || (permissions.size() == 0)) {
			throw new UnauthorizedException();
		}
		SimpleAuthorizationInfo info = 
				new SimpleAuthorizationInfo();
		Set<String> set = new HashSet();
		System.out.println("permissions=" + permissions);
		for (String per : permissions) {
			if (!StringUtils.isEmpty(per)) {
				set.add(per);
			}
		}
		info.setStringPermissions(set);
		this.pCache.put(user.getUsername(), info);
		return info;
	}
}





