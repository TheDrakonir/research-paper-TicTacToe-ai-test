package de.opicht.ft.facharbeit;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import de.opicht.ft.facharbeit.agents.Agent;

public class GameDispatcher {

    public GameDispatcher(int gameCount, Agent[] agents) {
        int logicalCoreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(logicalCoreCount);

        Set<Future<SimulationResult>> runner = ConcurrentHashMap.newKeySet();
        for (int i = 0; i < logicalCoreCount; i++) {
            Agent[] actualAgents = new Agent[2];
            actualAgents[0] = agents[0].getCopy();
            actualAgents[1] = agents[1].getCopy();
            runner.add(executorService
                    .submit(new GameRunner((int) Math.ceil((double) gameCount / logicalCoreCount), actualAgents)));
        }

        SimulationResult endResult = new SimulationResult(0, 0, agents[0].getAgentIdentifier(), 0, Duration.ZERO,
                agents[1].getAgentIdentifier(), 0, Duration.ZERO);
        while (!runner.isEmpty()) {
            try {
                for (Future<SimulationResult> future : runner) {
                    if (future.isDone()) {
                        endResult = endResult.combine(future.get());
                        runner.remove(future);
                    }
                }

                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        endResult.printStats();
        executorService.shutdown();
    }

}
