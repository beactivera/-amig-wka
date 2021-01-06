import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

class Model{
    char  tab[][] = new char[8][8];
    boolean sprawdz(int w, int k){
     // sprawdza, czy hetman atakuje pole (w,k);
     // wiemy, ze na tym polu nie ma hetmana
     int i;
     for (i=0;i<8;i++){
        if (tab[i][k]=='Q') return true;
	if (tab[w][i]=='Q') return true;
	if (w+i<8 && k+i<8 && tab[w+i][k+i]=='Q') return true;
	if (w+i<8 && k-i>0 && tab[w+i][k-i]=='Q') return true;	    
	if (w-i>0 && k-i>0 && tab[w-i][k-i]=='Q') return true;
	if (w-i>0 && k+i<8 && tab[w-i][k+i]=='Q') return true;	    
     }
     return false;
    }
}

class Plansza extends JFrame {
  Model model = new Model();
  JButton tab[][] = new JButton[8][8] ;
  JPanel plansza = new JPanel();
  JPanel sterowanie = new JPanel();
  JTextField t = new JTextField(10);
  public Plansza() {
    int i,j;
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(1,2));
    cp.add(plansza); cp.add(sterowanie);
    sterowanie.setLayout(new GridLayout(8,1));
    sterowanie.add(t);
    t.setFont(t.getFont().deriveFont(30.0f));
    plansza.setLayout(new GridLayout(8,8));
    for (i=0;i<8;i++)
       for (j=0;j<8;j++){
	 tab[i][j]=new JButton("");
	 if((i+j) % 2 == 0) (tab[i][j]).setBackground(Color.black);
         plansza.add(tab[i][j]);
         (tab[i][j]).addActionListener(new B(i,j));
    }
    ImageIcon queen = new ImageIcon("queen.jpg");
    tab[1][1].setIcon(queen);
    model.tab[1][1]='Q';  
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  class B implements ActionListener {
    int i,j;
    B(int i,int j){this.i=i;this.j=j;}  
      public void actionPerformed(ActionEvent e) {
      if (model.tab[i][j] == 'Q' )
	    t.setText(i+","+j+ " Hetman");
      else
	  if (model.sprawdz(i,j))
	      t.setText(i+","+j+" pole atakowane");
          else
     	      t.setText(i+","+j+" pole bezpieczne");
    }
  }

 public static void main(String[] args) {
    JFrame f = new Plansza();
    f.setSize(600,400);
    f.setLocation(100,100);
    f.setVisible(true);
  }
} 