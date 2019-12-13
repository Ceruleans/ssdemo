package com.demo.ssdemo.sys.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * @Author OZY
 * @Date 2019/08/09 20:42
 * @Description
 * @Version V1.0
 **/
@Entity
@Table(name = "sys_user")
public class User implements UserDetails {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;


    /**
     * 是否锁定
     * true: 未锁定
     * false: 锁定
     */
    @Column
    private boolean lockedFlag;

    /**
     * 用户所属角色
     */
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


    /**
     * security存储权限认证用的
     */
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户账号是否过期
     * true: 未过期
     * false: 已过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 用户账号是否被锁定
     * true: 未锁定
     * false: 锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return lockedFlag;
    }


    /**
     * 用户账号凭证(密码)是否过期
     * 简单的说就是可能会因为修改了密码导致凭证过期这样的场景
     * true: 过期
     * false: 无效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    /**
     * 用户账号是否被启用
     * true: 启用
     * false: 未启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(boolean lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
