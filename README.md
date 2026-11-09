# Parallel and Distributed Computing Lab Assignment 2

**Course:** CS-426L  
**Semester:** Fall 2026  
**University:** Air University, Kharian Campus  
**Department:** Computer Science  
**Student Registration Number:** 247194  
**GitHub:** https://github.com/247194-cloud/PDC-Assignment-2

## Project Overview

This repository contains seven Java programming tasks for the Parallel and Distributed Computing Lab Assignment 2.

## Question 1 — Event Dispatcher

**Folder:** Q1_EventDispatcher

Topics to document:
- Inheritance
- Method overriding
- Runtime polymorphism
- Constructor initialization
- Trade event processing

Main Java files:
- TradeEvent.java
- FIXOrderEvent.java
- ReutersQuoteEvent.java
- TradingEngineApp.java

## Question 2 — Packet Verification

**Folder:** Q2_PacketVerification

Topics to document:
- Array-based stack
- Array-based queue
- LIFO and FIFO
- Packet symmetry detection

Main Java files:
- TransactionStack.java
- TransactionQueue.java
- PacketSymmetryAuditor.java

## Question 3 — Telemetry Engine

**Folder:** Q3_TelemetryEngine

Topics to document:
- Java Thread class
- Runnable interface
- Concurrent execution
- Thread naming and sleep intervals

Main Java files:
- LogProcessorTask.java
- TelemetryThread.java
- AsyncWebEngineApp.java

## Question 4 — Parallel Risk Calculator

**Folder:** Q4_RiskCalculator

Topics to document:
- Fork/Join framework
- RecursiveTask
- RecursiveAction
- Parallel processing

Main Java files:
- ParallelRiskCalculator.java
- PortfolioThresholdTask.java
- RiskPortfolioApp.java

## Question 5 — Sensor Search

**Folder:** Q5_SensorSearch

Topics to document:
- Abstract classes
- Method overriding
- Parallel minimum search
- Parallel maximum search
- Worker threads

Main Java files:
- SensorSearchEngine.java
- ParallelMinSearch.java
- ParallelMaxSearch.java
- SensorGridMonitorApp.java

## Question 6 — Inventory Benchmark

**Folder:** Q6_InventoryBenchmark

Topics to document:
- HashMap
- Collections.synchronizedMap()
- ConcurrentHashMap
- Concurrent operations
- Execution time and throughput

Main Java files:
- InventoryCatalogManager.java
- InventoryBenchmarkApp.java

## Question 7 — Cache Locality Benchmark

**Folder:** Q7_CacheLocality

Topics to document:
- Two-dimensional arrays
- Row-major traversal
- Column-major traversal
- System.nanoTime()
- Cache locality and spatial locality

Main Java file:
- CacheLocalityBenchmark.java

## How to Run the Programs

Open the required question folder in VS Code, compile its Java files, and run the relevant main class.

## Repository Contents

This repository contains the source-code folders for all seven assignment questions.
## Question 1 - Compilation and Execution

Compile: javac -d out Q1_EventDispatcher\TradeEvent.java Q1_EventDispatcher\FIXOrderEvent.java Q1_EventDispatcher\ReutersQuoteEvent.java Q1_EventDispatcher\TradingEngineApp.java
Run: java -cp out TradingEngineApp
