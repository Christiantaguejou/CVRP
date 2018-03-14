import java.util.List;

/**
 * Created by tardy on 14/03/2018.
 */
public class Solution {
    private List<Integer> listeSolution;

    public Solution(List<Integer> listeSolution) {
        this.listeSolution = listeSolution;
    }

    public List<Integer> getListeSolution() {
        return listeSolution;
    }

    public void setListeSolution(List<Integer> listeSolution) {
        this.listeSolution = listeSolution;
    }

    @Override
    public String toString() {
        StringBuilder listeId = new StringBuilder();
        for(int i = 0; i < listeSolution.size(); i++){
            listeId.append(listeSolution.get(i)+" ");
        }
        return listeId.toString();
    }
}
