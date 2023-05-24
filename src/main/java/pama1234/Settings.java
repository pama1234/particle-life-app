package pama1234;

import org.yaml.snakeyaml.Yaml;

public class Settings{
  public static String path="data/settings.yaml";
  public static Settings data;
  public static Settings readFromYaml(Yaml yaml,String in) {
    return yaml.loadAs(in,Settings.class);
  }
  public String langType;
}
