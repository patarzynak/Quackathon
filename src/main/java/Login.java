/**
 * Created by Ryan on 19/11/2016.
 */
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.websocket.api.*;
import org.json.*;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;

import org.apache.velocity.app.*;


public class Login {

    private static String Username = "test";
    private static String Password = "password";

    public static VelocityTemplateEngine engine(){
        VelocityEngine conEngine = new VelocityEngine();
        conEngine.setProperty("runtime.references.string", true);
        conEngine.setProperty("resource.loader", "class");
        conEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityTemplateEngine vte = new VelocityTemplateEngine(conEngine);

        return vte;
    }

    public static Route GetPage = (spark.Request request, spark.Response response) ->
    {
        Map<String, Object> model = new HashMap<>();



        return engine().render(new ModelAndView(model, "/Velocity/login.vm"));
    };

    public static Route LoginPost = (spark.Request request, spark.Response response)-> {
        String u = request.queryParams("username");
        String p = request.queryParams("password");


        if (!u.equals(Username)) {
            return null;
        }

        if (!p.equals(Password)) {
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("auth-success", true);

        return engine().render(new ModelAndView(model, "/Velocity/login.vm"));
    };
}
