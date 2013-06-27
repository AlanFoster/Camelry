Feature: ThrowException DOM feature

  Background:
    Given I have the following blueprint bean definitions
    """
      <!-- The base class Throwable -->
      <bean class="java.lang.Throwable" id="throwableBean" />
      <!-- A class which has a super class Throwable -->
      <bean class="java.lang.Exception" id="exceptionBean" />
      <!-- A Bean which does does not inherit Throwable -->
      <bean class="java.lang.String" id="unrelatedBean" />
   """

  Scenario: ThrowException classes are only suggested to the USER
    Given the user has the following camel route
    """
    <camelContext xmlns="http://camel.apache.org/schema/blueprint">
        <route>
            <from uri="direct:entry"/>
            <throwException ref="<caret>" />
        </route>
    </camelContext>
  """
    When the user asks for completion
    Then the following variants will be shown
      | throwableBean |
      | exceptionBean |

  Scenario: When a non throwable bean is used, the error is highlighted
    Given the user has the following camel route
    """
    <camelContext xmlns="http://camel.apache.org/schema/blueprint">
        <route>
            <from uri="direct:entry"/>
            <throwException ref="unrelatedBean" />
        </route>
    </camelContext>
  """
    Then the non-throwable bean will be highlighted as an error
    """
    <camelContext xmlns="http://camel.apache.org/schema/blueprint">
        <route>
            <from uri="direct:entry"/>
            <throwException ref="<error descr="Unresolved Property">unrelatedBean</error>" />
        </route>
    </camelContext>
  """