package org.chench.springboot.scaffolding.model;

/**
 * 数据库表模型定义
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.model.Account
 * @date 2023.07.17
 */
public class Account {
    private long id = -1;
    private String name = "";
    private String email = "";
    private long ctime = -1;
    private long mtime = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }
}
