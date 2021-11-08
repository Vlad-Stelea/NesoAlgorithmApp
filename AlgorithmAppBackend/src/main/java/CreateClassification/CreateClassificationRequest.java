package CreateClassification;

public class CreateClassificationRequest {

    String className;
    String parentClassName;

    public void setClassName(String className) {
        this.className = className;
    }
    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public String getName() { return className; }
    public String getParentClassName() { return parentClassName; }

    public CreateClassificationRequest() { }

    public CreateClassificationRequest(String className, String parentClassName) {
        this.className = className;
        this.parentClassName = parentClassName;
    }

    public String toString() {
        return "CreateClassification(" + className + ", " + parentClassName + ")";
    }


}
