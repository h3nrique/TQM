package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by h3nrique on 8/12/15.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("tqm")
public class TQM implements Serializable {

    @XStreamAlias("firefoxBin")
    private String firefoxBin;

    @XStreamAlias("webdriverGeckoDriver")
    private String webdriverGeckoDriver;

    @XStreamAlias("logDir")
    private String logDir;

    @XStreamAlias("printDir")
    private String printDir;

    @XStreamAlias("requests")
    private Requests requests;
}