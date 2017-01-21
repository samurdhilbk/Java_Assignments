/**
 * Created by Samurdhi on 2016-09-12.
 */



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TButton extends JButton implements ActionListener{

    public boolean clicked;
    public boolean isCross;
    private TicTacToeController tc;
    private static final int RESTART_BUTTON=0;
    private static final int GRID_BUTTON=1;

    private int type;

    public TButton(String title,TicTacToeController tc,int type){
        super(title);
        clicked=false;
        this.addActionListener(this);
        this.tc=tc;
        this.type=type;
    }

    public TButton(TicTacToeController tc,int type){
        this("",tc,type);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(this.type==RESTART_BUTTON){
            tc.restart();
            return;
        }

        tc.handleClick(this);
        Timer t1=new Timer(500, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                tc.nextStep();
                tc.handleNextMove();
            }
        });

        t1.setRepeats(false);
        t1.start();



        Timer t3=new Timer(2000, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                tc.nextStep();
            }
        });

        t3.setRepeats(false);
        t3.start();
    }

}
