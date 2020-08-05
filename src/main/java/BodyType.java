import java.util.HashMap;

public class BodyType {

    private static String NAME = "name";
    private static String JOB = "job";
    public static String accessToken;

    static HashMap userParams(){
        HashMap<String, String> params = new HashMap<>();

        params.put(NAME, "David");
        params.put(JOB, "driver");

        return params;
    }
}
