package com.main.pbl;

public class SucessSearch {

    private PBL targetedPbl;
    private String sequence;
    private AuxAlg[] auxAlgs;

    public SucessSearch(PBL targetedPbl, String sequence, AuxAlg... auxAlgs) {
        this.targetedPbl = targetedPbl;
        this.sequence = sequence;
        this.auxAlgs = auxAlgs;
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

    public AuxAlg[] getAuxAlgs() {
        return auxAlgs;
    }

    public void setAuxAlgs(AuxAlg[] auxAlgs) {
        this.auxAlgs = auxAlgs;
    }

    @Override
    public String toString() {
        String r = targetedPbl.getTopPLL().getName() + "/" + targetedPbl.getBottomPLL().getName() + ": " + sequence;

        r += "[";
        for (AuxAlg x : auxAlgs){
            r += x.getName() + "|";
        }

        r += ";]";

        return r.replaceAll(",;", "");
    }
}