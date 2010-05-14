======================================================================
Software required
======================================================================
Glassfish v3: https://glassfish.dev.java.net/
James Server: http://james.apache.org/


======================================================================
Installation
======================================================================

======================================================================
LOG LEVELS IN GLASSFISH V3
======================================================================

To change logging levels in Glassfish V3

<domain>/config/logging.properties


======================================================================
Found working maven dependencies
======================================================================
        <dependency>
            <groupId>javax.faces</groupId>
            <artifactId>jsf-api</artifactId>
            <version>2.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSR 330 Dependency Injection for Java. -->
        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSR 299 Contexts and Dependency Injection for Java. -->
        <dependency>
            <groupId>javax.enterprise</groupId>
            <artifactId>cdi-api</artifactId>
            <version>1.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSR 303 Bean Validation 1.0. -->
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>1.0.0.GA</version>
            <scope>provided</scope>
        </dependency>
        <!-- JSR 317 Java Persistence API 2.0. -->
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>javax.persistence</artifactId>
            <version>2.0.0</version>
            <scope>provided</scope>
        </dependency>
 
