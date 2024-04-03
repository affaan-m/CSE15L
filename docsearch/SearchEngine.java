import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class SearchHandler implements URLHandler {
    List<String> stringList = new ArrayList<>();

    public String handleRequest(URI url) {
        String path = url.getPath();
        String query = url.getQuery();

        if (path.equals("/add")) {
            String[] parameters = query.split("=");
            if (parameters[0].equals("s")) {
                stringList.add(parameters[1]);
                return "String added!";
            }
        } else if (path.equals("/search")) {
            String[] parameters = query.split("=");
            if (parameters[0].equals("s")) {
                List<String> results = new ArrayList<>();
                for (String str : stringList) {
                    if (str.contains(parameters[1])) {
                        results.add(str);
                    }
                }
                return "Search results: " + results.toString();
            }
        }
        return "404 Not Found!";
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);
        Server.start(port, new SearchHandler());
    }
}
