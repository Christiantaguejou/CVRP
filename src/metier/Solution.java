package metier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 14/03/2018.
 */
public class Solution {
    private List<Integer> listeSolution;

    public Solution(){

    }

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
            if(listeSolution.get(i) > 0)
                route.add(listeSolution.get(i));
            else if(listeSolution.get(i) == 0){
                listeRoute.add(route);
                route = new ArrayList<>();
            }
        }
        return listeRoute;
    }

    public void setListeRoute(List<ArrayList<Integer>> listeRoute){
        List<Integer> listeSolution = new ArrayList<>();

        listeSolution.add(0);
        for(int i = 0; i < listeRoute.size(); i++){
            for(int j = 0; j < listeRoute.get(i).size(); j++){
                listeSolution.add(listeRoute.get(i).get(j));
            }
            listeSolution.add(0);
        }
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
