import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;

class WyswietlaniePlanszy extends JFrame {
//    DEKLARACJA ZMIENNYCH DO ZBUDOWANIA INTERFEJSU
    Plansza plansza = new Plansza();
    JButton tab[][] = new JButton[5][5] ;
    JButton zapisz = new JButton("Zapisz Grę");
    JButton redo = new JButton("Redo");
    JButton undo = new JButton("Undo");
    JButton sprawdz = new JButton("Sprawdź");
    JButton wczytaj = new JButton("Wczytaj grę");
    JPanel wyswietlanie = new JPanel();
    JPanel sterowanie = new JPanel();
    JLabel title = new JLabel("SKARBY");
    JLabel instrukcja = new JLabel("Instrukcja:");
    JLabel  link = new JLabel("www.math.edu.pl/skarby");
    JTextField t = new JTextField(10);

    WyswietlaniePlanszy() {
        int i, j;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1, 2));
//        JPANEL - 'wyswietlanie' - na tym obszarze użytkownik dokonuje postawienia skarbu bądź jego usunięcia
        cp.add(wyswietlanie);
        wyswietlanie.setLayout(new GridLayout(5, 5));
//        TO DO: dodanie innych plansz, przycinki z funkcjami plansza.generujPlansze + wyswietlPlansze
//        wygenerowanie planszy z cyframi
        plansza.generujPlansze();
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                tab[i][j] = new JButton("");
                wyswietlanie.add(tab[i][j]);
                (tab[i][j]).addActionListener(new Zaznacz(i, j));
            }
        };
        wyswietlPlansze();
//        JPANEL - 'sterowanie' - użytkownik klika danych przycisk wywołując zmianę na planszy
        cp.add(sterowanie);
        sterowanie.setLayout(new GridLayout(10, 1));
        sterowanie.add(title);
        sterowanie.add(new JLabel(""));
        sterowanie.add(instrukcja);
        sterowanie.add(link);
        sterowanie.add(new JLabel(""));
        sterowanie.add(new JLabel(""));
        sterowanie.add(redo);
        redo.addActionListener(new Ponow());
        sterowanie.add(undo);
        undo.addActionListener(new Cofnij());
        sterowanie.add(wczytaj);
        wczytaj.addActionListener(new Wczytaj());
        sterowanie.add(zapisz);
        zapisz.addActionListener(new Zapisz());
        sterowanie.add(new JLabel(""));
        sterowanie.add(new JLabel(""));
        sterowanie.add(t);
        sterowanie.add(sprawdz);
        sprawdz.addActionListener(new Sprawdzenie());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void wyswietlPlansze(){
//        funkcja, wertuje przesz wszystkie elementy w planszy
        int i, j;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
//                przypisujemy możliwość klikania dla pól bez liczb
                if (plansza.czyPoleJestPuste(i,j)) {
                    (tab[i][j]).setEnabled(true);
                } else {
//                    dla pól, które mają liczbę wyświetlamy ją na polu
                    tab[i][j].setText(Integer.toString(plansza.wartoscPolaLiczby(i,j)));
                    (tab[i][j]).setEnabled(false);

                }
//                
                if(plansza.czyJestSkarb(i,j)==true){
                    (tab[i][j]).setBackground(Color.GREEN);
                    (tab[i][j]).setText("");
                }
                else{
                    (tab[i][j]).setBackground(null);
                }
                wyswietlanie.add(tab[i][j]);
            }
        }
    };
    class Zaznacz implements ActionListener{
        int i, j;
        public Zaznacz(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e){
//            (tab[i][j]).setBackground(Color.GREEN);
//            (tab[i][j]).setText("X");
            plansza.zmienNaSkarb(i,j);
            wyswietlPlansze();
        }
    }

    class Sprawdzenie implements ActionListener{

        public void actionPerformed(ActionEvent e) {
          if(plansza.sprawdzaniePLanszy()== true){
              t.setText("Dobrze!");
          }
          else{
              t.setText("Źle.Spróbuj ponownie!");
          }
        }
    }

    class Cofnij implements ActionListener {
        public void actionPerformed(ActionEvent e){
            plansza.cofnijRuch();
            wyswietlPlansze();
        }
    }

    class Ponow implements ActionListener {
        public void actionPerformed(ActionEvent e){
            plansza.ponowRuch();
            wyswietlPlansze();
        }
    }

    class Zapisz implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                String nazwa = JOptionPane.showInputDialog("Podaj nazwę pliku: ");
                FileOutputStream fOut = new FileOutputStream("src/Dane/"+nazwa);
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.writeObject(plansza);
                objOut.close();
                fOut.close();
                t.setText("Zapisano poprawnie!");
            }
            catch(IOException ioException){
                t.setText("Nie powiodło się zapisanie!");
            }
        }
    }

    class Wczytaj implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                String nazwa = JOptionPane.showInputDialog("Podaj nazwę pliku: ");
                FileInputStream fIn = new FileInputStream("src/Dane/"+nazwa);
                ObjectInputStream objIn = new ObjectInputStream(fIn);
                plansza = (Plansza) objIn.readObject();
                objIn.close();
                fIn.close();
                t.setText("Wczytano dane.");
                wyswietlPlansze();
            }
            catch(IOException | ClassNotFoundException i){
                t.setText("Nie powiodło się wczytywanie!");
            }
        }
    }
    
}




