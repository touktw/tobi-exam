package springbook.lerningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import springbook.learningtest.template.Calculator;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public class CalcSumTest {
  private Logger logger = Logger.getLogger(this.getClass());
  @Test
  public void sumOfNumbers() throws IOException {
    Calculator calculator = new Calculator();
    File f = new File("numbers.txt");
    int sum = calculator.calcSum(f.getPath());
    assertThat(sum, is(10));
  }
}
