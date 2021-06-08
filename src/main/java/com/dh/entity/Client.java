package com.dh.entity;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @Date 2019/3/1 上午9:08
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 8957107006902627635L;

    private String token;

    private Integer id;

    private Session session;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getId(){
        return id;
    }

    public Client(String token, Session session, Integer id) {
        this.token = token;
        this.session = session;
        this.id = id;
    }

    public Client() {
    }
}
