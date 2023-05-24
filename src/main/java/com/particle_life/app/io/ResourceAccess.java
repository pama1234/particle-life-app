package com.particle_life.app.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceAccess{
  /**
   * @param path must not start with "/" or "./", e.g. "textures/image.png",
   *             "settings.properties", ...
   */
  public static InputStream getInputStream(String path) {
    return ClassLoader.getSystemClassLoader().getResourceAsStream(path);
  }
  public static String readTextFileData(String path) {
    try {
      InputStream inputStream=new FileInputStream(path);
      String text=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
      inputStream.close();
      return text;
    }catch(IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  public static String readTextFile(String path) {
    InputStream inputStream=getInputStream(path);
    String text=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))
      .lines()
      .collect(Collectors.joining("\n"));
    try {
      inputStream.close();
    }catch(IOException e) {
      e.printStackTrace();
    }
    return text;
  }
  /**
   * @param directory must not start with "/" or "./", e.g. "textures", "assets/music", ...
   */
  public static List<Path> listFiles(String directory) throws IOException,URISyntaxException {
    URI uri=ClassLoader.getSystemClassLoader().getResource(directory).toURI();
    Path path;
    if(uri.getScheme().equals("jar")) {
      path=FileSystems.newFileSystem(uri,Collections.emptyMap()).getPath(directory);
    }else {
      path=Paths.get(uri);
    }
    return Files.walk(path,1)
      .skip(1) // first entry is just the directory
      .collect(Collectors.toList());
  }
  public static void saveTextFile(String path,String data) {
    try {
      Path outputPath=Paths.get(path);
      Files.write(outputPath,data.getBytes(StandardCharsets.UTF_8));
    }catch(IOException e) {
      e.printStackTrace();
    }
  }
}
