import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class FonctionsUtiles {

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
}
