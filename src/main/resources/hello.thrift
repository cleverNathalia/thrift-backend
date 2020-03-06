namespace java com.example.thrift.hello

struct Person{
1: string id,
2: string name,
3: string address,
4: string phone
}

service HelloService {
    Person getRole(1: string role);
}