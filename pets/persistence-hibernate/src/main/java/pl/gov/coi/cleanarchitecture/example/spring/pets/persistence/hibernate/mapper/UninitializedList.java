package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.04.18
 */
@RequiredArgsConstructor
final class UninitializedList<T> implements List<T> {

  private final Class<?> type;

  @Override
  public int size() {
    throw newLazyInitializationException();
  }

  @Override
  public boolean isEmpty() {
    throw newLazyInitializationException();
  }

  @Override
  public boolean contains(Object o) {
    throw newLazyInitializationException();
  }

  @Override
  public Iterator<T> iterator() {
    throw newLazyInitializationException();
  }

  @Override
  public Object[] toArray() {
    throw newLazyInitializationException();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean add(T t) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean remove(Object o) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw newLazyInitializationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw newLazyInitializationException();
  }

  @Override
  public void clear() {
    throw newLazyInitializationException();
  }

  @Override
  public T get(int index) {
    throw newLazyInitializationException();
  }

  @Override
  public T set(int index, T element) {
    throw newLazyInitializationException();
  }

  @Override
  public void add(int index, T element) {
    throw newLazyInitializationException();
  }

  @Override
  public T remove(int index) {
    throw newLazyInitializationException();
  }

  @Override
  public int indexOf(Object o) {
    throw newLazyInitializationException();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw newLazyInitializationException();
  }

  @Override
  public ListIterator<T> listIterator() {
    throw newLazyInitializationException();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw newLazyInitializationException();
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw newLazyInitializationException();
  }

  private LazyInitializationException newLazyInitializationException() {
    return new LazyInitializationException(
      "Trying to use uninitialized collection for type: List<"
        + type.getSimpleName()
        + ">. You need to fetch this collection before using it." +
        " This prevents lazy loading n+1 problem."
    );
  }
}
