# Using Resource Files in a Maven Project

Run the following command to generate a blank Maven project:

```bash
mvn -B archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DgroupId=com.companyname.packagename \
  -DartifactId=my-new-package
```

It will make a new directory called "`my-new-package`" inside the current directory. Next, make a directory called `src/main/resources` inside the Maven project directory (inside `my-new-package`) and add the following text to a file called `example.txt` in that directory:

```
this is an example resource
here is a second line
and a third one
```

Remove the files `src/test/java/com/companyname/packagename/AppTest.java` and `src/main/java/com/companyname/packagename/App.java`. Replace the text of your `pom.xml` file with the following text:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>     com.companyname.packagename  </groupId>
  <artifactId>  my-new-package               </artifactId>
  <packaging>   jar                          </packaging>

  <version>1.0</version>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <build>
    <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.companyname.packagename.MainClass</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
    </plugins>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
      </resource>
    </resources>
  </build>

</project>
```

Finally, create a file at `src/main/java/com/companyname/packagename` called `MainClass.java` and paste the following text into it:

```java
package com.companyname.packagename;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainClass {

  public static void main (String[] args) throws UnsupportedEncodingException, IOException {
    readResource();
  }

  public static void readResource() throws UnsupportedEncodingException, IOException {

    InputStream is = MainClass.class.getClassLoader().getResourceAsStream("example.txt");
    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    BufferedReader file = new BufferedReader(isr);

    String line = null; // line read from file
    while ((line = file.readLine()) != null)
      System.out.println(line);

    file.close(); isr.close(); is.close();

  }

}
```

Run `mvn package` in the Maven project directory and wait for the compilation and packaging to finish. Then, run `java -jar target/my-new-package-1.0.jar`. You should see something like:

```bash
$ java -jar target/my-new-package-1.0.jar 
this is an example resource
here is a second line
and a third one
```

Ta-da! Resource read within Maven.
