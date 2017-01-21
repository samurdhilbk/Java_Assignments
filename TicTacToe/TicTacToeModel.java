import java.awt.*;
import java.util.Arrays;

/**
 * Created by Samurdhi on 2016-09-12.
 */
public class TicTacToeModel {

    private static final int RESTART_BUTTON=0;
    private static final int GRID_BUTTON=1;

    public int numclicks;
    public TicTacToeController tc;

    TButton[] grid;

    public TicTacToeModel(TicTacToeController tc){
        numclicks=0;
        grid=new TButton[9];
        this.tc=tc;
        for(int i=0;i<9;i++){
            grid[i]=new TButton(this.tc,GRID_BUTTON);
            grid[i].setBackground(new Color(59, 89, 182));
            grid[i].setForeground(Color.WHITE);
            grid[i].setFocusPainted(false);
            grid[i].setFont(new Font("Tahoma", Font.BOLD, 50));
        }
    }


}
