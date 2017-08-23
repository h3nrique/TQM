package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by h3nrique on 8/12/15.
 */
public class Requests implements Serializable {

    @XStreamImplicit
    @XStreamAlias("request")
    private List<Request> request;

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "request='" + request + '\'' +
                '}';
    }

    public class Request implements Serializable {

        private String url;
        private String name;
        @XStreamAlias("waitField")
        private WaitField waitField;
        private Integer timeoutLoadPageInSeconds;

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

        public Integer getTimeoutLoadPageInSeconds() {
            return timeoutLoadPageInSeconds;
        }

        public void setTimeoutLoadPageInSeconds(Integer timeoutLoadPageInSeconds) {
            this.timeoutLoadPageInSeconds = timeoutLoadPageInSeconds;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    ", waitField=" + waitField +
                    ", timeoutLoadPageInSeconds=" + timeoutLoadPageInSeconds +
                    '}';
        }
    }
}