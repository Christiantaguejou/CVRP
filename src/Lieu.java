/**
 * Created by tardy on 07/03/2018.
 */
public class Lieu {
    private Coordonnees m_coordonnees;
    private int m_quantite;
    private int m_id;

    public Lieu(Coordonnees coordonnees, int quantite, int id) {
        this.m_coordonnees = coordonnees;
        this.m_quantite = quantite;
        this.m_id = id;
    }

    public Lieu() {
    }

    public Coordonnees getCoordonnees() {
        return m_coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.m_coordonnees = coordonnees;
    }

    public int getQuantite() {
        return m_quantite;
    }

    public void setQuantite(int quantite) {
        this.m_quantite = quantite;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        this.m_id = id;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Lieu:\n")
                .append("Id: ")
                .append(this.getId())
                .append("\n")
                .append(this.getCoordonnees().toString())
                .append("Quantite: ")
                .append(this.getQuantite())
                .append("\n\n");
        return stringBuilder.toString();
    }
}
