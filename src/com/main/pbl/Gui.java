package com.main.pbl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lucas
 *
 * UI.
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    Gui() {
        initComponents();

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Simple square-1 PBL finder v1.2 by Anuar and Lucas");
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        topPllList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        bottomPllList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        auxiliarAlgorithmsList = new javax.swing.JList<>();
        newAlgorithmField = new javax.swing.JTextField();
        addAlgorithmBt = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        findSolutionsBt = new javax.swing.JButton();
        clearBt = new javax.swing.JButton();
        logLabel = new javax.swing.JLabel();
        removeAlgorithmBt = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        sairMenu = new javax.swing.JMenuItem();
        salvarAuxAlgsMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Top PLL"));

        topPllList.setToolTipText("This is the PLL case from the square-1 top.");
        jScrollPane1.setViewportView(topPllList);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bottom PLL"));

        bottomPllList.setToolTipText("This is the PLL from the square-1 bottom.");
        jScrollPane2.setViewportView(bottomPllList);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Auxiliary Algorithms"));

        auxiliarAlgorithmsList.setToolTipText("These algorithms are used to find and construct the solutions for the PBL case you defined (PBL = topPLL / bottomPLL).\n\nIt is important to remember that the more algorithms you set, the longer the solutions will take to be found.");
        jScrollPane3.setViewportView(auxiliarAlgorithmsList);

        newAlgorithmField.setText("name@sequence");
        newAlgorithmField.setToolTipText("new auxiliary algorithm to be added.");
        newAlgorithmField.setBorder(javax.swing.BorderFactory.createTitledBorder("New auxiliary algorithm:"));

        addAlgorithmBt.setText("Add");
        addAlgorithmBt.setToolTipText("adds the typed algorithm to the list");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));

        outputArea.setColumns(20);
        outputArea.setRows(5);
        outputArea.setText("- -");
        outputArea.setToolTipText("The main output of the program.");
        jScrollPane4.setViewportView(outputArea);

        findSolutionsBt.setText("Find solutions");
        findSolutionsBt.setToolTipText("Starts searching for solutions to the PBL case.");

        clearBt.setText("clear");
        clearBt.setToolTipText("clears the output console.");

        logLabel.setText("log");

        removeAlgorithmBt.setText("Remove");
        removeAlgorithmBt.setToolTipText("removes the selected algorithm from the list");

        jMenu1.setText("File");

        salvarAuxAlgsMenu.setText("Save alx algs (current program folder)");

        sairMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        sairMenu.setText("Exit");
        sairMenu.addActionListener(e -> System.exit(0));

        JMenu jMenu2 = new JMenu();
        jMenu2.setText("Help");

        JMenuItem aboutMenu = new JMenuItem();
        aboutMenu.setText("About...");
        aboutMenu.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setTitle("PBL Finder Info");
            frame.setSize(400, 200);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(Gui.this);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            MultiLineLabel about = new MultiLineLabel();
            about.setUI(MultiLineLabelUI.labelUI);
            about.setText("This program is used to find solutions to most PBL cases using two of the auxiliary algorithms. The ideia came from Jayden McNeill's post from the sq-1ers facebook group and most of the auxiliary algorithms are from the Sub 6 PBL list (goo.gl/8bWUcP), made by Charlie Stark.\n\n\tThe program was developed by Anuar Onofre and Lucas Sousa to be used as a cool tool to find easy advanced algorithms.");
            frame.add(about);

            EventQueue.invokeLater(() -> frame.setVisible(true));
        });

        jMenu1.add(salvarAuxAlgsMenu);
        jMenu1.add(sairMenu);

        jMenu2.add(aboutMenu);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(findSolutionsBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(newAlgorithmField, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addAlgorithmBt)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(removeAlgorithmBt, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(clearBt)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logLabel)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(newAlgorithmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addAlgorithmBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(removeAlgorithmBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(findSolutionsBt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clearBt)
                                        .addComponent(logLabel)))
        );

        pack();
    }

    private javax.swing.JButton addAlgorithmBt;
    private javax.swing.JList<String> auxiliarAlgorithmsList;
    private javax.swing.JList<String> bottomPllList;
    private javax.swing.JButton clearBt;
    private javax.swing.JButton findSolutionsBt;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextField newAlgorithmField;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton removeAlgorithmBt;
    private javax.swing.JMenuItem sairMenu;
    private javax.swing.JMenuItem salvarAuxAlgsMenu;
    private javax.swing.JList<String> topPllList;

    public JButton getAddAlgorithmBt() {
        return addAlgorithmBt;
    }

    public void setAddAlgorithmBt(JButton addAlgorithmBt) {
        this.addAlgorithmBt = addAlgorithmBt;
    }

    public JList<String> getAuxiliarAlgorithmsList() {
        return auxiliarAlgorithmsList;
    }

    public void setAuxiliarAlgorithmsList(JList<String> auxiliarAlgorithmsList) {
        this.auxiliarAlgorithmsList = auxiliarAlgorithmsList;
    }

    public JList<String> getBottomPllList() {
        return bottomPllList;
    }

    public void setBottomPllList(JList<String> bottomPllList) {
        this.bottomPllList = bottomPllList;
    }

    public JButton getClearBt() {
        return clearBt;
    }

    public void setClearBt(JButton clearBt) {
        this.clearBt = clearBt;
    }

    public JButton getFindSolutionsBt() {
        return findSolutionsBt;
    }

    public void setFindSolutionsBt(JButton findSolutionsBt) {
        this.findSolutionsBt = findSolutionsBt;
    }

    public JLabel getLogLabel() {
        return logLabel;
    }

    public void setLogLabel(JLabel logLabel) {
        this.logLabel = logLabel;
    }

    public JTextField getNewAlgorithmField() {
        return newAlgorithmField;
    }

    public void setNewAlgorithmField(JTextField newAlgorithmField) {
        this.newAlgorithmField = newAlgorithmField;
    }

    public JTextArea getOutputArea() {
        return outputArea;
    }

    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public JButton getRemoveAlgorithmBt() {
        return removeAlgorithmBt;
    }

    public void setRemoveAlgorithmBt(JButton removeAlgorithmBt) {
        this.removeAlgorithmBt = removeAlgorithmBt;
    }

    public JMenuItem getSalvarAuxAlgsMenu() {
        return salvarAuxAlgsMenu;
    }

    public void setSalvarAuxAlgsMenu(JMenuItem salvarAuxAlgsMenu) {
        this.salvarAuxAlgsMenu = salvarAuxAlgsMenu;
    }

    public JMenuItem getSairMenu() {
        return sairMenu;
    }

    public void setSairMenu(JMenuItem sairMenu) {
        this.sairMenu = sairMenu;
    }

    public JList<String> getTopPllList() {
        return topPllList;
    }

    public void setTopPllList(JList<String> topPllList) {
        this.topPllList = topPllList;
    }
}
