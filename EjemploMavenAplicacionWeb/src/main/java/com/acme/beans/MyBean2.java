package com.acme.beans;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyBean2 {

    private InetAddress local = null;

    public InetAddress getLocal() {
        return local;
    }

    public void setLocal(InetAddress ip) {
        local = ip;
    }

    public void setLocal(String localHost) {
        try {
            local = InetAddress.getByName(localHost);
        } catch (UnknownHostException ex) {
        }
    }

    private InetAddress remote = null;

    public InetAddress getRemote() {
        return remote;
    }

    public void setRemote(InetAddress ip) {
        remote = ip;
    }

    public void host(String remoteHost) {
        try {
            remote = InetAddress.getByName(remoteHost);
        } catch (UnknownHostException ex) {
        }
    }
}
