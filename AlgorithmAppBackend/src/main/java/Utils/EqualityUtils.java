package Utils;

public class EqualityUtils {
    public static boolean equalOrBothNull(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) ||
                (obj1 != null && obj1.equals(obj2));
    }
}