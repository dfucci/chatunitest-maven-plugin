package zju.cst.aces.utils;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilder;

import java.util.Random;

public class Config {

    public static MavenSession session;
    public static MavenProject project;
    public static DependencyGraphBuilder dependencyGraphBuilder;
    public static int testNumber;
    public static int timeOut;
    public static int processNumber;
    public static String resultDir;
    public static String projectDir;
    public static int maxRounds;
    public static int MAX_PROMPT_TOKENS;
    public static int minErrorTokens;
    public static String model;
    public static Double temperature;
    public static int topP;
    public static int frequencyPenalty;
    public static int presencePenalty;
    public static String[] apiKeys;

    public static void setSession(MavenSession session) {
        Config.session = session;
    }

    public static void setProject(MavenProject project) {
        Config.project = project;
    }

    public static void setDependencyGraphBuilder(DependencyGraphBuilder dependencyGraphBuilder) {
        Config.dependencyGraphBuilder = dependencyGraphBuilder;
    }

    public static void setApiKeys(String[] apiKeys) {
        Config.apiKeys = apiKeys;
    }

    public static void setTestNumber(int testNumber) {
        Config.testNumber = testNumber;
    }

    public static void setTimeOut(int timeOut) {
        Config.timeOut = timeOut;
    }

    public static void setProcessNumber(int processNumber) {
        Config.processNumber = processNumber;
    }

    public static void setResultDir(String resultDir) {
        Config.resultDir = resultDir;
    }

    public static void setProjectDir(String projectDir) {
        Config.projectDir = projectDir;
    }

    public static void setMaxRounds(int maxRounds) {
        Config.maxRounds = maxRounds;
    }

    public static void setMAX_PROMPT_TOKENS(int MAX_PROMPT_TOKENS) {
        Config.MAX_PROMPT_TOKENS = MAX_PROMPT_TOKENS;
    }

    public static void setMinErrorTokens(int minErrorTokens) {
        Config.minErrorTokens = minErrorTokens;
    }

    public static void setModel(String model) {
        Config.model = model;
    }

    public static void setTemperature(Double temperature) {
        Config.temperature = temperature;
    }

    public static void setTopP(int topP) {
        Config.topP = topP;
    }

    public static void setFrequencyPenalty(int frequencyPenalty) {
        Config.frequencyPenalty = frequencyPenalty;
    }

    public static void setPresencePenalty(int presencePenalty) {
        Config.presencePenalty = presencePenalty;
    }

    public static String getRandomKey() {
        Random rand = new Random();
        if (apiKeys == null) {
            throw new RuntimeException("apiKeys is null");
        }
        String apiKey = apiKeys[rand.nextInt(apiKeys.length)];
        return apiKey;
    }
}
