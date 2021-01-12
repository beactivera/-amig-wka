import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

class WyswietlaniePlanszy extends JFrame {
//    DEKLARACJA ZMIENNYCH DO ZBUDOWANIA INTERFEJSU
    Plansza plansza = new Plansza();
    JButton tab[][] = new JButton[5][5] ;
    JButton zapisz = new JButton("Zapisz Grę");
    JButton redo = new JButton("Redo");
    JButton undo = new JButton("Undo");
    JButton sprawdz = new JButton("Sprawdź");
    JPanel wyswietlanie = new JPanel();
    JPanel sterowanie = new JPanel();
    JLabel title = new JLabel("Skarby:");
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
            }
        };
        wyswietlPlansze();
//        JPANEL - 'sterowanie' - użytkownik klika danych przycisk wywołując zmianę na planszy
        cp.add(sterowanie);
        sterowanie.setLayout(new GridLayout(10, 1));
        sterowanie.add(t);
        t.setFont(t.getFont().deriveFont(30.0f));
        sterowanie.add(title);
        sterowanie.add(new JLabel(""));
        sterowanie.add(new JLabel(""));
        sterowanie.add(new JLabel(""));
//        TO DO: zapisanie gry do pliku
        sterowanie.add(zapisz);
        zapisz.addActionListener(new Zapisz());
//        TO DO - redo, cofnać cofnięcie?
        sterowanie.add(redo);
        sterowanie.add(undo);
        undo.addActionListener(new Cofnij());
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
                (tab[i][j]).addActionListener(new Zaznacz(i, j));
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
              t.setText("Wygrales :)");
          }
          else{
              t.setText("Przegrales :(");
          }
        }
    }

    class Cofnij implements ActionListener {
        public void actionPerformed(ActionEvent e){
            plansza.cofnijRuch();
            wyswietlPlansze();
        }
    }

    class Zapisz implements ActionListener {
        public void actionPerformed(ActionEvent e){
            }
        }


    class Wczytaj implements ActionListener {
        public void actionPerformed(ActionEvent e){

            }
        }

}

// gra SKARBY
// plansza przyciskow 5 x 5 - wpisujemy liczby w niektore pola - blokujemy do nich dostep aby nie mozna bylo ich zmienic
// pozostale miejsca sa aktywne do klikniecia, kazde zaznaczenie wiaze sie z pokolorowaniem pola na zielona i wstawienie X
// kiedy dokonujemy sprawdzenia - to wertujemy przez przyciski, szukamy tych zablokowanych i zliczamy ile jest sasiadujacych zielonych przyciskow
// jak liczba tych pol i cyfra na zablokowanych przyciskach jest sobie rowne to znaczy ze zostala gra rozwiazana poprawnie - sprawdzenie kazdego takiego pola?
// + zapisywanie do pliku historii, zczytywanie historii, poruszanie sie w przod i tyl, komentarze o rozwiazaniu
// wczytywanie roznych planszy?


