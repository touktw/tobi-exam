package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by tae.kim on 2016. 9. 1..
 */
public interface BufferedReaderCallback {
  Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
