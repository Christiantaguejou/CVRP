package metier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class Graphe {

    private List<Lieu> m_lieux;

    public Graphe(String chemin) {
        this.m_lieux = this.populer(chemin);
    }

    public List<Lieu> getLieux() {
        return m_lieux;
    }

    public void setLieux(List<Lieu> lieux) {
        this.m_lieux = lieux;
    }



    public List<Lieu> populer(String chemin) {
        List<Lieu> lieux = new ArrayList<>();
        String line = null;
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(chemin));
            line = inFile.readLine();
            if (line != null) {
                while ((line = inFile.readLine()) != null) {

                    String[] data = line.split(";");
                    Lieu lieu = new Lieu();

                    lieu.setId(Integer.parseInt(data[0]));
                    lieu.setCoordonnees(new Coordonnees(Integer.parseInt(data[1]),Integer.parseInt(data[2])));
                    lieu.setQuantite(Integer.parseInt(data[3]));

                    lieux.add(lieu);
                }
            }

            inFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lieux;
    }

    public float calculDistance(Lieu lieu1, Lieu lieu2) {
        Coordonnees coordonneesLieu1 = lieu1.getCoordonnees();
        Coordonnees coordonneesLieu2 = lieu2.getCoordonnees();
        return (float) Math.sqrt(
                Math.pow(
                        coordonneesLieu2.getX() - coordonneesLieu1.getX(),
                        2
                )+ Math.pow(
                        coordonneesLieu2.getY() - coordonneesLieu1.getY(),
                        2
                ));
    }

    public Solution solutionGlouton(){
        int capacite = 0;
        List<Integer> itineraire = new ArrayList<>();
        Solution solution = new Solution(itineraire);
        itineraire.add(m_lieux.get(0).getId());

        for(int i = 1; i < m_lieux.size(); i++){
            capacite += m_lieux.get(i).getQuantite();
            if(capacite <= 100 && i < m_lieux.size() - 1){
                itineraire.add(m_lieux.get(i).getId());
            }
            else if(capacite > 100){
                itineraire.add(m_lieux.get(0).getId());
                capacite = 0;
                capacite += m_lieux.get(i).getQuantite();
                itineraire.add(m_lieux.get(0).getId());
                itineraire.add(m_lieux.get(i).getId());
            }
            else if(i == m_lieux.size() - 1){
                itineraire.add(m_lieux.get(i).getId());
                itineraire.add(m_lieux.get(0).getId());
            }
        }
        return solution;
    }
}
