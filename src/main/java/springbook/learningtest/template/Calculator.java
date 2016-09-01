package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public class Calculator {
  public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(filepath));
      int ret = callback.doSomethingWithReader(br);
      return ret;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw e;
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(filepath));
      T res = initVal;
      String line = null;
      while ((line = br.readLine()) != null) {
        res = callback.doSomethingWithLine(line, res);
      }
      return res;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw e;
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public Integer calcSum(String filepath) throws IOException {
    LineCallback sumCallback = new LineCallback<Integer>() {
      @Override
      public Integer doSomethingWithLine(String line, Integer value) {
        return value + Integer.valueOf(line);
      }
    };
    return lineReadTemplate(filepath, sumCallback, 0);
  }

  public Integer calcMultiply(String filepath) throws IOException {
    LineCallback sumCallback = new LineCallback<Integer>() {
      @Override
      public Integer doSomethingWithLine(String line, Integer value) {
        return value * Integer.valueOf(line);
      }
    };
    return lineReadTemplate(filepath, sumCallback, 1);
  }
}
