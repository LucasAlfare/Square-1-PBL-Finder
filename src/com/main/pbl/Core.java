package com.main.pbl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Core {

    private Gui gui;

    public Core(Gui gui) {
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

        for (AuxAlg x : AlgTemplates.AUX_ALGS) {
            auxAuxAlgs.add(x.toString());
        }

        refreshList(gui.getTopPllList(), topPlls.toArray(new String[topPlls.size()]));
        refreshList(gui.getBottomPllList(), bottomPlls.toArray(new String[bottomPlls.size()]));
        refreshList(gui.getAuxiliarAlgorithmsList(), auxAuxAlgs.toArray(new String[auxAuxAlgs.size()]));

        gui.getFindSolutionsBt().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("OOOOIIIII");

                final PBL userPBL = new PBL(
                        gui.getTopPllList().getSelectedValue() + "/" + gui.getBottomPllList().getSelectedValue(),
                        AlgTemplates.getPllByName(gui.getTopPllList().getSelectedValue()),
                        AlgTemplates.getPllByName(gui.getBottomPllList().getSelectedValue())
                );

                Finder finder = new Finder(userPBL);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        finder.search();

                        for (SucessSearch s : finder.getSucessSearches()) {
                            gui.getOutputArea().append(s.toString() + "\n\n");
                        }
                    }
                });

                try {
                    thread.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                thread.start();
            }
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

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}