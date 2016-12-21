package pl.gov.coi.cleanarchitecture.usecase;

/**
 * This is a interface that represents a business use case. It's designed after ideas
 * of Robert C. Martin aka "Uncle Bob" that can be found by catchy name "Clean Architecture".
 * <p>
 * Use case will have only one method - {@link #execute(Request, Response)}. This method takes
 * a pair of request and response objects that should be instantiated externally in plugin way
 * pattern. That ensures decoupling of presentation from domain.
 * <p>
 * Usually response object is being implemented by {@link pl.gov.coi.cleanarchitecture.presentation.Presenter} object.
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@FunctionalInterface
public interface UseCase<I extends Request, O extends Response> {

  /**
   * Executes a business use case. Request and response object should be given
   * @param request a request for use case
   * @param response a response for use case
   */
  void execute(I request, O response);

}
