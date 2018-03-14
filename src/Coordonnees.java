/**
 * Created by tardy on 07/03/2018.
 */
public class Coordonnees {
    private int m_x;
    private int m_y;

    public Coordonnees(int x, int y) {
        this.m_x = x;
        this.m_y = y;
    }

    public int getX() {
        return m_x;
    }

    public void setX(int x) {
        this.m_x = x;
    }

    public int getY() {
        return m_y;
    }

    public void setY(int y) {
        this.m_y = y;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("X: ")
                .append(this.getX())
                .append(" ,Y: ")
                .append(this.getY())
                .append("\n");
        return stringBuilder.toString();
    }
}