package com.demo.ssdemo.sys.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: OZY
 * @createTime: 2019-08-13 15:28
 * @description:
 * @version: 1.0.0
 */
@Entity
@Table(name = "sys_role")
public class Role implements GrantedAuthority {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;

    /**
     * 角色标识
     */
    @Column
    private String roleKey;

    /**
     * 角色名称
     */
    @Column
    private String roleName;

    /**
     * 角色拥有的资源(多对多)
     */
    @ManyToMany(targetEntity = Resource.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_resource",
            joinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false)
            })
    private Set<Resource> resources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String getAuthority() {
        return roleKey;
    }
}
