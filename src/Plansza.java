import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;

class Plansza extends JFrame {
    JButton tab[][] = new JButton[5][5] ;
    JPanel plansza = new JPanel();
    JPanel sterowanie = new JPanel();
    JTextField t = new JTextField(10);
    Plansza() {
        int i,j;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1,2));
        cp.add(plansza); cp.add(sterowanie);
        sterowanie.setLayout(new GridLayout(8,1));
        sterowanie.add(t);
        t.setFont(t.getFont().deriveFont(30.0f));
        plansza.setLayout(new GridLayout(5,5));
        tab[0][2] = new JButton("2");
        tab[1][1] = new JButton("3");
        tab[1][3] = new JButton("4");
        tab[3][0] = new JButton("2");
        tab[3][1] = new JButton("3");
        tab[3][2] = new JButton("4");
        tab[3][3] = new JButton("2");
        for (i=0;i<5;i++)
            for (j=0;j<5;j++){
                if(tab[i][j] != null) {
                    (tab[i][j]).setEnabled(false);
                }else{
                    tab[i][j] = new JButton("");
                }
                plansza.add(tab[i][j]);
                (tab[i][j]).addActionListener(new Zaznacz(i,j));
            }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class Zaznacz implements ActionListener{
        int i, j;
        public Zaznacz(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e){
            (tab[i][j]).setBackground(Color.GREEN);
            (tab[i][j]).setText("X");
        }
    }
}
