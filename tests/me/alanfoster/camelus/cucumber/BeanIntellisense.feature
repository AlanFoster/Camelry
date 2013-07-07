Feature: Blueprint Bean Injection Language

  Scenario: A developer invokes a simple completion
    Given a blueprint file which has the following beans
    """
    <bean id="one" class="java.lang.String" />
    <bean id="two" class="java.lang.String" />
    <bean id="three" class="java.lang.String" />
  """
    And the user has the following camel route
    """
      <route>
        <from uri="direct:a" />
        <bean ref="<caret>" />
      </route>
    """
    When the user asks for completion
    Then the following variants will be shown
      | one   |
      | two   |
      | three |

  Scenario: A developer invokes completion with beans in multiple modules
    Given a blueprint file which has the following beans
    """
    <bean id="one" class="java.lang.String" />
    <bean id="two" class="java.lang.String" />
    <bean id="three" class="java.lang.String" />
  """
    And in another module there are the following beans
    """
    <bean id="external-one" class="java.lang.String" />
    <bean id="external-two" class="java.lang.String" />
    <bean id="external-three" class="java.lang.String" />
  """
    And the user has the following camel route
    """
      <route>
        <from uri="direct:a" />
        <bean ref="<caret>" />
      </route>
    """
    When the user asks for completion
    Then the following variants will be shown
      | one   |
      | two   |
      | three |
