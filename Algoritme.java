/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.ArrayList;
/**
 *
 * @author Sony
 */
public class Algoritme {
    private ArrayList<String> allComb = new ArrayList<>();  
    private ArrayList<String> colors = new ArrayList<>();
    private String comb = "";

    public Algoritme() {
    }
    
    
    public void start_c() {
        
        colors.add("B");
        colors.add("G");
        colors.add("Y");
        setWithout_Repe_Comb();
        System.out.println (allComb.size());
    }
    
    
    public void setAllComb() {
        
       
        if (comb.length() == 4) {
            allComb.add(comb);
            System.out.println(comb+ "\n");
        }
        else {
            for (int i = 0; i < colors.size(); ++i){
                String oldComb = comb;
                comb = comb + colors.get(i);
                setAllComb();
                comb = oldComb;
            }
        }
    }
    
    public void setWithout_Repe_Comb(){
        if (comb.length() == 3) {
            allComb.add(comb);
            System.out.println(comb+ "\n");
        }
        else {
            for (int i = 0; i < colors.size(); ++i){
                String oldComb = comb;
                if (!comb.contains(colors.get(i))){
                    comb = comb + colors.get(i);
                    setWithout_Repe_Comb();
                }
                comb = oldComb;
            }
        }
    }
}
