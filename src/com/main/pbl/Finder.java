package com.main.pbl;

import com.main.puzzle.Piece;
import com.main.puzzle.SquareOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Finder {

    private PBL targetPbl;
    private ArrayList<SucessSearch> sucessSearches;
    private long elapsedTime;

    public Finder(PBL targetPbl) {
        this.targetPbl = targetPbl;
        sucessSearches = new ArrayList<>();
    }

    public ArrayList<SucessSearch> getSucessSearches(){
        SquareOne squareOne = new SquareOne();
        squareOne.applyStringSequence(reversedSequence(targetPbl.getTopPLL().getSequence()));
        squareOne.applyStringSequence(reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom()));

        long i = System.currentTimeMillis();
        for (AuxAlg a : AlgTemplates.AUX_ALGS) {
            for (AuxAlg b : AlgTemplates.AUX_ALGS) {

                String testSolveSeq = a.getSequence() + b.getSequence();
                squareOne.applyStringSequence(testSolveSeq);

                if (isSolved(squareOne)) {
                    elapsedTime = System.currentTimeMillis() - i;
                    sucessSearches.add(new SucessSearch(targetPbl, testSolveSeq, a, b));
                } else {
                    squareOne.applyStringSequence(reversedSequence(testSolveSeq));
                }
            }
        }

        return sucessSearches;
    }

    @Deprecated
    public void check() {
        System.out.println("Aimed PBL: " + targetPbl);

        System.out.println("Top setup:    " + reversedSequence(targetPbl.getTopPLL().getSequence()));
        System.out.println("Bottom setup: " + reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom()));
        System.out.println();

        SquareOne squareOne = new SquareOne();
        squareOne.applyStringSequence(reversedSequence(targetPbl.getTopPLL().getSequence()));
        squareOne.applyStringSequence(reversedSequence(targetPbl.getBottomPLL().sequenceAtBottom()));

        System.out.println("PBL " + targetPbl.getName() + " was applied to the cube! This is the square-1 view:");
        System.out.println(squareOne);

        System.out.println();
        System.out.println("Searching...");
        long i = System.currentTimeMillis();
        for (AuxAlg a : AlgTemplates.AUX_ALGS) {
            for (AuxAlg b : AlgTemplates.AUX_ALGS) {

                String testSolveSeq = a.getSequence() + b.getSequence();
                squareOne.applyStringSequence(testSolveSeq);

                if (isSolved(squareOne)) {
                    //long elapesedTime = System.currentTimeMillis() - i;
                    System.out.println("FOUND!!");

                    System.out.println("The sequence\n" +
                            testSolveSeq.replaceAll(" ", "") + "[" + a.getName() + "|" + b.getName() +
                            "]\nsolves the passed PBL!!");

                    //System.out.println("Search time for single solution was " + elapesedTime + " miliseconds.");
                    sucessSearches.add(new SucessSearch(targetPbl, testSolveSeq));
                    System.out.println();
                    System.out.println();
                    //return;
                } else {
                    squareOne.applyStringSequence(reversedSequence(testSolveSeq));
                }
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Solutions foud: " + sucessSearches.size());
        //System.err.println("Couldn't find a sequence! :(");
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - i));
    }

    //TODO: verificar faces em apenas 1 loop
    private boolean isSolved(SquareOne targetSquare) {
        SquareOne aSolvedSquareOne = new SquareOne();
        aSolvedSquareOne.setColorScheme(targetSquare.getColorScheme());

        boolean currentEquals = false;

        ArrayList<Piece> topTop = new ArrayList<>();
        topTop.addAll(Arrays.asList(aSolvedSquareOne.getTopPieces()));
        topTop.addAll(Arrays.asList(aSolvedSquareOne.getTopPieces()));
        Piece[] targetTop = targetSquare.getTopPieces();

        for (int i = 0; i < topTop.size() - targetTop.length; i++) {
            for (int j = 0; j < targetTop.length; j++) {
                currentEquals = Arrays.equals(targetTop[j].getColors(), topTop.get(i + j).getColors());
                if (!currentEquals) {
                    break;
                }
            }
            if (currentEquals) {
                break;
            }
        }

        if (currentEquals) {
            ArrayList<Piece> bottomBottom = new ArrayList<>();
            bottomBottom.addAll(Arrays.asList(aSolvedSquareOne.getBottomPieces()));
            bottomBottom.addAll(Arrays.asList(aSolvedSquareOne.getBottomPieces()));
            Piece[] targetBottom = targetSquare.getBottomPieces();

            for (int i = 0; i < bottomBottom.size() - targetBottom.length; i++) {
                for (int j = 0; j < targetBottom.length; j++) {
                    currentEquals = Arrays.equals(targetBottom[j].getColors(), bottomBottom.get(i + j).getColors());
                    if (!currentEquals) {
                        break;
                    }
                }
                if (currentEquals) {
                    break;
                }
            }
        }

        return currentEquals;
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

        String r = hold.toString().replaceAll(", ", "/").replaceAll("\\[", "").replaceAll("]", "");

        if (algorithm.startsWith("/")) r += "/";
        if (algorithm.endsWith("/")) r = "/" + r;

        return r;
    }

    @Deprecated
    public static PBL[] allPbls() {
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
}
