import java.util.HashMap;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class Graphe {

    private List<Lieu> m_lieux;
    private List<Arrete> m_arretes;

    public Graphe(List<Lieu> lieux, List<Arrete> arretes) {

        this.m_lieux = lieux;
        this.m_arretes = arretes;
    }

    public List<Lieu> getLieux() {
        return m_lieux;
    }

    public void setLieux(List<Lieu> lieux) {
        this.m_lieux = lieux;
    }

    public List<Arrete> getArretes() {
        return m_arretes;
    }

    public void setArretes(List<Arrete> arretes) {
        this.m_arretes = arretes;
    }

    public void makeGrapheComplet(){
        FonctionsUtiles fonctionsUtiles = new FonctionsUtiles();
        List<Lieu> lieux = fonctionsUtiles.populer("./data/data01.txt");
        for (Lieu lieu: m_lieux
             ) {

        }
    }


}
