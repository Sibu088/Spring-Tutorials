package net.javaguides.springannotations;

import net.javaguides.springannotations.scope.SingletonBean;
import net.javaguides.springannotations.scope.PrototypeBean;
import net.javaguides.springannotations.lazy.propertysource.PropertySourceDemo;
import net.javaguides.springannotations.configurationproperties.AppPropertiesDemo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private PrototypeBean prototypeBean;

    @Autowired
    private PropertySourceDemo propertySourceDemo;

    @Autowired
    private AppPropertiesDemo appPropertiesDemo;

    @Override
    public void run(String... args) {

        // 🔥 COMMENT / UNCOMMENT the demo you want to show:

         demoSingleton();
        //demoPrototype();
         //demoPropertySource();
        // demoConfigurationProperties();

    }


    private void demoSingleton() {
        System.out.println("=== Singleton Scope ===");
        System.out.println(singletonBean.hashCode());
        System.out.println(singletonBean.hashCode());
        System.out.println(singletonBean.hashCode());
    }

    private void demoPrototype() {
        System.out.println("=== Prototype Scope ===");
        System.out.println(prototypeBean.hashCode());
        System.out.println(prototypeBean.hashCode());
        System.out.println(prototypeBean.hashCode());
    }

    private void demoPropertySource() {
        System.out.println("=== @PropertySource Demo ===");
        System.out.println(propertySourceDemo.getHost());
        System.out.println(propertySourceDemo.getEmail());
        System.out.println(propertySourceDemo.getPassword());
        System.out.println(propertySourceDemo.getAppName());
        System.out.println(propertySourceDemo.getAppDesc());
    }

    private void demoConfigurationProperties() {
        System.out.println("=== @ConfigurationProperties Demo ===");
        appPropertiesDemo.display();
    }
}
