import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Samurdhi on 2016-09-12.
 */
public class TicTacToeController {


    private TicTacToeModel tm;

    private TicTacToeView tv;

    public TicTacToeController(){
        tm=new TicTacToeModel(this);
        tv=new TicTacToeView("TicTacToe");
    }


    public void handleClick(TButton b){
        tm.numclicks++;

        b.clicked=true;
        if(tm.numclicks%2==1){
            b.isCross=true;
            b.setText("X");
        }
        else{
            b.setText("O");
            b.isCross=false;
        }
        b.removeActionListener(b);

    }

    public void nextStep(){
        int ret;
        ret=process();
        if(ret!=-1) tv.displayResult(tm,ret);
        else if(tm.numclicks==9){
            tv.displayResult(tm,2);
        }
    }

    public int process(){
        int[][] status=new int[3][3];
        for(int i=0;i<3;i++) for(int j=0;j<3;j++) status[i][j]=-1;

        for(int i=0;i<9;i++){
            if(tm.grid[i].clicked) status[i/3][i%3]=tm.grid[i].isCross?1:0;
        }

        for(int i=0;i<3;i++){
            boolean win=true;
            for(int j=0;j<3;j++){
                if(status[j][i]==-1){
                    win=false;
                    break;
                }
                else{
                    if(j>0) win&=(status[j][i]==status[j-1][i]);
                }
            }
            if(win) return status[0][i];
        }

        for(int i=0;i<3;i++){
            boolean win=true;
            for(int j=0;j<3;j++){
                if(status[i][j]==-1){
                    win=false;
                    break;
                }
                else{
                    if(j>0) win&=(status[i][j]==status[i][j-1]);
                }
            }
            if(win) return status[i][0];
        }

        if(status[0][0]==status[1][1] && status[1][1]==status[2][2] && status[0][0]!=-1) return status[0][0];

        if(status[0][2]==status[1][1] && status[1][1]==status[2][0] && status[0][2]!=-1) return status[0][2];


        return -1;
    }

    public void handleNextMove(){
        tm.numclicks++;
        int ret=nextMove();
        if(ret==-1) return;
        tm.grid[ret].clicked=true;
        tm.grid[ret].setText("O");
        tm.grid[ret].isCross=false;
        tm.grid[ret].removeActionListener(tm.grid[ret]);
    }

    public int nextMove(){

        int[][] status=new int[3][3];
        for(int i=0;i<3;i++) for(int j=0;j<3;j++) status[i][j]=-1;

        int ret=-1;

        for(int i=0;i<9;i++){
            if(tm.grid[i].clicked) status[i/3][i%3]=tm.grid[i].isCross?1:0;
        }

        for(int i=0;i<3;i++){
            int nO=0,nX=0;
            for(int j=0;j<3;j++){
                if(status[j][i]!=-1){
                    if(status[j][i]==0) nO++;
                    else nX++;
                }
            }

            int s=nX+nO;

            if(nO==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][i]==-1){
                        return 3*j+i;
                    }
                }
            }
            else if(nX==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][i]==-1){
                        ret=3*j+i;
                    }
                }
            }
        }

        for(int i=0;i<3;i++){
            int nO=0,nX=0;
            for(int j=0;j<3;j++){
                if(status[i][j]!=-1){
                    if(status[i][j]==0) nO++;
                    else nX++;
                }
            }

            int s=nX+nO;

            if(nO==2 && s<3 ){
                for(int j=0;j<3;j++){
                    if(status[i][j]==-1){
                        return 3*i+j;
                    }
                }
            }
            else if(nX==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[i][j]==-1){
                        ret=3*i+j;
                    }
                }
            }
        }

        {
            int nO = 0, nX = 0;
            for (int i = 0; i < 3; i++) {

                if (status[i][i] != -1) {
                    if (status[i][i] == 0) nO++;
                    else nX++;
                }
            }
            int s=nX+nO;
            if(nO==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][j]==-1){
                        return 3*j+j;
                    }
                }
            }
            else if(nX==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][j]==-1){
                        //System.out.println("Diagonal");
                        ret=3*j+j;
                    }
                }
            }


        }

        {
            int nO = 0, nX = 0;
            for (int i = 0; i < 3; i++) {

                if (status[i][2-i] != -1) {
                    if (status[i][2-i] == 0) nO++;
                    else nX++;
                }
            }
            int s=nX+nO;
            if(nO==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][2-j]==-1){
                        return 3*j+2-j;
                    }
                }
            }

            else if(nX==2 && s<3){
                for(int j=0;j<3;j++){
                    if(status[j][2-j]==-1){
                        ret=3*j+2-j;
                    }
                }
            }

        }

        if(ret!=-1) return ret;

        ArrayList<Integer> al=new ArrayList<Integer>();


        for(int i=0;i<9;i++) if(!tm.grid[i].clicked) al.add(i);

        if(al.size()==0) return -1;


        return  al.get((int )(Math.random() * al.size() + 1)-1);

    }

    public void start(){
        tv.createAndShowGUI(tm);
    }

    public void restart(){
        tm=new TicTacToeModel(this);
        tv.refereshGUI(tm);
    }

}
