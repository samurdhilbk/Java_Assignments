/**
 * Created by Samurdhi on 2016-09-12.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class TicTacToeView extends JFrame {

    private static final int RESTART_BUTTON=0;
    private static final int GRID_BUTTON=1;

    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton applyButton = new JButton("Apply gaps");
    GridLayout experimentLayout = new GridLayout(3,3);
    JPanel compsToExperiment;
    TicTacToeView frame;



    public TicTacToeView(String name) {
        super(name);
        setResizable(false);
    }


    public void addComponentsToPane(final Container pane,TicTacToeModel tm) {
        compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);

        compsToExperiment.setPreferredSize(new Dimension(500,500));
        compsToExperiment.setMinimumSize(new Dimension(500,500));
        compsToExperiment.setMaximumSize(new Dimension(500,500));

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1,1));




        for(int i=0;i<9;i++){
            compsToExperiment.add(tm.grid[i]);
        }



        controls.add(new TButton("Restart",tm.tc,RESTART_BUTTON));


        pane.add(compsToExperiment, BorderLayout.NORTH);

        JSeparator js=new JSeparator();
        js.setPreferredSize(new Dimension(100,15));
        pane.add(js, BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);

    }


    public void createAndShowGUI(TicTacToeModel tm) {
        try {

            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            System.err.println(ex);
        }

        //UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Create and set up the window.
        frame = new TicTacToeView("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane(),tm);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void refereshGUI(TicTacToeModel tm){

        frame.getContentPane().removeAll();
        frame.addComponentsToPane(frame.getContentPane(),tm);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void displayResult(TicTacToeModel tm,int num){

        frame.getContentPane().removeAll();
        compsToExperiment=new JPanel();
        compsToExperiment.setPreferredSize(new Dimension(500,500));
        compsToExperiment.setMinimumSize(new Dimension(500,500));
        compsToExperiment.setMaximumSize(new Dimension(500,500));
        JLabel jl=new JLabel();
        if(num==0) jl.setText("Player O Wins!");
        else if(num==1) jl.setText("Player X Wins!");
        else if(num==2) jl.setText("DRAW!");
        jl.setFont(new Font("Tahoma", Font.BOLD, 50));
        compsToExperiment.add(jl);
        frame.getContentPane().add(compsToExperiment, BorderLayout.NORTH);
        JSeparator js=new JSeparator();
        js.setPreferredSize(new Dimension(100,15));
        frame.getContentPane().add(js, BorderLayout.CENTER);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1,1));
        controls.add(new TButton("Restart",tm.tc,RESTART_BUTTON));
        frame.getContentPane().add(controls, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

}

