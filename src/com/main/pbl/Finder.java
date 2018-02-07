package com.main.pbl;

import com.main.puzzle.Piece;
import com.main.puzzle.SquareOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Finder {

    private static final PLL[] STANDARD_PLLs = {
            //new PLL("Skip", "", false),
            new PLL("Ua", "/3,0/1,0/0,-3/-1,0/-3,0/1,0/0,3/-1,0", false),
            new PLL("Ub", "1,0/0,-3/-1,0/3,0/1,0/0,3/-1,0/-3,0/", false),
            new PLL("Z", "1,0/-1,-1/-2,1/-1,-1/4,1/-1,-1/0,1", false),
            new PLL("H", "1,0/-1,-1/-2,1/-1,-1/-5,1/-1,-1/-2,1/-1,-1/0,1", false),
            new PLL("Aa", "1,0/-3,0/3,3/0,-3/2,0/-3,0/3,3/0,-3/", false),
            new PLL("Ab", "/-3,0/3,3/0,-3/-2,0/-3,0/3,3/0,-3/-1,0", false),
            new PLL("E", "/3,3/1,-2/2,2/-3,0/-3,-3/", false),
            new PLL("F", "1,0/-1,-1/-3,0/3,0/1,4/-3,0/3,0/2,-4/-3,0/0,1", false),
            new PLL("Ja", "1,0/3,-3/3,0/-3,0/0,3/-3,0/-1,0", false),
            new PLL("Jb", "/3,-3/3,0/-3,0/0,3/-3,0/", false),
            new PLL("Ra", "/-3,0/3,0/-3,0/0,-3/4,1/-1,2/-3,-3/", false),
            new PLL("Rb", "1,0/3,0/-3,0/3,0/0,3/2,5/1,4/3,3/-1,0/", false),
            new PLL("T", "/3,0/-3,-3/0,3/4,0/3,0/-3,-3/0,3/-1,0", false),
            new PLL("Y", "1,0/3,0/3,3/3,0/5,0/0,3/3,3/0,3/", false),
            new PLL("Na", "/3,3/-3,0/3,3/-3,0/3,3/", false),
            new PLL("Nb", "1,0/3,3/-3,0/3,3/-3,0/3,3/-1,0", false),
            new PLL("Ga", "/-3,3/3,-3/-2,0/0,3/0,-3/0,3/0,-3/-1,0", false),
            new PLL("Gb", "1,0/0,3/0,-3/0,3/0,-3/-1,0/-3,3/3,-3/", false),
            new PLL("Gc", "1,0/-3,3/3,-3/-4,0/0,3/0,-3/0,3/0,-3/", false),
            new PLL("Gd", "/0,3/0,-3/0,3/0,-3/1,0/-3,3/3,-3/-1,0", false),
            new PLL("V", "1,0/3,0/-4,-1/0,-3/3,0/1,-2/0,3/0,3/-4,-1/0,1", false)
    };

    private static final PLL[] PARITY_PLLs = {
            new PLL("Adj", "/-3,0/0,3/0,-3/0,3/2,0/0,2/-2,0/4,0/0,-2/0,2/-1,4/0,-3/", true),
            new PLL("Opp", "/3,3/1,0/-2,-2/2,0/2,2/0,-2/-1,-1/0,3/-3,-3/0,2/-2,-2/-1,0", true),
            new PLL("Oa", "/3,3/1,0/-2,-2/-2,0/2,2/-1,0/-3,-3/1,0/2,2/0,1", true),
            new PLL("Ob", "/3,3/1,0/-2,-2/2,0/2,2/-1,0/-3,-3/0,2/-2,-2/-1,0", true),
            new PLL("W", "0,-1/1,-2/-4,0/0,3/1,0/3,-2/-4,0/-4,0/-2,2/-1,0/0,-3/", true),
            new PLL("M", "/3,0/1,2/2,0/-4,0/4,0/2,0/-1,-2/-3,0/-2,0/-4,2/4,-2/-1,0", true),
            new PLL("pN", "/-3,0/0,-3/2,0/0,2/-2,0/4,0/0,-2/0,2/-4,1/3,0/", true),
            new PLL("pJ", "/-3,0/-3,0/-1,0/0,2/-2,0/4,0/0,-2/0,2/-1,4/0,-3/", true),
            new PLL("X", "/3,0/1,0/4,5/0,4/0,4/2,3/0,-1/-3,0/-4,0/1,-2/0,3/-1,0", true),
            new PLL("Q", "1,0/5,0/3,3/1,-4/4,0/2,2/0,4/4,-1/3,3/-5,0/-1,0", true),
            new PLL("Ka", "/3,3/5,0/0,2/-2,0/4,0/0,-2/0,2/-1,1/3,0/3,3/", true),
            new PLL("Kb", "1,0/5,0/-3,-3/0,3/4,0/-2,0/4,0/-4,0/-2,0/-1,0/-3,-3/-2,3/-1,0", true),
            new PLL("Sa", "/-3,0/0,-1/-4,0/0,4/0,4/4,0/-4,0/1,-4/3,0/3,-3/", true),
            new PLL("Sb", "/3,0/1,0/0,2/4,0/0,4/2,-2/0,4/1,-1/3,0/-3,0/-3,1/-1,0", true),
            new PLL("Ba", "/0,3/-3,3/2,1/0,-2/2,0/-4,0/0,2/0,-2/0,1/0,-3/0,-3/", true),
            new PLL("Bb", "1,0/2,-4/1,4/-4,0/0,3/1,0/-1,4/0,4/4,0/2,0/-1,0/-3,0/", true),
            new PLL("Ca", "0,-1/-3,0/3,0/0,1/3,0/1,0/-2,0/2,-1/0,1/-4,0/-5,0/-4,0/-1,0/-3,0/", true),
            new PLL("Cb", "/3,0/1,0/4,0/5,0/4,0/0,-1/-2,1/2,0/-1,0/-3,0/0,-1/-3,0/3,0/0,1", true),
            new PLL("Da", "/3,0/1,0/-2,0/-4,0/0,-4/1,-4/-1,0/0,-3/4,0/-1,-4/-2,4/-1,0", true),
            new PLL("Db", "/0,3/0,3/0,-1/0,2/0,-2/4,0/-2,0/0,2/-2,-1/3,-3/0,-3/", true),
            new PLL("Pa", "/-3,0/3,-3/3,0/-1,0/0,2/-2,0/4,0/0,-2/0,2/-4,1/3,0/", true),
            new PLL("Pb", "/-3,0/4,-1/0,-2/0,2/-4,0/2,0/0,-2/1,0/-3,0/-3,3/3,0/", true)
    };

    private static final AuxAlg[] AUX_ALGS = {
            new AuxAlg("N/N1", "/3,-3/-3,3/0,0"),
            new AuxAlg("N/N2", "/0,0/1,0/3,-3/-3,3/-1,0"),
            new AuxAlg("N/N3", "/0,0/0,-1/3,-3/-3,3/0,1"),
            new AuxAlg("N/N4", "/0,0/1,-1/3,-3/-3,3/-1,1"),
            new AuxAlg("pN/pN1", "/0,0/1,0/2,-4/-2,4/-1,0"),
            new AuxAlg("pN/pN2", "/0,0/-2,0/2,-4/-2,4/2,0"),
            new AuxAlg("pN/pN3", "/0,0/1,3/2,-4/-2,4/-1,0"),
            new AuxAlg("pN/pN4", "/0,0/-2,3/2,-4/-2,4/2,0"),
            new AuxAlg("J/J1", "/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J2", "/0,0/0,3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J3", "/0,0/0,6/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J4", "/0,0/0,-3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J5", "/0,0/0,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J6", "/0,0/0,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J7", "/0,0/0,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J8", "/0,0/0,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J9", "/0,0/-3,0/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J10", "/0,0/-3,3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J11", "/0,0/-3,6/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J12", "/0,0/-3,-3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J13", "/0,0/-3,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J14", "/0,0/-3,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J15", "/0,0/-3,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J16", "/0,0/-3,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J17", "/0,0/6,0/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J18", "/0,0/6,3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J19", "/0,0/6,6/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J20", "/0,0/6,-3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J21", "/0,0/6,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J22", "/0,0/6,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J23", "/0,0/6,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J24", "/0,0/6,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J25", "/0,0/3,0/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J26", "/0,0/3,3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J27", "/0,0/3,6/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J28", "/0,0/3,-3/-3,0/3,3/0,-3/0,0"),
            new AuxAlg("J/J29", "/0,0/3,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J30", "/0,0/3,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J31", "/0,0/3,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J32", "/0,0/3,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J33", "/0,0/1,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J34", "/0,0/1,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J35", "/0,0/1,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J36", "/0,0/1,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J37", "/0,0/1,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J38", "/0,0/1,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J39", "/0,0/1,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J40", "/0,0/1,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J41", "/0,0/-2,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J42", "/0,0/-2,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J43", "/0,0/-2,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J44", "/0,0/-2,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J45", "/0,0/-2,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J46", "/0,0/-2,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J47", "/0,0/-2,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J48", "/0,0/-2,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J49", "/0,0/-5,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J50", "/0,0/-5,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J51", "/0,0/-5,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J52", "/0,0/-5,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J53", "/0,0/-5,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J54", "/0,0/-5,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J55", "/0,0/-5,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J56", "/0,0/-5,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J57", "/0,0/4,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J58", "/0,0/4,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J59", "/0,0/4,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J60", "/0,0/4,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J61", "/0,0/4,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J62", "/0,0/4,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J63", "/0,0/4,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J64", "/0,0/4,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("pJ/pJ1", "/0,0/1,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ2", "/0,0/1,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ3", "/0,0/1,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ4", "/0,0/1,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ5", "/0,0/-2,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ6", "/0,0/-2,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ7", "/0,0/-2,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ8", "/0,0/-2,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ9", "/0,0/-5,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ10", "/0,0/-5,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ11", "/0,0/-5,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ12", "/0,0/-5,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ13", "/0,0/4,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ14", "/0,0/4,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ15", "/0,0/4,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ16", "/0,0/4,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("J/N1", "/0,3/0,-3/0,3/0,-3/0,0"),
            new AuxAlg("J/N2", "/0,0/0,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N3", "/0,0/-3,0/0,3/0,-3/0,3/0,-3/0,0"),
            new AuxAlg("J/N4", "/0,0/-3,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N5", "/0,0/6,0/0,3/0,-3/0,3/0,-3/0,0"),
            new AuxAlg("J/N6", "/0,0/6,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N7", "/0,0/3,0/0,3/0,-3/0,3/0,-3/0,0"),
            new AuxAlg("J/N8", "/0,0/3,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N9", "/0,0/1,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N10", "/0,0/1,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N11", "/0,0/-2,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N12", "/0,0/-2,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N13", "/0,0/-5,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N14", "/0,0/-5,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N15", "/0,0/4,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N16", "/0,0/4,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("N/J1", "/3,0/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("N/J2", "/0,0/1,0/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J3", "/0,0/0,3/3,0/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("N/J4", "/0,0/1,3/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J5", "/0,0/0,6/3,0/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("N/J6", "/0,0/1,6/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J7", "/0,0/0,-3/3,0/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("N/J8", "/0,0/1,-3/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J9", "/0,0/0,-1/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J10", "/0,0/1,-1/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J11", "/0,0/0,2/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J12", "/0,0/1,2/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J13", "/0,0/0,5/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J14", "/0,0/1,5/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J15", "/0,0/0,-4/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J16", "/0,0/1,-4/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("opp/opp1", "/0,0/1,0/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp2", "/0,0/4,0/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp3", "/0,0/1,3/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp4", "/0,0/4,3/5,-1/-5,1/-1,0"),
            new AuxAlg("adj/adj1", "/0,0/1,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj2", "/0,0/1,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj3", "/0,0/1,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj4", "/0,0/1,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj5", "/0,0/-2,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj6", "/0,0/-2,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj7", "/0,0/-2,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj8", "/0,0/-2,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj9", "/0,0/-5,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj10", "/0,0/-5,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj11", "/0,0/-5,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj12", "/0,0/-5,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj13", "/0,0/4,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj14", "/0,0/4,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj15", "/0,0/4,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj16", "/0,0/4,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("F/V1", "/0,0/0,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V2", "/0,0/3,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V3", "/0,0/6,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V4", "/0,0/-3,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V5", "/0,0/0,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V6", "/0,0/3,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V7", "/0,0/6,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V8", "/0,0/-3,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V9", "/0,0/0,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V10", "/0,0/3,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V11", "/0,0/6,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V12", "/0,0/-3,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V13", "/0,0/0,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V14", "/0,0/3,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V15", "/0,0/6,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V16", "/0,0/-3,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("V/F1", "/0,0/1,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F2", "/0,0/1,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F3", "/0,0/1,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F4", "/0,0/1,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F5", "/0,0/4,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F6", "/0,0/4,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F7", "/0,0/4,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F8", "/0,0/4,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F9", "/0,0/-2,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F10", "/0,0/-2,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F11", "/0,0/-2,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F12", "/0,0/-2,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F13", "/0,0/-5,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F14", "/0,0/-5,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F15", "/0,0/-5,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F16", "/0,0/-5,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("U/U1", "/0,0/1,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U2", "/0,0/1,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U3", "/0,0/1,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U4", "/0,0/1,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U5", "/0,0/4,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U6", "/0,0/4,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U7", "/0,0/4,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U8", "/0,0/4,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U9", "/0,0/-2,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U10", "/0,0/-2,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U11", "/0,0/-2,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U12", "/0,0/-2,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U13", "/0,0/-5,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U14", "/0,0/-5,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U15", "/0,0/-5,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U16", "/0,0/-5,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U'/U'1", "/0,0/1,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'2", "/0,0/1,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'3", "/0,0/1,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'4", "/0,0/1,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'5", "/0,0/4,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'6", "/0,0/4,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'7", "/0,0/4,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'8", "/0,0/4,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'9", "/0,0/-2,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'10", "/0,0/-2,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'11", "/0,0/-2,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'12", "/0,0/-2,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'13", "/0,0/-5,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'14", "/0,0/-5,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'15", "/0,0/-5,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'16", "/0,0/-5,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("O/opp1", "/0,0/1,0 / -1,-1 / 4,1 / -1,-1 / 4,1 / -1,-1 / 6,1"),
            new AuxAlg("O/opp2", "/0,0/1,3 / -1,-1 / 4,1 / -1,-1 / 4,1 / -1,-1 / 6,1"),
            new AuxAlg("O/opp3", "/0,0/1,0 / -1,-1 / -2,1 / -1,-1 / -2,1 / -1,-1 / 6,1"),
            new AuxAlg("O/opp4", "/0,0/1,3 / -1,-1 / -2,1 / -1,-1 / -2,1 / -1,-1 / 6,1"),
            new AuxAlg("opp/O1", "/0,0/ 1,0 / -1,-1 / 1,-2 / -1,-1 / 1,-2 / -1,-1 / 0,-5"),
            new AuxAlg("opp/O2", "/0,0/ 4,0 / -1,-1 / 1,-2 / -1,-1 / 1,-2 / -1,-1 / 0,-5"),
            new AuxAlg("opp/O3", "/0,0/ 1,0 / -1,-1 / 1,4 / -1,-1 / 1,4 / -1,-1 / 0,-5"),
            new AuxAlg("opp/O4", "/0,0/ 4,0 / -1,-1 / 1,4 / -1,-1 / 1,4 / -1,-1 / 0,-5"),
            new AuxAlg("J/-1", "/3,-3/3,0/-3,0/0,3/-3,0/0,0"),
            new AuxAlg("J/-2", "/0,0/3,0/3,-3/3,0/-3,0/0,3/-3,0/0,0"),
            new AuxAlg("J/-3", "/0,0/6,0/3,-3/3,0/-3,0/0,3/-3,0/0,0"),
            new AuxAlg("J/-4", "/0,0/-3,0/3,-3/3,0/-3,0/0,3/-3,0/0,0"),
            new AuxAlg("J/-5", "/0,0/1,0/3,-3/3,0/-3,0/0,3/-3,0/-1,0"),
            new AuxAlg("J/-6", "/0,0/4,0/3,-3/3,0/-3,0/0,3/-3,0/-1,0"),
            new AuxAlg("J/-7", "/0,0/-2,0/3,-3/3,0/-3,0/0,3/-3,0/-1,0"),
            new AuxAlg("J/-8", "/0,0/-5,0/3,-3/3,0/-3,0/0,3/-3,0/-1,0"),
            new AuxAlg("-/J1", "/3,-3/0,3/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("-/J2", "/0,0/0,3/3,-3/0,3/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("-/J3", "/0,0/0,6/3,-3/0,3/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("-/J4", "/0,0/0,-3/3,-3/0,3/-3,0/3,0/-3,0/0,0"),
            new AuxAlg("-/J5", "/0,0/0,-1/3,-3/0,3/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("-/J6", "/0,0/0,-4/3,-3/0,3/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("-/J7", "/0,0/0,2/3,-3/0,3/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("-/J8", "/0,0/0,5/3,-3/0,3/-3,0/3,0/-3,0/0,1")
    };

    //private PBL[] allStandardPbl = allPbls();
    private ArrayList<SucessSearch> sucessSearches;

    private PBL targetPbl;

    public Finder(PBL targetPbl) {
        this.targetPbl = targetPbl;
        sucessSearches = new ArrayList<>();
    }

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
        for (AuxAlg a : Finder.AUX_ALGS) {
            for (AuxAlg b : Finder.AUX_ALGS) {

                String testSolveSeq = a.getSequence() + b.getSequence();
                squareOne.applyStringSequence(testSolveSeq);

                if (isSolved(squareOne)) {
                    long elapesedTime = System.currentTimeMillis() - i;
                    System.out.println("FOUND!!");

                    System.out.println("The sequence\n" +
                            testSolveSeq.replaceAll(" ", "") + "[" + a.getName() + "|" + b.getName() +
                            "]\nsolves the passed PBL!!");

                    System.out.println("Search time for single solution was " + elapesedTime + " miliseconds.");
                    return;
                } else {
                    squareOne.applyStringSequence(reversedSequence(testSolveSeq));
                }
            }
        }

        System.err.println("Couldn't find a sequence! :(");
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - i));
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

    public static PBL[] allPbls() {
        ArrayList<PBL> r = new ArrayList<>();
        int counter = 0;

        for (PLL top : Finder.STANDARD_PLLs) {
            for (PLL bottom : Finder.STANDARD_PLLs) {
                r.add(new PBL(counter + "", top, bottom));
                counter++;
            }
        }

        counter = 0;
        for (PLL top : Finder.PARITY_PLLs) {
            for (PLL bottom : Finder.PARITY_PLLs) {
                r.add(new PBL(counter + "*", top, bottom));
                counter++;
            }
        }

        return r.toArray(new PBL[r.size()]);
    }

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

    public PBL getTargetPbl() {
        return targetPbl;
    }

    public void setTargetPbl(PBL targetPbl) {
        this.targetPbl = targetPbl;
    }
}
