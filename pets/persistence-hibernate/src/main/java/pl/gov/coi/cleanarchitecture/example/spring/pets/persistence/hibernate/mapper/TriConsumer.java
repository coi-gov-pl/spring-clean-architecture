package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts tree input arguments and returns no
 * result.  This is the tree-arity specialization of {@link Consumer}.
 * Unlike most other functional interfaces, {@code TriConsumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object, Object)}.
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 * @param <V> the type of the third argument to the operation
 *
 * @see Consumer
 * @since 1.8
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 25.04.18
 */
public interface TriConsumer<T, U, V> {
  /**
   * Performs this operation on the given arguments.
   *
   * @param t the first input argument
   * @param u the second input argument
   * @param v the third input argument
   */
  void accept(T t, U u, V v);

  /**
   * Returns a composed {@code TriConsumer} that performs, in sequence, this
   * operation followed by the {@code after} operation. If performing either
   * operation throws an exception, it is relayed to the caller of the
   * composed operation.  If performing this operation throws an exception,
   * the {@code after} operation will not be performed.
   *
   * @param after the operation to perform after this operation
   * @return a composed {@code TriConsumer} that performs in sequence this
   * operation followed by the {@code after} operation
   * @throws NullPointerException if {@code after} is null
   */
  default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
    Objects.requireNonNull(after);

    return (f, s, t) -> {
      accept(f, s, t);
      after.accept(f, s, t);
    };
  }
}
