package pl.gov.coi.cleanarchitecture.example.spring.pets.persistance;

import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
class ObjectSerializer<T> {
  T refresh(T instance) {
    return unserialize(serialize(instance));
  }

  private byte[] serialize(T target) {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(target);
      out.flush();
      return bos.toByteArray();
    } catch (IOException e) {
      throw new EidIllegalStateException("20161219:162734", e);
    }
  }

  @SuppressWarnings("unchecked")
  private T unserialize(byte[] bytes) {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
         ObjectInput in = new ObjectInputStream(bis)) {
      return (T) in.readObject();
    } catch (ClassNotFoundException | IOException ex) {
      throw new EidIllegalStateException("20161219:163039", ex);
    }
  }
}
