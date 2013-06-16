This package deals with the Blueprint injection language.

An example of this language can be shown below

```xml
<cm:property-placeholder id="placeholder" persistent-id="placeholder">
    <cm:default-properties>
        <cm:property name="hello.world" value="hello world" />
    </cm:default-properties>
</cm:propert-placeholder>

<bean id="foo" class="foo.bar.Baz">
    <property name="quux" value="${hello.world}" />
</bean>
```
