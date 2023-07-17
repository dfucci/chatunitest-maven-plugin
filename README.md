# :mega: ChatUnitest Maven Plugin

[English](./README.md) | [中文](./Readme_zh.md)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.ZJU-ACES-ISE/chatunitest-maven-plugin?color=hex&style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.github.ZJU-ACES-ISE/chatunitest-maven-plugin)

## Updates:
💥 We have recently added a multithreading feature to generate tests more quickly!

## Background
Many people have tried using ChatGPT to help them with various programming tasks and have achieved good results. However, there are some issues with using ChatGPT directly. Firstly, the generated code often fails to execute correctly, leading to the famous saying **"five minutes to code, two hours to debug"**. Secondly, it is inconvenient to integrate with existing projects as it requires manual interaction with ChatGPT and switching between different platforms. To address these problems, we have proposed the **"Generate-Validate-Repair"** framework and implemented a prototype. Additionally, to make it easier for everyone to use, we have developed some plugins that can be seamlessly integrated into existing development workflows.

## Steps to run

### 0. Add our plugin to `pom.xml` and config

You can configure the plugin with the following parameters to your `pom.xml` file:

```xml

<plugin>
    <groupId>io.github.ZJU-ACES-ISE</groupId>
    <artifactId>chatunitest-maven-plugin</artifactId>
    <version>1.1.0</version>
    <configuration>
        <!-- Required: You must specify your OpenAI API keys. -->
        <apiKeys></apiKeys>
        <model>gpt-3.5-turbo</model>
        <testNumber>5</testNumber>
        <maxRounds>5</maxRounds>
        <minErrorTokens>500</minErrorTokens>
        <temperature>0.5</temperature>
        <topP>1</topP>
        <frequencyPenalty>0</frequencyPenalty>
        <presencePenalty>0</presencePenalty>
        <proxy>${proxy}</proxy>
    </configuration>
</plugin>
```

**Here's a detailed explanation of each configuration option:**

- `apiKeys`: (**Required**) Your OpenAI API keys. Example: `Key1, Key2, ...`.
- `model`: (**Optional**) The OpenAI model. Default: `gpt-3.5-turbo`.
- `testNumber`: (**Optional**) The number of tests for each method. Default: `5`.
- `maxRounds`: (**Optional**) The maximum rounds of the repair process. Default: `5`.
- `minErrorTokens`: (**Optional**) The minimum tokens of error message in the repair process. Default: `500`.
- `temperature`: (**Optional**) The OpenAI API parameters. Default: `0.5`.
- `topP`: (**Optional**) The OpenAI API parameters. Default: `1`.
- `frequencyPenalty`: (**Optional**) The OpenAI API parameters. Default: `0`.
- `presencePenalty`: (**Optional**) The OpenAI API parameters. Default: `0`.
- `proxy`: (**Optional**) Your host name and port number if you need. Example:`127.0.0.1:7078`.
- `selectClass`: (**Optional**) Class under test. If the project contains the same class name in different packages, the className should be specified as the fully qualified name.
- `selectMethod`: (**Optional**) Method under test.
- `tmpOutput`: (**Optional**) The output path for parsed information. Default: `/tmp/chatunitest-info`.
- `testOutput`: (**Optional**) The output path for tests generated by `chatunitest`. Default: `{basedir}/chatunitest`.
- `project`: (**Optional**) The target project path. Default: `{basedir}`.
- `thread`: (**Optional**) Enable multi-threaded execution. Default: `true`.
- `maxThread`: (**Optional**) The maximum number of threads. Default: `CPU cores * 5`.
- `stopWhenSuccess`: (**Optional**) Stop the repair process when the test passes. Default: `true`.
- `noExecution`: (**Optional**) Skip the execution verification step of generated tests. Default: `false`.

All these parameters also can be specified in the command line with `-D` option.

Essentially, the only thing you need to provide are your API keys.

### 1. Add the following dependency to pom.xml

```xml
<dependency>
    <groupId>io.github.ZJU-ACES-ISE</groupId>
    <artifactId>chatunitest-starter</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
</dependency>
```

### 2. Run

**First, you need to download all necessary dependencies, which can be done by running `mvn compile` or `mvn install` command.**

**You can run the plugin with the following command:**

**Generate unit tests for the target method:**

```shell
mvn chatunitest:method -DselectMethod=className#methodName
```

**Generate unit tests for the target class:**

```shell
mvn chatunitest:class -DselectClass=className
```

You must specify `selectMethod` and `selectClass` when executing `mvn chatunitest:method` or `mvn chatunitest:class`.
This is done using the -D option.

Example:

```
public class Example {
    public void method1(Type1 p1, ...) {...}
    public void method2() {...}
    ...
}
```

To test the class `Example` and all methods in it:

```shell
mvn chatunitest:class -DselectClass=Example
```

To test the method `method1` in the class `Example` (Now ChatUnitest will generate tests for all methods named method1
in the class)

```shell
mvn chatunitest:method -DselectMethod=Example#method1
```

**Generate unit tests for the whole project:**

:warning: :warning: :warning: For a large project, it may consume a significant number of tokens, resulting in a
substantial bill.

```shell
mvn chatunitest:project
```

**Clean the generated tests:**

```shell
mvn chatunitest:clean
```
Running this command will delete all generated tests and restore your test folder.


**Run the generated tests manually:**

```shell
mvn chatunitest:copy
```
Running this command will copy the generated tests into the `src/test/java/` directory for your convenience when you want
to run the tests manually. Your test folder will be backed up in the `src/back/` directory.

```shell
mvn chatunitest:restore
```

Running this command will restore the test folder from the backup in the `src/back/` directory.

## Requirements

This Maven plugin can be executed in various operating systems with different Java Development Kits (JDK) and Maven
versions. The following environments have been tested and proven to work:

- Environment 1: Windows 11 / Oracle JDK 11 / Maven 3.9
- Environment 2: Windows 10 / Oracle JDK 11 / Maven 3.6
- Environment 3: Ubuntu 22.04 / OpenJDK 11 / Maven 3.6
- Environment 4: Darwin Kernel 22.1.0 / Oracle JDK 11 / Maven 3.8

Please note that these environments are tested and known to work. You can also try running the plugin in similar
environments. If you encounter any issues in other environments, please refer to the documentation or seek appropriate
support.

## :construction: TODO

- Add code obfuscation to avoid sending the original code to ChatGPT.
- Add expense estimation and quota.
- Optimize the structure of generated test cases.

## MISC

Our work has been submitted to arXiv. Check it out here: [ChatUniTest](https://arxiv.org/abs/2305.04764).

```
@misc{xie2023chatunitest,
      title={ChatUniTest: a ChatGPT-based automated unit test generation tool}, 
      author={Zhuokui Xie and Yinghao Chen and Chen Zhi and Shuiguang Deng and Jianwei Yin},
      year={2023},
      eprint={2305.04764},
      archivePrefix={arXiv},
      primaryClass={cs.SE}
}
```

## :email: Contact us

If you have any questions or would like to inquire about our experimental results, please feel free to contact us via
email. The email addresses of the authors are as follows:

1. Corresponding author: `zjuzhichen AT zju.edu.cn`
2. Author: `yh_ch AT zju.edu.cn`, `xiezhuokui AT zju.edu.cn`
