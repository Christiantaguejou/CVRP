import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        List<Lieu> lieux;
        FonctionsUtiles fonctionsUtiles = new FonctionsUtiles();
        lieux = fonctionsUtiles.populer("./data/data01.txt");
        System.out.println(lieux);
    }
}
