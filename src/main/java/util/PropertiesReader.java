package util;

import exception.InvalidPropertiesException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class PropertiesReader {

  private Properties props = new Properties();

  public PropertiesReader(String filePath){
    String absPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + filePath;
    try {
      props.load(new FileInputStream(absPath));
    } catch (IOException e) {
      throw new InvalidPropertiesException(
          String.format("Unable to load properties from %s", absPath),
          e
      );
    }
  }

  public String get(String propName) {
    return Optional.ofNullable(props.getProperty(propName))
        .orElseThrow(()->new InvalidPropertiesException(
            String.format("No '%s' property in properties file", propName)));
  }

}
