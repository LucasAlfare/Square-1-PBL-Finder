package com.main.pbl;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "auxAlgs_table.txt";

    public static void main(String[] args) {
        String log = "ready.";
        Path path = FileSystems.getDefault().getPath(FILE_NAME);
        File file = new File(path.toUri());

        if (!file.exists() && !file.isDirectory()){
            auxAlgsToFile(AlgTemplates.AUX_ALGS);
            log = "Auxiliary algorithms table created (first time).";
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        String finalLog = log;
        EventQueue.invokeLater(() -> {
            Core core = new Core(new Gui());
            core.getGui().getLogLabel().setText(finalLog);
            core.getGui().setVisible(true);
        });
    }

    public static ArrayList<AuxAlg> fileToAuxAlgs(){
        Path path = FileSystems.getDefault().getPath(FILE_NAME);
        File file = new File(path.toUri());

        if (file.exists() && !file.isDirectory()){
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<AuxAlg> auxAlgs = new ArrayList<>();

                while (scanner.hasNext()){
                    String[] currItem = scanner.nextLine().split(":");

                    String currName =
                            currItem[0].replaceAll(":", "").replaceAll(" ", "");
                    String currSequence =
                            currItem[1].replaceAll(":", "").replaceAll(" ", "").replaceAll("\\[", "").replaceAll("]", "");

                    AuxAlg curr = new AuxAlg(currName, currSequence);
                    auxAlgs.add(curr);
                }

                return auxAlgs;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void auxAlgsToFile(ArrayList<AuxAlg> source){
        Path path = FileSystems.getDefault().getPath(FILE_NAME);
        File file = new File(path.toUri());

        if (!file.isDirectory()){
            try {
                ArrayList<String> output = new ArrayList<>();

                for (AuxAlg x : source){
                    output.add(x.toString());
                }

                Files.write(path, output, Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
