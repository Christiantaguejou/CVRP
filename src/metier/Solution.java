package metier;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public List<ArrayList<Integer>> getListeRoute(){
        List<ArrayList<Integer>> listeRoute = new ArrayList<>();
        ArrayList<Integer> route = new ArrayList<>();

        for(int i = 1; i < listeSolution.size(); i++){
            //route = new ArrayList<>();
            if(listeSolution.get(i) > 0)
                route.add(listeSolution.get(i));
            else if(listeSolution.get(i) == 0){
                listeRoute.add(route);
                route = new ArrayList<>();
            }

            //System.out.println(route);
           // System.out.println(listeRoute);
        }

        return listeRoute;
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
