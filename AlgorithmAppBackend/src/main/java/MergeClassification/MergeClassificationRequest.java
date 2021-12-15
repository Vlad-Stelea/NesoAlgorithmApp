package MergeClassification;

import com.google.gson.Gson;

public class MergeClassificationRequest {

    private String class1;
    private String class2;
    private String newName;
    private String parentName;
    public MergeClassificationRequest(){}
    public MergeClassificationRequest(String class1, String class2,String newName) {
        this.class1 = class1;
        this.class2 = class2;
        this.newName =newName;

    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
