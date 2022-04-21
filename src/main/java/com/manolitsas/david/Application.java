package com.manolitsas.david;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

    if (args.length > 0) {
      List<Integer> numbers = List.of(2, 5, 4, 3, 2);
      // map
      List<Integer> squared = numbers.stream().map(n -> n * n).collect(Collectors.toList());
      log.info("Numbers squared: {}", squared);

      List<String> languages = List.of("C", "C++", "C#", "Java", "Python", "JavaScript");
      // filter
      List<String> cLanguages =
          languages.stream().filter(l -> l.startsWith("C")).collect(Collectors.toList());
      log.info("C-programming languages: {}", cLanguages);

      // sorted
      List<Integer> numbersSorted = numbers.stream().sorted().collect(Collectors.toList());
      log.info("Numbers sorted: {}", numbersSorted);

      // collect (to set)
      Set<Integer> sqNumSet = numbers.stream().map(n -> n * n).collect(Collectors.toSet());
      log.info("Numbers squared set: {}", sqNumSet);

      // forEach
      languages.stream().filter(s -> s.contains("Java")).forEach(l -> log.info("{}", l));

      // reduce
      /*
       * Explanation of stream().reduce()
       *
       * filter(): filter down to only even numbers
       * reduce(): reduce elements to a single value, in this case calculating the sum of all
       *           even numbers in the List<>
       *
       */
      int sumOfEvenNumbers =
          numbers.stream()
              .filter(n -> n % 2 == 0)
              .reduce(
                  0,
                  (sum, n) -> {
                    log.info("sum: {}, n: {}", sum, n);
                    return sum + n;
                  });
      log.info("{}", sumOfEvenNumbers);
    }
  }
}
