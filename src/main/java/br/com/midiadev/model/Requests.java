package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by h3nrique on 8/12/15.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Requests implements Serializable {

    @XStreamImplicit
    @XStreamAlias("request")
    private List<Request> request;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class Request implements Serializable {

        private String url;
        private String name;
        @XStreamAlias("waitField")
        private WaitField waitField;
        private Integer timeoutLoadPageInSeconds;
    }
}