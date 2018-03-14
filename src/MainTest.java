import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        List<Lieu> lieux;
        List<Arrete> arretes = new ArrayList();
        FonctionsUtiles fonctionsUtiles = new FonctionsUtiles();
        lieux = fonctionsUtiles.populer("./data/data01.txt");
        System.out.println(lieux);
        arretes.add(new Arrete(lieux.get(0),lieux.get(1)));
        Graphe graphe = new Graphe(lieux,arretes);
    }
}
