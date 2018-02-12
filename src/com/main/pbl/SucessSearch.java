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
        StringBuilder r = new StringBuilder(
                targetedPbl.getTopPLL().getName() + "/" + targetedPbl.getBottomPLL().getName() + ": " + CustomStringUtils.otimizedSequence(sequence));

        r.append("   Algs: [");
        for (AuxAlg x : auxAlgs){
            r.append(x.getName()).append(" | ");
        }
        r.append("]");

        return r.toString().replaceAll("\\| ]", "]");
    }
}