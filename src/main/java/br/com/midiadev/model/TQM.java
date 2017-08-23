package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by h3nrique on 8/12/15.
 */
@XStreamAlias("tqm")
public class TQM implements Serializable {

    private List<Request> requests;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "TQM{" +
                "requests=" + requests +
                '}';
    }
}