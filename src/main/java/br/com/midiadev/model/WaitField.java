package br.com.midiadev.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by h3nrique on 8/12/15.
 */
@XStreamAlias("waitField")
public class WaitField implements Serializable {

    private String name;
    private FieldType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WaitField{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}