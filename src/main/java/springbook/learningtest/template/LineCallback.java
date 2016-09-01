package springbook.learningtest.template;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public interface LineCallback<T> {
  T doSomethingWithLine(String line, T value);
}
