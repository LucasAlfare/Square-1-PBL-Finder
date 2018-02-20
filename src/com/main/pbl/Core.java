package com.main.pbl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Core {

    private Gui gui;
    private ArrayList<AuxAlg> currAuxAlgs;

    Core(Gui gui) {
        this.gui = gui;

        this.init();
    }

    private void init() {
        ArrayList<String> topPlls = new ArrayList<>();
        ArrayList<String> bottomPlls = new ArrayList<>();
        ArrayList<String> auxAuxAlgs = new ArrayList<>();

        for (PLL x : AlgTemplates.STANDARD_PLLs) {
            topPlls.add(x.getName());
            bottomPlls.add(x.getName());
        }

        for (PLL x : AlgTemplates.PARITY_PLLs) {
            topPlls.add(x.getName());
            bottomPlls.add(x.getName());
        }

        currAuxAlgs = Main.fileToAuxAlgs();
        assert currAuxAlgs != null;
        for (AuxAlg x : currAuxAlgs) {
            auxAuxAlgs.add(x.toString());
        }

        refreshList(gui.getTopPllList(), topPlls.toArray(new String[topPlls.size()]));
        refreshList(gui.getBottomPllList(), bottomPlls.toArray(new String[bottomPlls.size()]));
        refreshList(gui.getAuxiliarAlgorithmsList(), auxAuxAlgs.toArray(new String[auxAuxAlgs.size()]));

        gui.getFindSolutionsBt().addActionListener(e -> {
            if (gui.getTopPllList().getSelectedIndex() != -1 && gui.getBottomPllList().getSelectedIndex() != -1){
                final PBL userPBL = new PBL(
                        gui.getTopPllList().getSelectedValue() + "/" + gui.getBottomPllList().getSelectedValue(),
                        AlgTemplates.getPllByName(gui.getTopPllList().getSelectedValue()),
                        AlgTemplates.getPllByName(gui.getBottomPllList().getSelectedValue())
                );
                Finder finder = new Finder(userPBL);

                Thread a = new Thread(() -> {
                    while (true){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }

                        gui.getRemoveAlgorithmBt().setEnabled(!finder.isSearching());
                        gui.getClearBt().setEnabled(!finder.isSearching());
                        gui.getAddAlgorithmBt().setEnabled(!finder.isSearching());
                        gui.getFindSolutionsBt().setEnabled(!finder.isSearching());

                        gui.getLogLabel().setText(finder.isSearching() ? "searching..." : "finished");
                        gui.getLogLabel().setForeground(finder.isSearching() ? Color.RED : Color.GREEN);

                        if (!finder.isSearching()){
                            JOptionPane.showMessageDialog(gui,
                                    "Found " + finder.getSucessSearches().size() +
                                            " solutions in " + finder.getElapsedTime() + " miliseconds!");

                            gui.getOutputArea().setText("");
                            gui.getOutputArea().append(finder.getSetups());
                            gui.getOutputArea().append("Found " + finder.getSucessSearches().size() +
                                    " solutions in " + finder.getElapsedTime() + " milliseconds!\n\n\n\n");

                            finder.getSucessSearches().sort(Comparator.comparing(SucessSearch::getSequenceTwistMetricLenght));

                            for (SucessSearch x : finder.getSucessSearches()){
                                gui.getOutputArea().append(x + "\n\n");
                            }

                            break;
                        }
                    }
                });

                Thread b = new Thread(finder::search);

                //primeiro inicia a thread de atualizar a UI (sleep 200ms)
                a.start();

                //depois começa a procuar
                b.start();
            } else {
                JOptionPane.showMessageDialog(gui, "SELECT THE PLLs PROPERLY!!");
            }
        });

        gui.getClearBt().addActionListener(e -> gui.getOutputArea().setText("- -"));

        gui.getAddAlgorithmBt().addActionListener(e -> {
            if (!gui.getNewAlgorithmField().getText().equals("")){
                auxAuxAlgs.clear();
                String[] userAlg = gui.getNewAlgorithmField().getText()
                        .replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").split("@");
                currAuxAlgs.add(new AuxAlg(userAlg[0], userAlg[1]));

                for (AuxAlg x : currAuxAlgs) {
                    auxAuxAlgs.add(x.toString());
                }

                refreshList(gui.getAuxiliarAlgorithmsList(), auxAuxAlgs.toArray(new String[auxAuxAlgs.size()]));

                new Thread(() -> Main.auxAlgsToFile(currAuxAlgs)).start();
                getGui().getLogLabel().setText("aux alg was saved in the main file table.");
            } else {
                JOptionPane.showMessageDialog(gui, "PLEASE, INPUT SEQ ON THE FORMAT: 'name,alg'");
            }
        });

        gui.getRemoveAlgorithmBt().addActionListener(e -> {
            int selectedIndext = gui.getAuxiliarAlgorithmsList().getSelectedIndex();

            if (selectedIndext != -1){
                currAuxAlgs.remove(selectedIndext);
                auxAuxAlgs.clear();
                for (AuxAlg x : currAuxAlgs) {
                    auxAuxAlgs.add(x.toString());
                }
                refreshList(gui.getAuxiliarAlgorithmsList(), auxAuxAlgs.toArray(new String[auxAuxAlgs.size()]));

                new Thread(() -> Main.auxAlgsToFile(currAuxAlgs)).start();
                getGui().getLogLabel().setText("aux alg removed from main file table.");
            } else {
                JOptionPane.showMessageDialog(gui, "YOU SHOULD SELECT A ITEM TO REMOVE!!");
            }
        });

        gui.getSalvarAuxAlgsMenu().addActionListener(e -> {
            Main.auxAlgsToFile(currAuxAlgs);
            getGui().getLogLabel().setText("Table SAVED.");
            JOptionPane.showMessageDialog(gui, "Table SAVED!");
        });
    }

    private void refreshList(JList<String> target, String[] content) {
        target.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return content.length;
            }

            @Override
            public String getElementAt(int index) {
                return content[index];
            }
        });
    }

    public Gui getGui() {
        return gui;
    }
}