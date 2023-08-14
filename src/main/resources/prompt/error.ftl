I need you to fix an error in a unit test, an error occurred while compiling and executing

The unit test is:
```
${unit_test}
```

The error message is:
```
${error_message}
```

The unit test is testing the method `${method_name}` in the class `${class_name}`,
the source code of the method under test and its class is:
```
${method_code}
```
<#if other_methods_code?has_content>
```
The signatures of other methods in its class are `${other_methods_code}`
```
</#if>

Please fix the error and return the whole fixed unit test. You can use Junit 5, Mockito 3 and reflection. No explanation is needed.