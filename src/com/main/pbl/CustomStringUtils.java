package com.main.pbl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CustomStringUtils {

    public static String otimizedSequence(String velha) {
        ArrayList<String> aux = new ArrayList<>(Arrays.asList(velha.replaceAll(" ", "").split("/")));
        //remove os itens vazios..
        aux.removeAll(Collections.singletonList(""));

        int indexOfZeros = Collections.indexOfSubList(aux, Collections.singletonList("0,0"));

        if (indexOfZeros != -1){
            if (indexOfZeros >= 1 && indexOfZeros < aux.size() - 1){ //pelo meio...

                String[] previousPair = aux.get(indexOfZeros - 1).split(",");
                String[] nextPair = aux.get(indexOfZeros + 1).split(",");

                //remove os pares velhos
                aux.remove(indexOfZeros - 1);
                aux.remove(indexOfZeros);
                aux.remove(indexOfZeros - 1);

                int a = (Integer.parseInt(previousPair[0]) + Integer.parseInt(nextPair[0]));
                int b = (Integer.parseInt(previousPair[1]) + Integer.parseInt(nextPair[1]));

                int x = a > 6 ? ((12 - a) * -1) : (a < 0 ? (a < -6 ? 12 - a : a) : a);
                int y = b > 6 ? ((12 - b) * -1) : (b < 0 ? (b < -6 ? 12 - b : b) : b);

                //a+c,b+d
                String novoPar = x + "," + y;
                aux.add(indexOfZeros - 1, novoPar);

                return otimizedSequence(strListToSequence(aux, velha));
            }
            else if (indexOfZeros == 0){ //no começo..
                aux.remove(0);
                return otimizedSequence(strListToSequence(aux, velha.replaceFirst("/", "")));
            } else { //no fim...
                aux.remove(aux.size() - 1);
                return otimizedSequence(strListToSequence(aux, velha));
            }
        }  else {
            return strListToSequence(aux, velha);
        }
    }

    private static String strListToSequence(ArrayList<String> strings, String original){
        ArrayList<String> hold = new ArrayList<>();

        for (String x : strings) {
            String[] aux2 = x.split(",");
            if (!x.equals("")) {
                hold.add((Integer.parseInt(aux2[0])) + "," + (Integer.parseInt(aux2[1])));
            }
        }

        //cleans toString list
        String r = hold.toString().replaceAll(", ", "/").replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");

        //re-adds twists
        if (original.startsWith("/")) r = "/" + r;
        if (original.endsWith("/")) r += "/";

        return r;
    }

    /**
     * Reverses a square-1 sequence.
     * Example:
     * original ->  (1,-3)/
     * out      -> /(-1,3)
     *
     * @param algorithm original sequence do be reversed.
     * @return a reversed string from original param.
     */
    public static String reversedSequence(String algorithm) {
        ArrayList<String> hold = new ArrayList<>();
        //cleans and splits
        ArrayList<String> aux = new ArrayList<>(Arrays.asList(algorithm.replaceAll(" ", "").split("/")));

        //reverses
        Collections.reverse(aux);

        //inverses
        for (String x : aux) {
            String[] aux2 = x.split(",");
            if (!x.equals("")) {
                hold.add((Integer.parseInt(aux2[0]) * -1) + "," + (Integer.parseInt(aux2[1]) * -1));
            }
        }

        //cleans toString list
        String r = hold.toString().replaceAll(", ", "/").replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");

        //readds twists
        if (algorithm.startsWith("/")) r += "/";
        if (algorithm.endsWith("/")) r = "/" + r;

        return r;
    }
}
