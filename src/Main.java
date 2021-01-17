import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame f = new WyswietlaniePlanszy();
        f.setSize(600,400);
        f.setLocation(100,100);
        f.setVisible(true);

        JFrame parent = new JFrame();

        JOptionPane.showMessageDialog(parent, "Gra polega na odnalezieniu skarbów ukrytych w polach diagramu.\n" +
                "Liczby znajdujące się w polach informują o tym, w ilu sąsiednich polach połączonych \n" + "bokiem lub wierzchołkiem z danym polem znajdują się skarby.\n"+" W polach liczbowych skarbów nie ma.");
    }
}
