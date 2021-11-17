package entities;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmPage {

    Algorithm algorithm;
    List<MachineConfiguration> machineConfigurations;

    public AlgorithmPage(){
        algorithm = null;
        machineConfigurations = new ArrayList<>();
    }

    public void setAlgorithm(Algorithm algorithm){
        this.algorithm = algorithm;
    }

    public Algorithm getAlgorithm(){
        return this.algorithm;
    }

    public void setMachineConfigurations(List<MachineConfiguration> mcs){
        this.machineConfigurations = mcs;
    }

    public List<MachineConfiguration> getMachineConfigurations(){
        return this.machineConfigurations;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof AlgorithmPage)) return false;
        AlgorithmPage a = (AlgorithmPage) obj;
        return this.algosMatch(a) && this.machineConfigurationsMatch(a);
    }

    /*
     * Checks if this algorithmpages's machineConfigurations match a's machineConfigurations exactly(order doesn't matter)
     */
    private boolean machineConfigurationsMatch(AlgorithmPage a) {
        //check if machineConfigurations is null to prevent null pointers
        if(this.machineConfigurations != null){
            //null pointer safty VV
            if(a.machineConfigurations != null && this.machineConfigurations.size() == a.machineConfigurations.size()){
                //go through each machineConfiguration and make sure a has it as well
                for(int i = 0; i < a.machineConfigurations.size(); i++){
                    if(!this.machineConfigurations.contains(a.machineConfigurations.get(i))) {
                        return false;
                    }
                }
                return true;
            }else{
                //either a has null machineConfigurations(and this does not) or the sizes don't match
                return false;
            }
        }else{
            //this.machineConfigurations is null so a must have null machineConfigurations
            return a.machineConfigurations == null;
        }
    }
/*
Checks if the algos match
 */
    private boolean algosMatch(AlgorithmPage a) {
        //the if statement prevents null pointers
        if(this.algorithm != null){
            return this.algorithm.equals(a.algorithm);
        }else{
            return a.algorithm == null;
        }
    }


}
