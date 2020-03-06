import com.linecorp.armeria.common.HttpHeaderNames;
import com.linecorp.armeria.common.HttpMethod;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.Service;
import com.linecorp.armeria.server.cors.CorsService;
import com.linecorp.armeria.server.cors.CorsServiceBuilder;
import com.linecorp.armeria.server.thrift.THttpService;
import hello.MyHelloService;
import java.util.function.Function;

public class HelloServer {
    public static void main(String[] args) {
        final Function<Service<HttpRequest, HttpResponse>, CorsService> corsBuilder = CorsServiceBuilder.forOrigin("http://localhost:8081")
                .allowCredentials()
                .allowNullOrigin()
                .allowRequestHeaders(HttpHeaderNames.CONTENT_TYPE)
                .allowRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .preflightResponseHeader("x-preflight-cors", "Hello CORS")
                .newDecorator();

        ServerBuilder sb = Server.builder();
        sb.service("/", THttpService.of(new MyHelloService(), ThriftSerializationFormats.JSON))
                .http(9090)
                .decorator(corsBuilder);

        System.out.println("SERVER STARTED");
        Server server = sb.build();
        server.start();
    }
}