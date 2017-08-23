package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by h3nrique on 8/12/15.
 */
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

    public Requests getRequests() {
        return requests;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }

    public String getFirefoxBin() {
        return firefoxBin;
    }

    public void setFirefoxBin(String firefoxBin) {
        this.firefoxBin = firefoxBin;
    }

    public String getWebdriverGeckoDriver() {
        return webdriverGeckoDriver;
    }

    public void setWebdriverGeckoDriver(String webdriverGeckoDriver) {
        this.webdriverGeckoDriver = webdriverGeckoDriver;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public String getPrintDir() {
        return printDir;
    }

    public void setPrintDir(String printDir) {
        this.printDir = printDir;
    }

    @Override
    public String toString() {
        return "TQM{" +
                "requests=" + requests +
                "firefoxBin=" + firefoxBin +
                "webdriverGeckoDriver=" + webdriverGeckoDriver +
                "logDir=" + logDir +
                "printDir=" + printDir +
                '}';
    }
}