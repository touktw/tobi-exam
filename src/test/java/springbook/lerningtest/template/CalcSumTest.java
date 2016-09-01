package springbook.lerningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import springbook.learningtest.template.Calculator;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public class CalcSumTest {
  private Logger logger = Logger.getLogger(this.getClass());
  Calculator calculator;
  String numFilePath;

  @Before
  public void setUp() {
    this.calculator = new Calculator();
    this.numFilePath = new File("numbers.txt").getPath();
  }

  @Test
  public void sumOfNumbers() throws IOException {
    assertThat(calculator.calcSum(numFilePath), is(10));
  }

  @Test
  public void multiplyOfNumbers() throws IOException {
    assertThat(calculator.calcMultiply(numFilePath), is(24));
  }
}
