package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by h3nrique on 8/12/15.
 */
@XStreamAlias("request")
public class Request implements Serializable {

    private String url;
    private String name;
    private WaitField waitField;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WaitField getWaitField() {
        return waitField;
    }

    public void setWaitField(WaitField waitField) {
        this.waitField = waitField;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", waitField=" + waitField +
                '}';
    }
}