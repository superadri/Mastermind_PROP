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
    private ArrayList<String> allColors = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private String comb = "";
    private int nFiles, nColors;
    private boolean repetition;

    public Algoritme(int nFiles, int nColors,boolean repetition) {
        this.nFiles = nFiles;
        this.nColors = nColors; 
        this.repetition = repetition;
    }
    public void start_c() {
        crearColors();
        if (repetition) setAllComb();
        else setWithout_Repe_Comb();
        System.out.println (allComb.size());
    }
    
    public void crearColors() {
        allColors.add("B");
        allColors.add("G");
        allColors.add("Y");
        allColors.add("R");
        allColors.add("O");
        allColors.add("C");
        for (int i = 0; i < nFiles; ++i){
            colors.add(allColors.get(i));
        } 
    }
    
    public void setAllComb() {
        
       
        if (comb.length() == nColors) {
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
        if (comb.length() == nColors) {
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
