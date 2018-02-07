package com.main.pbl;

public class SucessSearch {

    private PBL targetedPbl;
    private String sequence;

    public SucessSearch(PBL targetedPbl, String sequence) {
        this.targetedPbl = targetedPbl;
        this.sequence = sequence;
    }

    public PBL getTargetedPbl() {
        return targetedPbl;
    }

    public void setTargetedPbl(PBL targetedPbl) {
        this.targetedPbl = targetedPbl;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return targetedPbl.getTopPLL().getName() + "/" + targetedPbl.getBottomPLL().getName() + ": " + sequence;
    }
}
