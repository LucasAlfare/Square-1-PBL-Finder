package com.main.pbl;

import com.cs.main.puzzle.FullCube;

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

    public void search(){
//        SquareOne squareOne = new SquareOne();

        FullCube squareOne = new FullCube();
        squareOne.applyStringSequence(reversedSequence(targetPbl.getTopPLL().getSequence()));
        squareOne.applyStringSequence(reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom()));

        setups = "Setups aplicados:\n";
        setups += reversedSequence(targetPbl.getTopPLL().getSequence()) + ";\n" +
                reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom())+ ";\n\n";

        long i = System.currentTimeMillis();

//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
                for (AuxAlg a : AlgTemplates.AUX_ALGS) {
                    for (AuxAlg b : AlgTemplates.AUX_ALGS) {
                        searching = true;

                        String testSolveSeq = a.getSequence() + b.getSequence();
                        squareOne.applyStringSequence(testSolveSeq);

                        if (isSolved(squareOne)) {
                            elapsedTime = System.currentTimeMillis() - i;
                            sucessSearches.add(new SucessSearch(targetPbl, testSolveSeq, a, b));
                            squareOne.applyStringSequence(reversedSequence(testSolveSeq));
                        } else {
                            squareOne.applyStringSequence(reversedSequence(testSolveSeq));
                        }
                    }
                }
//            }
//        });
//        a.start();

        searching = false;
        elapsedTime = System.currentTimeMillis() - i;
    }

    //TODO: verificar faces em apenas 1 loop
    private boolean isSolved(FullCube targetSquare) {
        FullCube solved = new FullCube();
        ArrayList<Byte> topSolvedBytes = new ArrayList<>();
        topSolvedBytes.addAll(Arrays.asList(solved.getPieces(true)));
        topSolvedBytes.addAll(Arrays.asList(solved.getPieces(true)));

        ArrayList<Byte> bottomSolvedBytes = new ArrayList<>();
        bottomSolvedBytes.addAll(Arrays.asList(solved.getPieces(false)));
        bottomSolvedBytes.addAll(Arrays.asList(solved.getPieces(false)));

        return (Collections.indexOfSubList(topSolvedBytes, Arrays.asList(targetSquare.getPieces(true))) != -1) &&
                (Collections.indexOfSubList(bottomSolvedBytes, Arrays.asList(targetSquare.getPieces(false))) != -1);
    }

    private String reversedSequence(String algorithm) {
        ArrayList<String> hold = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>(Arrays.asList(algorithm.replaceAll(" ", "").split("/")));

        Collections.reverse(aux);

        for (String x : aux) {
            String[] aux2 = x.split(",");
            if (!x.equals("")) {
                hold.add((Integer.parseInt(aux2[0]) * -1) + "," + (Integer.parseInt(aux2[1]) * -1));
            }
        }

        String r = hold.toString().replaceAll(", ", "/").replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");

        if (algorithm.startsWith("/")) r += "/";
        if (algorithm.endsWith("/")) r = "/" + r;

        return r;
    }

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
