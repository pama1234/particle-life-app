package pama1234;

import org.yaml.snakeyaml.Yaml;

public class Localization{
  public static Localization usedLang;
  public static Localization readFromYaml(Yaml yaml,String in) {
    // var yaml=new Yaml();
    return yaml.loadAs(in,Localization.class);
  }
  public String title;
}
