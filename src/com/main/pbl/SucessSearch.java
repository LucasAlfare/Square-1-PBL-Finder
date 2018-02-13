package com.main.pbl;

/**
 * Esta é uma busca bem-sucedida.
 */
public class SucessSearch {

    private PBL targetedPbl;
    private String sequence;
    private AuxAlg[] auxAlgs;

    public SucessSearch(PBL targetedPbl, String sequence, AuxAlg... auxAlgs) {
        this.targetedPbl = targetedPbl;
        this.sequence = sequence;
        this.auxAlgs = auxAlgs;
    }

    public int getSequenceTwistMetricLenght(){
        return getSequence().split("/",-1).length - 1;
    }

    public int getFaceTurnMetricLenght() {
        String input = getSequence();
        int count = 0;
        boolean isPreviousDigit = false;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i)) && input.charAt(i) != '0') {
                if (!isPreviousDigit) {
                    count++;
                    isPreviousDigit = true;
                }
            } else {
                isPreviousDigit = false;
            }
        }

        return count + getSequenceTwistMetricLenght();
    }

    public PBL getTargetedPbl() {
        return targetedPbl;
    }

    public void setTargetedPbl(PBL targetedPbl) {
        this.targetedPbl = targetedPbl;
    }

    public String getSequence(){
        return CustomStringUtils.otimizedSequence(sequence);
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
                targetedPbl.getTopPLL().getName() + "/" + targetedPbl.getBottomPLL().getName() + ": " + getSequence());

        r.append("   Algs: [");
        for (AuxAlg x : auxAlgs){
            r.append(x.getName()).append(" | ");
        }
        r.append("]");

        return r.toString().replaceAll("\\| ]", "]").concat(
                "   ~{" + getSequenceTwistMetricLenght() + "/" + getFaceTurnMetricLenght() + "}");
    }
}