package http;

public class CreateClassificationRequest {

    public String className;
    public String parentClassName;

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
