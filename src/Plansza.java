import javax.swing.*;
import java.util.ArrayList;

public class Plansza {
//    sprawdzanie czy liczba skarbow jest rowna liczbie na planszy
    int rozmiar_planszy = 5;
    int x, y;
    boolean[][] tablica_gracza;
    int[][] tablica_rozwiazana;
    ArrayList<Ruch> listaRuchow = new ArrayList<>();

    Plansza(){
        tablica_gracza = new boolean[rozmiar_planszy][rozmiar_planszy];
        tablica_rozwiazana = new int[rozmiar_planszy][rozmiar_planszy];
//        wyzerowac tablice gracza
        for(int i=0; i<rozmiar_planszy; i++){
            for(int j=0; j<rozmiar_planszy; j++){
                tablica_gracza[i][j]= false;
            }
        }
//        wygenerowac tablice rozwiazana
        for(int i=0; i<rozmiar_planszy; i++){
            for(int j=0; j<rozmiar_planszy; j++){
                tablica_rozwiazana[i][j]= 0;
            }
        }

    }

    void generujPlansze(){
//        wpisac wartosci do danych pol
        tablica_rozwiazana[0][2] = 2;
        tablica_rozwiazana[1][1] = 3;
        tablica_rozwiazana[1][3] = 4;
        tablica_rozwiazana[3][0] = 2;
        tablica_rozwiazana[3][1] = 3;
        tablica_rozwiazana[3][2] = 4;
        tablica_rozwiazana[3][3] = 2;
//        --------------------------------------
//            tab[0][2].setText("2");
//            tab[1][1].setText("3");
//            tab[1][3].setText("4");
//            tab[3][0].setText("2");
//            tab[3][1].setText("3");
//            tab[3][2].setText("4");
//            tab[3][3].setText("2");
//        --------------------------------------
//            tab[0][0] = new JButton("2");
//            tab[0][3] = new JButton("1");
//            tab[0][4] = new JButton("1");
//            tab[1][2] = new JButton("3");
//            tab[3][0] = new JButton("2");
//            tab[3][2] = new JButton("4");
//            tab[3][3] = new JButton("3");
//            tab[3][4] = new JButton("1");
//        ---------------------------------------
//            tab[1][1] = new JButton("3");
//            tab[1][2] = new JButton("3");
//            tab[2][1] = new JButton("3");
//            tab[2][4] = new JButton("3");
//            tab[4][1] = new JButton("3");
//            tab[4][2] = new JButton("2");
//            tab[1][1] = new JButton("1");
//        -----------------------------------------
//              tab[0][0] = new JButton("2");
//              tab[0][3] = new JButton("1");
//              tab[1][2] = new JButton("3");
//              tab[1][4] = new JButton("1");
//              tab[2][1] = new JButton("4");
//              tab[3][1] = new JButton("5");
//              tab[4][1] = new JButton("3");
//              tab[4][4] = new JButton("1");
//        ------------------------------------------
//            tab[0][3] = new JButton("2");
//            tab[1][1] = new JButton("4");
//            tab[1][2] = new JButton("4");
//            tab[2][2] = new JButton("5");
//            tab[2][4] = new JButton("4");
//            tab[3][0] = new JButton("4");
//            tab[4][2] = new JButton("3");
//            tab[4][3] = new JButton("1");
//            tab[4][4] = new JButton("1");
//        --------------------------------------------
//            tab[0][1] = new JButton("2");
//            tab[0][3] = new JButton("2");
//            tab[1][3] = new JButton("4");
//            tab[2][0] = new JButton("1");
//            tab[3][0] = new JButton("1");
//            tab[3][1] = new JButton("3");
//            tab[3][2] = new JButton("3");
//            tab[3][4] = new JButton("1");
//            tab[4][3] = new JButton("1");
    };

    boolean sprawdzaniePLanszy(){
//        znalezienie liczb w tablicy
        //iteracja po tablicy rozwiazanej (podwojny for x, y)
//        sprawdzic czy pole[x][y] w tablicy rozwiazanej > 0 (czyli jest tam liczba)
        for(int i=0; i<rozmiar_planszy; i++){
            for(int j=0; j<rozmiar_planszy; j++){
                if(tablica_rozwiazana[i][j]>0){
                    if(zliczanieSkarbow(i,j) != tablica_rozwiazana[i][j]){
                        return false;
                    }
                }
            }
        }
        return true;
    };

    boolean czyPoleJestPuste(int x, int y){
       if(tablica_rozwiazana[x][y]==0 && tablica_gracza[x][y] == false) {
           return true;
       }
       else{
           return false;
       }
    };

    int zliczanieSkarbow(int x, int y){
        int licznik=0;
        if(x-1>=0 && tablica_gracza[x-1][y]==true)licznik++;
        if(y-1>=0 && tablica_gracza[x][y-1]==true)licznik++;
        if(x-1>=0 && y-1>=0 && tablica_gracza[x-1][y-1]==true)licznik++;
        if(x+1<5 && tablica_gracza[x+1][y]==true)licznik++;
        if(y-1>=0 && x+1<5 && tablica_gracza[x+1][y-1]==true)licznik++;
        if(x+1<5 && y+1<5 && tablica_gracza[x+1][y+1]==true)licznik++;
        if(y+1<5 && tablica_gracza[x][y+1]==true)licznik++;
        if(x-1>=0 && y+1<5 && tablica_gracza[x-1][y+1]==true)licznik++;

        return licznik;
    };

    int wartoscPolaLiczby(int x, int y){
        return tablica_rozwiazana[x][y];
    }

    void zmienNaSkarb(int x, int y){
//        zmienic dana pozycje na true
//        dodaj to listy Ruchow
        tablica_gracza[x][y] = true;
        listaRuchow.add(new Ruch(x,y));
    };

    boolean czyJestSkarb(int x, int y){
        return tablica_gracza[x][y];
    }

//    lista wykonanych ruchow

    void cofnijRuch(){
//        spojrzec na ostatni ruch z listy ruchow
        if(listaRuchow.isEmpty()== false){
            Ruch ruch = listaRuchow.get(listaRuchow.size()-1);
            tablica_gracza[ruch.x][ruch.y] = false;
            listaRuchow.remove(listaRuchow.size() -1);
        }

    }



}

