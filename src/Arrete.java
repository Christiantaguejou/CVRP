/**
 * Created by tardy on 07/03/2018.
 */
public class Arrete {

    private Lieu m_lieu1;
    private Lieu m_lieu2;

    public Arrete(Lieu lieu1, Lieu lieu2) {
        this.m_lieu1 = lieu1;
        this.m_lieu2 = lieu2;
    }

    public Lieu getLieu1() {
        return m_lieu1;
    }

    public void setLieu1(Lieu lieu1) {
        this.m_lieu1 = lieu1;
    }

    public Lieu getLieu2() {
        return m_lieu2;
    }

    public void setLieu2(Lieu lieu2) {
        this.m_lieu2 = lieu2;
    }
}
