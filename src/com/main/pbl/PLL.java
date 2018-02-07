package com.main.pbl;

public class PLL {

    private String name;
    private String sequence;
    private boolean isParityPLL;

    public PLL(String name, String sequence, boolean isParityPLL) {
        this.name = name;
        this.sequence = sequence;
        this.isParityPLL = isParityPLL;
    }

    //TODO
    public String sequenceAtBottom(){
        String prefix = sequence.startsWith("/") ? "/6,6/-1,1" : "/6,6/-1,1/";
        String sufix = !sequence.endsWith("/") ? "/6,6/-1,1" : "1,-1/6,6/";
        return prefix + sequence + sufix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public boolean isParityPLL() {
        return isParityPLL;
    }

    public void setParityPLL(boolean parityPLL) {
        isParityPLL = parityPLL;
    }

    @Override
    public String toString() {
        return this.name + ": [" + this.sequence + "]";
    }
}
