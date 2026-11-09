

\# Parallel and Distributed Computing Lab Assignment 2



\## Student Information



\- \*\*Course:\*\* CS-426L - Parallel and Distributed Computing Lab

\- \*\*Semester:\*\* Fall 2026

\- \*\*University:\*\* Air University, Kharian Campus

\- \*\*Department:\*\* Computer Science

\- \*\*Registration Number:\*\* 247194

\- \*\*Section:\*\* BSCYS-F-24-B

\- \*\*GitHub Repository:\*\* https://github.com/247194-cloud/PDC-Assignment-2



\---



\## Project Overview



This repository contains seven Java programming tasks developed for Parallel and Distributed Computing Lab Assignment 2.



The assignment demonstrates object-oriented programming, data structures, multithreading, parallel processing, concurrent collections, and cache locality.



\## Question 1 - Event Dispatcher



\*\*Folder:\*\* `Q1\_EventDispatcher`



\*\*Main files:\*\*

\- TradeEvent.java

\- FIXOrderEvent.java

\- ReutersQuoteEvent.java

\- TradingEngineApp.java



\*\*Concepts demonstrated:\*\*

\- Inheritance

\- Method overriding

\- Runtime polymorphism

\- Constructor initialization

\- Trade event processing

\- FIX purchase fee calculation

\- Reuters bid-ask spread calculation



\*\*Verified results:\*\*

\- FIX processing fee: 15.0

\- Total purchase amount: 10015.0

\- Reuters spread: 0.75



\## Question 2 - Packet Verification



\*\*Folder:\*\* `Q2\_PacketVerification`



\*\*Main files:\*\*

\- TransactionStack.java

\- RequestQueue.java

\- PacketSymmetryAuditor.java



\*\*Concepts demonstrated:\*\*

\- Array-based stack

\- Array-based queue

\- LIFO and FIFO operations

\- Push, pop, peek, enqueue and dequeue

\- Packet symmetry verification



\*\*Verified results:\*\*

\- ABBA: Symmetrical = true

\- ABCD: Symmetrical = false



\## Question 3 - Multithreaded Telemetry Engine



\*\*Folder:\*\* `Q3\_TelemetryEngine`



\*\*Main files:\*\*

\- LogProcessorTask.java

\- TelemetryThread.java

\- AsyncWebEngineApp.java



\*\*Concepts demonstrated:\*\*

\- Runnable interface

\- Thread class

\- Concurrent execution

\- Thread naming

\- Thread.sleep()

\- Thread.join()

\- Thread execution states



\*\*Implementation details:\*\*

\- Five iterations per worker

\- Log worker sleep interval: 1000 ms

\- Telemetry worker sleep interval: 800 ms

\- Worker names: Log-Worker and Telemetry-Worker



Both workers complete their execution before the main program terminates.



\## Question 4 - Parallel Risk Calculator



\*\*Folder:\*\* `Q4\_RiskCalculator`



\*\*Main files:\*\*

\- ParallelRiskCalculator.java

\- PortfolioThresholdTask.java

\- ForkJoinPortfolioApp.java



\*\*Concepts demonstrated:\*\*

\- Fork/Join framework

\- RecursiveTask

\- RecursiveAction

\- ForkJoinPool

\- Parallel processing

\- Task decomposition



\*\*Implementation details:\*\*

\- Portfolio size: 10000

\- Task chunk size: 1000

\- Risk limit: 10000.0

\- Stress penalty: 5%



\*\*Verified results:\*\*

\- Total portfolio risk before stress: 7.425E7

\- Values above risk limit after adjustment: 3300



\## Question 5 - Parallel Sensor Search



\*\*Folder:\*\* `Q5\_SensorSearch`



\*\*Main files:\*\*

\- SensorSearchEngine.java

\- ParallelMinSearch.java

\- ParallelMaxSearch.java

\- SensorGridMonitorApp.java



\*\*Concepts demonstrated:\*\*

\- Abstract classes

\- Method overriding

\- Parallel minimum search

\- Parallel maximum search

\- Multithreading

\- Work division among threads



\*\*Implementation details:\*\*

\- Total sensor readings: 50000

\- Worker threads: 4



\*\*Verified results:\*\*

\- Global minimum: 0.0

\- Global maximum: 9999.0



\## Question 6 - Inventory Concurrency Benchmark



\*\*Folder:\*\* `Q6\_InventoryBenchmark`



\*\*Main files:\*\*

\- InventoryCatalogManager.java

\- InventoryBenchmarkApp.java



\*\*Concepts demonstrated:\*\*

\- HashMap

\- Collections.synchronizedMap()

\- ConcurrentHashMap

\- Concurrent lookups and updates

\- ExecutorService

\- Performance measurement

\- Throughput calculation



\*\*Implementation details:\*\*

\- Products in catalog: 1000

\- Worker threads: 10

\- Operations per worker: 50000

\- Total operations per benchmark: 500000

\- Product IDs: String

\- Stock values: Integer



\*\*Observed benchmark results:\*\*

\- SynchronizedMap time: 243.21 ms

\- ConcurrentHashMap time: 130.24 ms

\- SynchronizedMap throughput: 2055806.09 operations/second

\- ConcurrentHashMap throughput: 3838924.86 operations/second



ConcurrentHashMap was faster in this recorded execution. Performance can vary between runs and computers.



\## Question 7 - Cache Locality Benchmark



\*\*Folder:\*\* `Q7\_CacheLocality`



\*\*Main file:\*\*

\- CacheLocalityBenchmark.java



\*\*Concepts demonstrated:\*\*

\- Two-dimensional double arrays

\- Row-major traversal

\- Column-major traversal

\- System.nanoTime()

\- Cache lines

\- Spatial locality

\- Execution time comparison



\*\*Implementation details:\*\*

\- Matrix dimensions: 2000 x 2000

\- Data type: double

\- Benchmark iterations: 10

\- Two warm-up iterations

\- Total and average execution time

\- Row-major versus column-major speed ratio



\*\*Expected checksum:\*\* 7996000000



Execution times and speed ratios depend on the computer and JVM.



\---



\## How to Compile and Run



Open CMD and navigate to the required question folder.



Compile all Java source files:



```cmd

javac \*.java

```



Run the appropriate main class:



| Question | Main class |

|---|---|

| Q1 | TradingEngineApp |

| Q2 | PacketSymmetryAuditor |

| Q3 | AsyncWebEngineApp |

| Q4 | ForkJoinPortfolioApp |

| Q5 | SensorGridMonitorApp |

| Q6 | InventoryBenchmarkApp |

| Q7 | CacheLocalityBenchmark |



Example for Question 6:



```cmd

cd /d "C:\\Users\\Windows 11\\Desktop\\PDC\_Lab\_Assignment\_2\\Q6\_InventoryBenchmark"

javac \*.java

java InventoryBenchmarkApp

```



\---



\## Repository Structure



```text

PDC\_Lab\_Assignment\_2

|

|-- Q1\_EventDispatcher

|-- Q2\_PacketVerification

|-- Q3\_TelemetryEngine

|-- Q4\_RiskCalculator

|-- Q5\_SensorSearch

|-- Q6\_InventoryBenchmark

|-- Q7\_CacheLocality

|-- verify-all.bat

|-- README.md

|-- .gitignore

```



\---



\## Technologies Used



\- Java

\- Object-Oriented Programming

\- Java Collections Framework

\- Multithreading

\- ExecutorService

\- Fork/Join Framework

\- ConcurrentHashMap

\- Git and GitHub



\## Conclusion



This assignment demonstrates important concepts of parallel and distributed computing through seven Java programming tasks.



The programs cover event processing, packet verification, multithreading, parallel risk calculation, sensor searching, concurrent inventory management, and memory cache locality.



