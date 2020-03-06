package hello;

import com.example.thrift.hello.HelloService;
import com.example.thrift.hello.Person;
import org.apache.thrift.TException;

public class MyHelloService implements HelloService.Iface {

    Person people = new Person();

    @Override
    public Person getRole(String role) throws TException {
        if (role.equals("admin")){
            people.id = "960712834959";
            people.name = "Jane Johnson";
            people.address = "8 Brood Street, Cape Town";
            people.phone = "0825943219";

            return people;

        }else{
            people.id = "920343204032";
            people.name = "John Doe";
            people.address = "";
            people.phone = "";

            return people;
        }
    }

}
