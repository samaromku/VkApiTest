import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Савок on 24.09.2016.
 */
public class Main {

    public static String ownerID = "vsya_nedvigimost";
    public static String message = "we%all%die(((";

    public static void main(String[] args) throws IOException {

       // URLConnection connection = new URL("https://oauth.vk.com/authorize?client_id=5640858&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.52").openConnection();
        URLConnection connection = new URL("https://api.vk.com/method/wall.post?owner_id="+ownerID+"&message="+message+"&v=5.52&access_token=074ca6ac211db3c969bc5b4bdb4554a8bcff1dcfcf7a508e0f41be9f0deffdedaf1ba26796e4a1cbbee27").openConnection();
        //URLConnection connection = new URL("https://api.vk.com/method/http://vk.com/dev/photos.createComment?owner_id=229439932&photo_id=229439932_393234523&message=бобр_Добр&v=5.52&access_token=38e370d4939f4257b6422358aa6440c3f408a19a0c8f66456c2c66a80356393361ddffc7c74c638b4c9fd").openConnection();
        //URLConnection connection = new URL("https://api.vk.com/method/friends.get?user_id=229439932&v=5.52&access_token=38e370d4939f4257b6422358aa6440c3f408a19a0c8f66456c2c66a80356393361ddffc7c74c638b4c9fd").openConnection();
        //URLConnection connection = new URL("https://api.vk.com/method/users.get?user_ids=210700286,210700287,210700284&fields=photo_id,verified,blacklisted,sex&v=5.53").openConnection();
        //URLConnection connection = new URL("https://api.vk.com/method/users.search?status=1&sex=1&v=5.53").openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String str;
        StringBuilder s = new StringBuilder();
        while ((str=reader.readLine())!=null){
            s.append(str+"\n");
        }
        reader.close();
        System.out.println(s.toString());
    }
}
