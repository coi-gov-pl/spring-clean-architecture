package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.ExceptionMethod.ConstructionDelegate;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.isConstructor;
import static net.bytebuddy.matcher.ElementMatchers.noneOf;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
final class LazyObjectFactory {
  <I, T extends I> T newLazy(Class<I> cls, String eid) {
    try {
      return newLazyUnsafely(cls, eid);
    } catch (NoSuchMethodException |
      IllegalAccessException |
      InstantiationException ex) {
      throw new EidIllegalStateException("20180517:110633", ex);
    }
  }

  private <I, T extends I> T newLazyUnsafely(Class<I> cls, String eid) throws
    NoSuchMethodException, IllegalAccessException, InstantiationException {

    Method toStringMethod = Object.class.getDeclaredMethod("toString");
    DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
      .subclass(cls)
      .method(noneOf(toStringMethod))
      .intercept(throwingEid(eid))
      .make();
    DynamicType.Loaded<?> loaded = dynamicType.load(getClass().getClassLoader());
    @SuppressWarnings("unchecked")
    Class<T> resultCls = (Class<T>) loaded.getLoaded();
    return resultCls.newInstance();
  }

  private Implementation throwingEid(String eid) {
    Class<EidIllegalStateException> cls = EidIllegalStateException.class;
    TypeDescription typeDescription = new TypeDescription.ForLoadedType(
      cls
    );
    ConstructionDelegate constr = new EidConstructionDelegate(typeDescription, eid);
    return new ExceptionMethod(typeDescription, constr);
  }

  private static final class EidConstructionDelegate implements ConstructionDelegate {
    private final TypeDescription exceptionType;
    private final MethodDescription targetConstructor;
    private final MethodDescription eidConstructor;
    private final TypeDescription eidType;
    private final String eid;

    private EidConstructionDelegate(TypeDescription exceptionType, String eid) {
      this.exceptionType = exceptionType;
      this.targetConstructor = exceptionType
        .getDeclaredMethods()
        .filter(
          isConstructor()
            .and(takesArguments(Eid.class))
        ).getOnly();
      this.eid = eid;
      this.eidType = new TypeDescription.ForLoadedType(Eid.class);
      this.eidConstructor = eidType
        .getDeclaredMethods()
        .filter(
          isConstructor()
            .and(takesArguments(String.class))
        ).getOnly();
    }

    @Override
    public StackManipulation make() {
      return new StackManipulation.Compound(
        TypeCreation.of(exceptionType),
        Duplication.SINGLE,
        eidCreation(),
        MethodInvocation.invoke(targetConstructor));
    }

    private StackManipulation eidCreation() {
      return new StackManipulation.Compound(
        TypeCreation.of(eidType),
        Duplication.SINGLE,
        new TextConstant(eid),
        MethodInvocation.invoke(eidConstructor));
    }
  }
}
