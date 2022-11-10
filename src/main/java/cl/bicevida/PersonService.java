package cl.bicevida;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonService {

    public String getName(Person p){
        return p.getName();
    }
}