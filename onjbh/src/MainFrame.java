import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    int a = 0;
    ArrayList<Gamebutton> gbs = new ArrayList<>(9);
    int[][] game = new int[3][3];
    JButton button1 = new JButton("-");
    private boolean checmate(int a){
        for(int i = 0; i < 3; i++){
            int col = 0;
            for(int j = 0; j < 3; j++){
                if(game[i][j] == a) col++;
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
            if(col == 3) return true;
            col = 0;
            for(int j = 0; j < 3; j++){
                if(game[j][i] == a) col++;
            }
            if(col == 3) return true;
        }
        int col2 = 0;
        for(int i = 0; i < 3; i++){
            if(game[i][i] == a) col2++;
        }
        if(col2 == 3) return true;
        col2 = 0;
        for(int i = 0; i < 3; i++){
            if(game[i][2 - i] == a) col2++;
        }
        if(col2 == 3) return true;
        return false;
    }
    private void replay(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                game[i][j] = -1;
            }
        }
        for(int i = 0; i < 9; i++){
            gbs.get(i).setIcon(null);
            gbs.get(i).setEnabled(true);
        }
    }
    MainFrame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                game[i][j] = -1;
            }
        }
        setSize(900, 900);
        setLocationRelativeTo(null);
        Image icon = getToolkit().getImage("src/images/png-transparent-computer-icons-user-blog-staff-miscellaneous-monochrome-black.png");
        setIconImage(icon);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
        for(int i = 0; i < 9; i++){
            gbs.add(new Gamebutton());
            gbs.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() instanceof Gamebutton && a == 0){
                        a = 1;
                        Gamebutton temp = (Gamebutton)e.getSource();
                        temp.setEnabled(false);
                        temp.setIcon(new ImageIcon(getToolkit().getImage("src/images/da.png")));
                        int index = 0;
                        for(Gamebutton g : gbs){
                            if(g.equals(temp)){
                                break;
                            }
                            index++;
                        }
                        int y = index % 3;
                        int x = index / 3;
                        game[x][y] = 1;
                        if(checmate(a)){
                            JOptionPane.showMessageDialog(null, "Circle WIN!");
                            replay();
                            a = 0;
                        }
                    }
                    else{
                        a = 0;
                        Gamebutton temp = (Gamebutton)e.getSource();
                        temp.setEnabled(false);
                        temp.setIcon(new ImageIcon(getToolkit().getImage("src/images/Untitled.png")));
                        int index = 0;
                        for(Gamebutton g : gbs){
                            if(g.equals(temp)){
                                break;
                            }
                            index++;
                        }
                        int y = index % 3;
                        int x = index / 3;
                        game[x][y] = 0;
                        if(checmate(a)){
                            JOptionPane.showMessageDialog(null, "Jesus WIN!");
                            replay();
                        }
                    }
                }
            }
        );
            panel.add(gbs.get(i));
            System.out.println("1");
        }

        add(panel);
    }
}
