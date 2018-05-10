package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.springframework.stereotype.Service;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-10
 */
@Service
final class JavaSerializer implements Serializer {
  @Override
  public String serialize(Serializable serializable) {
    if (serializable instanceof Long) {
      return Long.toString((Long) serializable);
    }
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(serializable);
      out.flush();
      return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(bos.toByteArray());
    } catch (IOException e) {
      throw new EidIllegalStateException("20180509:143244", e);
    }
  }

  @Override
  public Serializable unserialize(String serialized) throws IOException, ClassNotFoundException {
    try {
      return Long.valueOf(serialized);
    } catch (NumberFormatException ex) {
      byte[] bytes = Base64
        .getUrlDecoder()
        .decode(serialized);
      try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
           ObjectInput in = new ObjectInputStream(bis)) {
        return (Serializable) in.readObject();
      }
    }
  }
}
