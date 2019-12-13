package com.demo.ssdemo.sys.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: OZY
 * @createTime: 2019-08-13 15:28
 * @description:
 * @version: 1.0.0
 */
@Entity
@Table(name = "sys_resource")
public class Resource  implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Integer id;

    /**
     * 资源名称
     */
    @Column(nullable = false)
    private String resourceName;

    /**
     * 资源标识
     */
    @Column(nullable = false)
    private String resourceKey;

    /**
     * 资源url
     */
    @Column(nullable = false)
    private String url;

    /**
     * 资源类型
     * 0:菜单
     * 1:按钮
     */
    @Column(nullable = false)
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
