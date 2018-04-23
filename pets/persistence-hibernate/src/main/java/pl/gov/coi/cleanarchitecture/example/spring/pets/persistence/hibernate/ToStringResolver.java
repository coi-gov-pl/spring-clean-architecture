package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.hibernate.Hibernate;
import pl.wavesoftware.eid.exceptions.EidRuntimeException;

import java.lang.reflect.Field;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 * @deprecated Use <a href="https://github.com/wavesoftware/java-stringify-object">pl.wavesoftware.utils:stringify-object</a>
 */
@Deprecated
public final class ToStringResolver {
  public String toString(Object object) {
    ToStringResolving resolving = new ToStringResolving(object);
    return resolving.resolve();
  }

  private static final class ToStringResolving {
    private static final Object CONTAIN = new Object();

    private final Map<Object, Object> resolved;
    private final Object target;

    ToStringResolving(Object target) {
      this(target, new IdentityHashMap<>());
    }

    ToStringResolving(Object target,
                      Map<Object, Object> resolved) {
      this.resolved = resolved;
      this.target = inspecting(target);
    }

    String resolve() {
      inspecting(target);
      StringBuilder sb = new StringBuilder();
      sb.append('<');
      sb.append(target.getClass().getSimpleName());
      String props = propertiesForToString();
      if (!props.equals("")) {
        sb.append(' ');
        sb.append(props);
      }
      sb.append('>');
      return sb.toString();
    }

    private String propertiesForToString() {
      Map<String, String> props;
      props = inspectTargetAsClass(target.getClass());
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<String, String> entry : props.entrySet()) {
        String fieldName = entry.getKey();
        String fieldStringValue = entry.getValue();
        sb.append(fieldName);
        sb.append("=");
        sb.append(fieldStringValue);
        sb.append(", ");
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }

    private Map<String, String> inspectTargetAsClass(Class<?> type) {
      Class<?> supertype = type.getSuperclass();
      Map<String, String> props;
      if (supertype == null || supertype.equals(Object.class)) {
        props = new LinkedHashMap<>();
      } else {
        props = inspectTargetAsClass(supertype);
      }
      inspectFields(type.getDeclaredFields(), props);
      return props;
    }

    private void inspectFields(Field[] fields,
                               Map<String, String> properties) {
      for (Field field : fields) {
        boolean accessable = field.isAccessible();
        if (!accessable) {
          field.setAccessible(true);
        }
        if (field.isAnnotationPresent(Inspect.class)) {
          Inspect annot = field.getAnnotation(Inspect.class);
          inspectAnnotatedField(properties, field, annot);
        }
      }
    }

    private void inspectAnnotatedField(Map<String, String> properties,
                                       Field field,
                                       Inspect inspect) {
      try {
        Object value = field.get(target);
        if (value == null) {
          if (inspect.showNull()) {
            properties.put(field.getName(), null);
          }
        } else {
          properties.put(field.getName(), inspectObject(value));
        }
      } catch (IllegalArgumentException | IllegalAccessException ex1) {
        throw new EidRuntimeException("20130422:154938", ex1);
      }
    }

    private Object inspecting(Object object) {
      resolved.put(object, CONTAIN);
      return object;
    }

    private boolean wasInspected(Object object) {
      return resolved.containsKey(object);
    }

    private String inspectObject(Object o) {
      if (!Hibernate.isInitialized(o)) {
        return "⁂Lazy";
      } else if (o instanceof String) {
        return "\"" + o.toString() + "\"";
      } else if (o instanceof Character) {
        return "'" + o.toString() + "'";
      } else if (isPrimitive(o)) {
        return o.toString();
      } else if (o instanceof Map) {
        return inspectMap((Map<?,?>) o);
      } else if (o instanceof Iterable) {
        return inspectIterable((Iterable<?>) o);
      } else if (wasInspected(o)) {
        return "(↻)";
      } else {
        ToStringResolving sub = new ToStringResolving(o, resolved);
        return sub.resolve();
      }
    }

    private boolean isPrimitive(Object o) {
      return o instanceof Number
        || o instanceof Boolean
        || o instanceof Enum
        || o instanceof Date
        || o instanceof Temporal;
    }

    private String inspectIterable(Iterable<?> iterable) {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      for (Object elem : iterable) {
        sb.append(inspectObject(elem));
        sb.append(",");
      }
      if (sb.length() > 1) {
        sb.deleteCharAt(sb.length() - 1);
      }
      sb.append("]");
      return sb.toString();
    }

    private String inspectMap(Map<?, ?> map) {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      for (Map.Entry<?, ?> entry : map.entrySet()) {
        Object key = entry.getKey();
        Object value = entry.getValue();
        sb.append(inspectObject(key));
        sb.append(": ");
        sb.append(inspectObject(value));
        sb.append(",");
      }
      if (sb.length() > 1) {
        sb.deleteCharAt(sb.length() - 1);
      }
      sb.append("}");
      return sb.toString();
    }

  }


}
