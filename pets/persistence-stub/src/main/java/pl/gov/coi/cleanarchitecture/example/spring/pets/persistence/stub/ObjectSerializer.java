package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.HasMetadata;
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
final class ObjectSerializer<T> {
  T refresh(T instance) {
    T refreshed = unserialize(serialize(instance));
    if (refreshed instanceof HasMetadata) {
      @SuppressWarnings("unchecked")
      HasMetadata<T> hasMeta = (HasMetadata<T>) refreshed;
      @SuppressWarnings("unchecked")
      HasMetadata<T> inputAsMeta = (HasMetadata<T>) instance;
      if (!hasMeta.isMetadataSet() && inputAsMeta.isMetadataSet()) {
        hasMeta.supplierOfMetadata(inputAsMeta::getMetadata);
      }
    }
    return refreshed;
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
