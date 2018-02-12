package com.main.pbl;

import com.cs.main.puzzle.Cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Finder {

    private PBL targetPbl;
    private ArrayList<SucessSearch> sucessSearches;
    private long elapsedTime;
    private boolean searching;
    private String setups;

    public Finder(PBL targetPbl) {
        this.targetPbl = targetPbl;

        sucessSearches = new ArrayList<>();
    }

    public void search() {
        Cube squareOne = new Cube();
        String seqA = CustomStringUtils.otimizedSequence(
                CustomStringUtils.reversedSequence(targetPbl.getTopPLL().getSequence()));

        String seqB = CustomStringUtils.otimizedSequence(
                CustomStringUtils.reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom()));

        squareOne.applyStringSequence(seqA);
        squareOne.applyStringSequence(seqB);

        setups = "PBL: " + targetPbl.getName() + "\n";
        setups += "Setups aplicados:\n";
        setups +=
                seqA + ";\n" +
                seqB + ";\n\n";

        long i = System.currentTimeMillis();
        for (AuxAlg a : AlgTemplates.AUX_ALGS) {
            for (AuxAlg b : AlgTemplates.AUX_ALGS) {
                searching = true;

                String testSolveSeq = a.getSequence() + b.getSequence();
                squareOne.applyStringSequence(testSolveSeq);

                if (isSolved(squareOne)) {
                    elapsedTime = System.currentTimeMillis() - i;
                    sucessSearches.add(new SucessSearch(targetPbl, testSolveSeq, a, b));
                    squareOne.applyStringSequence(CustomStringUtils.reversedSequence(testSolveSeq));
                } else {
                    squareOne.applyStringSequence(CustomStringUtils.reversedSequence(testSolveSeq));
                }
            }
        }

        searching = false;
        elapsedTime = System.currentTimeMillis() - i;
    }

    /**
     * Checks if both layers of passed square-1 are solved.
     *
     * @param targetSquare the square to check layers.
     * @return true if solved.
     */
    private boolean isSolved(Cube targetSquare) {
        Cube solved = new Cube();
        ArrayList<Byte> topSolvedBytes = new ArrayList<>();
        topSolvedBytes.addAll(Arrays.asList(solved.getPieces(true)));
        topSolvedBytes.addAll(Arrays.asList(solved.getPieces(true)));

        ArrayList<Byte> bottomSolvedBytes = new ArrayList<>();
        bottomSolvedBytes.addAll(Arrays.asList(solved.getPieces(false)));
        bottomSolvedBytes.addAll(Arrays.asList(solved.getPieces(false)));

        return (Collections.indexOfSubList(topSolvedBytes, Arrays.asList(targetSquare.getPieces(true))) != -1) &&
                (Collections.indexOfSubList(bottomSolvedBytes, Arrays.asList(targetSquare.getPieces(false))) != -1);
    }

    /**
     * USELESS.
     *
     * @return
     */
    @Deprecated
    public PBL[] allPbls() {
        ArrayList<PBL> r = new ArrayList<>();
        int counter = 0;

        for (PLL top : AlgTemplates.STANDARD_PLLs) {
            for (PLL bottom : AlgTemplates.STANDARD_PLLs) {
                r.add(new PBL(counter + "", top, bottom));
                counter++;
            }
        }

        counter = 0;
        for (PLL top : AlgTemplates.PARITY_PLLs) {
            for (PLL bottom : AlgTemplates.PARITY_PLLs) {
                r.add(new PBL(counter + "*", top, bottom));
                counter++;
            }
        }

        return r.toArray(new PBL[r.size()]);
    }

    public PBL getTargetPbl() {
        return targetPbl;
    }

    public void setTargetPbl(PBL targetPbl) {
        this.targetPbl = targetPbl;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public boolean isSearching() {
        return searching;
    }

    public void setSearching(boolean searching) {
        this.searching = searching;
    }

    public ArrayList<SucessSearch> getSucessSearches() {
        return sucessSearches;
    }

    public void setSucessSearches(ArrayList<SucessSearch> sucessSearches) {
        this.sucessSearches = sucessSearches;
    }

    public String getSetups() {
        return setups;
    }

    public void setSetups(String setups) {
        this.setups = setups;
    }
}
