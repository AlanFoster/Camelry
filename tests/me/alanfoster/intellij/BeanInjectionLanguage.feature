Feature: Blueprint Bean Injection Language
  As a developer
  I want to have support for the Blueprint Bean injection Language
  In order to be more productive

  Background:
    Given I have the following property place holder definition
    """
      <cm:property-placeholder id="placeholder" persistent-id="placeholder">
        <cm:default-properties>
            <cm:property name="Hello" value="hello" />
            <cm:property name="World" value="world" />
        </cm:default-properties>
    </cm:property-placeholder>
  """

  Scenario: A developer invokes a simple completion
    Given the user has their caret at the following location
    """
    <bean class="foo.Helper" id="helper">
        <property name="foo" value="${<caret>}" />
    </bean>
  """
    When the user asks for completion
    Then the following variants will be shown
      | Hello |
      | Foo   |

  Scenario: A developer invokes intellisense with a more complex string
    Given the user has their caret at the following location
    """
    <bean class="foo.Helper" id="helper">
        <property name="foo" value="${Hello} ${<caret>}" />
    </bean>
  """
    When the user asks for completion
    Then the following variants will be shown
      | Hello |
      | Foo   |
    And there should be no annotation errors

  Scenario: A developer is warned when a reference does not exist
    When the user references a property that does not exist
    Then the user will be shown the following error
    """
    <bean class="foo.Helper" id="helper">
        <property name="foo" value="${<error descr="Unresolved Property">Foo</error>}" />
    </bean>
  """


  Scenario: Folding should automatically occur
    Given the following bean definition
    """
    <bean class="foo.Helper" id="helper">
      <property name="foo" value="${Hello} ${World}! ${Foo}" />
    </bean>
  """
    Then the folding should look like the following
    """
        <bean class="foo.Helper" id="helper">
        <property name="foo" value="hello} world! ${<error descr="Unresolved Property">Foo</error>}" />
    </bean>
    """