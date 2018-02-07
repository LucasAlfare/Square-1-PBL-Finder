package com.main.pbl;

import com.main.puzzle.Piece;
import com.main.puzzle.SquareOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Finder {

    private static final PLL[] STANDARD_PLLs = {
                    new PLL("Skip", "", false),
                    new PLL("Ua", "/3,0/1,0/0,-3/-1,0/-3,0/1,0/0,3/-1,0", false),
                    new PLL("Ub", "1,0/0,-3/-1,0/3,0/1,0/0,3/-1,0/-3,0/", false),
                    new PLL("Z", "1,0/-1,-1/-2,1/-1,-1/4,1/-1,-1/0,1", false),
                    new PLL("H", "1,0/-1,-1/-2,1/-1,-1/-5,1/-1,-1/-2,1/-1,-1/0,1", false),
                    new PLL("Aa", "1,0/-3,0/3,3/0,-3/2,0/-3,0/3,3/0,-3/", false),
                    new PLL("Ab", "/-3,0/3,3/0,-3/-2,0/-3,0/3,3/0,-3/-1,0", false),
                    new PLL("E", "/3,3/1,-2/2,2/-3,0/-3,-3/", false),
                    new PLL("F", "1,0/-1,-1/-3,0/3,0/1,4/-3,0/3,0/2,-4/-3,0/0,1*", false),
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
                    new PLL("V", "1,0/3,0/-4,-1/0,-3/3,0/1,-2/0,3/0,3/-4,-1/0,1*", false)
            };

    private static final PLL[] PARITY_PLLs = {
                    new PLL("Adj", "/-3,0/0,3/0,-3/0,3/2,0/0,2/-2,0/4,0/0,-2/0,2/-1,4/0,-3/*", true),
                    new PLL("Opp", "/3,3/1,0/-2,-2/2,0/2,2/0,-2/-1,-1/0,3/-3,-3/0,2/-2,-2/-1,0", true),
                    new PLL("Oa", "/3,3/1,0/-2,-2/-2,0/2,2/-1,0/-3,-3/1,0/2,2/0,1", true),
                    new PLL("Ob", "/3,3/1,0/-2,-2/2,0/2,2/-1,0/-3,-3/0,2/-2,-2/-1,0", true),
                    new PLL("W", "0,-1/1,-2/-4,0/0,3/1,0/3,-2/-4,0/-4,0/-2,2/-1,0/0,-3/*", true),
                    new PLL("M", "/3,0/1,2/2,0/-4,0/4,0/2,0/-1,-2/-3,0/-2,0/-4,2/4,-2/-1,0", true),
                    new PLL("pN", "/-3,0/0,-3/2,0/0,2/-2,0/4,0/0,-2/0,2/-4,1/3,0/*", true),
                    new PLL("pJ", "/-3,0/-3,0/-1,0/0,2/-2,0/4,0/0,-2/0,2/-1,4/0,-3/*", true),
                    new PLL("X", "/3,0/1,0/4,5/0,4/0,4/2,3/0,-1/-3,0/-4,0/1,-2/0,3/-1,0", true),
                    new PLL("Q", "1,0/5,0/3,3/1,-4/4,0/2,2/0,4/4,-1/3,3/-5,0/-1,0", true),
                    new PLL("Ka", "/3,3/5,0/0,2/-2,0/4,0/0,-2/0,2/-1,1/3,0/3,3/*", true),
                    new PLL("Kb", "1,0/5,0/-3,-3/0,3/4,0/-2,0/4,0/-4,0/-2,0/-1,0/-3,-3/-2,3/-1,0", true),
                    new PLL("Sa", "/-3,0/0,-1/-4,0/0,4/0,4/4,0/-4,0/1,-4/3,0/3,-3/*", true),
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

//    private static final AuxAlg[] AUX_ALGS = {
//                    new AuxAlg("N/N", "/3,-3/-3,3/"),
//                    new AuxAlg("pN/pN", "1,0/2,-4/-2,4/-1,0"),
//                    new AuxAlg("J/J", "/-3,0/3,3/0,-3/"),
//                    new AuxAlg("pJ/pJ", "1,0/-3,0/2,2/1,-2/-1,0"),
//                    new AuxAlg("J/N", "/0,3/0,-3/0,3/0,-3/"),
//                    new AuxAlg("N/J", "/3,0/-3,0/3,0/-3,0/"),
//                    new AuxAlg("opp/opp", "1,0/5,-1/-5,1/-1,0"),
//                    new AuxAlg("adj/adj", "1,0/0,3/-1,-1/1,-2/-1,0"),
//                    new AuxAlg("F/V", "0,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
//                    new AuxAlg("V/F", "1,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
//                    new AuxAlg("U/U", "1,0/5,-1/-3,0/1,1/-3,0/5,0"),
//                    new AuxAlg("U'/U'", "1,0/3,0/-1,-1/3,0/-5,1/5,0")
//            };

    private static final AuxAlg[] AUX_ALGS = {
            new AuxAlg("N/N1", "/3,-3/-3,3/"),
            new AuxAlg("N/N2", "1,0/3,-3/-3,3/-1,0"),
            new AuxAlg("N/N3", "0,-1/3,-3/-3,3/0,1"),
            new AuxAlg("N/N4", "1,-1/3,-3/-3,3/-1,1"),
            new AuxAlg("pN/pN1", "1,0/2,-4/-2,4/-1,0"),
            new AuxAlg("pN/pN2", "-2,0/2,-4/-2,4/2,0"),
            new AuxAlg("pN/pN3", "1,3/2,-4/-2,4/-1,0"),
            new AuxAlg("pN/pN4", "-2,3/2,-4/-2,4/2,0"),
            new AuxAlg("J/J1", "/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J2", "0,3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J3", "0,6/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J4", "0,-3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J5", "0,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J6", "0,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J7", "0,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J8", "0,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J9", "-3,0/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J10", "-3,3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J11", "-3,6/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J12", "-3,-3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J13", "-3,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J14", "-3,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J15", "-3,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J16", "-3,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J17", "6,0/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J18", "6,3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J19", "6,6/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J20", "6,-3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J21", "6,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J22", "6,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J23", "6,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J24", "6,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J25", "3,0/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J26", "3,3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J27", "3,6/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J28", "3,-3/-3,0/3,3/0,-3/"),
            new AuxAlg("J/J29", "3,-1/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J30", "3,2/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J31", "3,5/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J32", "3,-4/-3,0/3,3/0,-3/0,1"),
            new AuxAlg("J/J33", "1,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J34", "1,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J35", "1,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J36", "1,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J37", "1,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J38", "1,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J39", "1,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J40", "1,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J41", "-2,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J42", "-2,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J43", "-2,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J44", "-2,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J45", "-2,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J46", "-2,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J47", "-2,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J48", "-2,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J49", "-5,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J50", "-5,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J51", "-5,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J52", "-5,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J53", "-5,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J54", "-5,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J55", "-5,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J56", "-5,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J57", "4,0/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J58", "4,3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J59", "4,6/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J60", "4,-3/-3,0/3,3/0,-3/-1,0"),
            new AuxAlg("J/J61", "4,-1/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J62", "4,2/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J63", "4,5/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("J/J64", "4,-4/-3,0/3,3/0,-3/-1,1"),
            new AuxAlg("pJ/pJ1", "1,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ2", "1,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ3", "1,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ4", "1,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ5", "-2,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ6", "-2,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ7", "-2,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ8", "-2,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ9", "-5,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ10", "-5,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ11", "-5,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ12", "-5,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ13", "4,0/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ14", "4,3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ15", "4,6/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("pJ/pJ16", "4,-3/-3,0/2,2/1,-2/-1,0"),
            new AuxAlg("J/N1", "/0,3/0,-3/0,3/0,-3/"),
            new AuxAlg("J/N2", "0,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N3", "-3,0/0,3/0,-3/0,3/0,-3/"),
            new AuxAlg("J/N4", "-3,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N5", "6,0/0,3/0,-3/0,3/0,-3/"),
            new AuxAlg("J/N6", "6,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N7", "3,0/0,3/0,-3/0,3/0,-3/"),
            new AuxAlg("J/N8", "3,-1/0,3/0,-3/0,3/0,-3/0,1"),
            new AuxAlg("J/N9", "1,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N10", "1,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N11", "-2,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N12", "-2,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N13", "-5,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N14", "-5,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("J/N15", "4,0/0,3/0,-3/0,3/0,-3/-1,0"),
            new AuxAlg("J/N16", "4,-1/0,3/0,-3/0,3/0,-3/-1,1"),
            new AuxAlg("N/J1", "/3,0/-3,0/3,0/-3,0/"),
            new AuxAlg("N/J2", "1,0/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J3", "0,3/3,0/-3,0/3,0/-3,0/"),
            new AuxAlg("N/J4", "1,3/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J5", "0,6/3,0/-3,0/3,0/-3,0/"),
            new AuxAlg("N/J6", "1,6/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J7", "0,-3/3,0/-3,0/3,0/-3,0/"),
            new AuxAlg("N/J8", "1,-3/3,0/-3,0/3,0/-3,0/-1,0"),
            new AuxAlg("N/J9", "0,-1/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J10", "1,-1/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J11", "0,2/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J12", "1,2/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J13", "0,5/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J14", "1,5/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("N/J15", "0,-4/3,0/-3,0/3,0/-3,0/0,1"),
            new AuxAlg("N/J16", "1,-4/3,0/-3,0/3,0/-3,0/-1,1"),
            new AuxAlg("opp/opp1", "1,0/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp2", "4,0/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp3", "1,3/5,-1/-5,1/-1,0"),
            new AuxAlg("opp/opp4", "4,3/5,-1/-5,1/-1,0"),
            new AuxAlg("adj/adj1", "1,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj2", "1,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj3", "1,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj4", "1,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj5", "-2,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj6", "-2,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj7", "-2,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj8", "-2,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj9", "-5,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj10", "-5,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj11", "-5,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj12", "-5,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj13", "4,0/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj14", "4,3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj15", "4,6/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("adj/adj16", "4,-3/0,3/-1,-1/1,-2/-1,0"),
            new AuxAlg("F/V1", "0,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V2", "3,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V3", "6,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V4", "-3,-1/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V5", "0,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V6", "3,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V7", "6,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V8", "-3,-4/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V9", "0,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V10", "3,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V11", "6,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V12", "-3,2/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V13", "0,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V14", "3,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V15", "6,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("F/V16", "-3,5/1,-2/-1,2/1,-2/-1,2/0,1"),
            new AuxAlg("V/F1", "1,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F2", "1,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F3", "1,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F4", "1,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F5", "4,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F6", "4,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F7", "4,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F8", "4,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F9", "-2,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F10", "-2,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F11", "-2,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F12", "-2,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F13", "-5,0/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F14", "-5,3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F15", "-5,6/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("V/F16", "-5,-3/2,-1/-2,1/2,-1/-2,1/-1,0"),
            new AuxAlg("U/U1", "1,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U2", "1,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U3", "1,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U4", "1,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U5", "4,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U6", "4,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U7", "4,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U8", "4,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U9", "-2,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U10", "-2,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U11", "-2,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U12", "-2,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U13", "-5,0/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U14", "-5,3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U15", "-5,6/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U/U16", "-5,-3/5,-1/-3,0/1,1/-3,0/5,0"),
            new AuxAlg("U'/U'1", "1,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'2", "1,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'3", "1,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'4", "1,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'5", "4,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'6", "4,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'7", "4,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'8", "4,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'9", "-2,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'10", "-2,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'11", "-2,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'12", "-2,-3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'13", "-5,0/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'14", "-5,3/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'15", "-5,6/3,0/-1,-1/3,0/-5,1/5,0"),
            new AuxAlg("U'/U'16", "-5,-3/3,0/-1,-1/3,0/-5,1/5,0")
    };

    //private PBL[] allStandardPbl = allPbls();

    private PBL targetPbl;

    public Finder(PBL targetPbl) {
        this.targetPbl = targetPbl;
    }

    public void check(){
        System.out.println("Aimed PBL: " + targetPbl);

        System.out.println("Top setup:    " + algorithmToSetupSequence(targetPbl.getTopPLL().getSequence()));
        System.out.println("Bottom setup: " + algorithmToSetupSequence(targetPbl.getBottomPLL().sequenceAtBottom()));
        System.out.println();

        SquareOne squareOne = new SquareOne();
        squareOne.applyStringSequence(algorithmToSetupSequence(targetPbl.getTopPLL().getSequence()));
        squareOne.applyStringSequence(algorithmToSetupSequence(targetPbl.getBottomPLL().sequenceAtBottom()));

        System.out.println("PBL " + targetPbl.getName() + " applied! Square-1 view:");
        System.out.println(squareOne);

        for (AuxAlg a : Finder.AUX_ALGS){

        }
    }

    private String algorithmToSetupSequence(String algorithm) {
        ArrayList<String> hold = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>(Arrays.asList(algorithm.split("/")));

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

    public static PBL[] allPbls(){
        ArrayList<PBL> r = new ArrayList<>();
        int counter = 0;

        for (PLL top : Finder.STANDARD_PLLs){
            for (PLL bottom : Finder.STANDARD_PLLs){
                r.add(new PBL(counter + "", top, bottom));
                counter++;
            }
        }

        counter = 0;
        for (PLL top : Finder.PARITY_PLLs){
            for (PLL bottom : Finder.PARITY_PLLs){
                r.add(new PBL(counter + "*", top, bottom));
            }
        }

        return r.toArray(new PBL[r.size()]);
    }

    private boolean isSolved(SquareOne targetSquare){
        SquareOne aSolvedSquareOne = new SquareOne();
        aSolvedSquareOne.setColorScheme(targetSquare.getColorScheme());

        boolean currentEquals = false;

        ArrayList<Piece> topTop= new ArrayList<>();
        topTop.addAll(Arrays.asList(aSolvedSquareOne.getTopPieces()));
        topTop.addAll(Arrays.asList(aSolvedSquareOne.getTopPieces()));
        Piece[] targetTop = targetSquare.getTopPieces();

        for (int i = 0; i < topTop.size() - targetTop.length; i++){
            for (int j = 0; j < targetTop.length; j++) {
                currentEquals = Arrays.equals(targetTop[j].getColors(), topTop.get(i + j).getColors());
                if (!currentEquals){
                    break;
                }
            }
            if (currentEquals){
                break;
            }
        }

        if (currentEquals){
            ArrayList<Piece> bottomBottom = new ArrayList<>();
            bottomBottom.addAll(Arrays.asList(aSolvedSquareOne.getBottomPieces()));
            bottomBottom.addAll(Arrays.asList(aSolvedSquareOne.getBottomPieces()));
            Piece[] targetBottom = targetSquare.getBottomPieces();

            for (int i = 0; i < bottomBottom.size() - targetBottom.length; i++){
                for (int j = 0; j < targetBottom.length; j++) {
                    currentEquals = Arrays.equals(targetBottom[j].getColors(), bottomBottom.get(i + j).getColors());
                    if (!currentEquals){
                        break;
                    }
                }
                if (currentEquals){
                    break;
                }
            }
        }

        return currentEquals;
    }

//    public PBL getTargetPbl() {
//        return targetPbl;
//    }
//
//    public void setTargetPbl(PBL targetPbl) {
//        this.targetPbl = targetPbl;
//    }
}
