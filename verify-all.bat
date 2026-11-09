
@echo off
setlocal
cd /d "%~dp0"

if not exist out mkdir out

echo ========================================
echo PDC LAB ASSIGNMENT 2 - VERIFICATION
echo ========================================

echo.
echo [Q1] Event Dispatcher
javac -d out Q1_EventDispatcher\TradeEvent.java Q1_EventDispatcher\FIXOrderEvent.java Q1_EventDispatcher\ReutersQuoteEvent.java Q1_EventDispatcher\TradingEngineApp.java
if errorlevel 1 goto failed
java -cp out TradingEngineApp
if errorlevel 1 goto failed

echo.
echo [Q2] Packet Verification
javac -d out Q2_PacketVerification\TransactionStack.java Q2_PacketVerification\RequestQueue.java Q2_PacketVerification\PacketSymmetryAuditor.java
if errorlevel 1 goto failed
java -cp out PacketSymmetryAuditor
if errorlevel 1 goto failed

echo.
echo [Q3] Telemetry Engine
javac -d out Q3_TelemetryEngine\LogProcessorTask.java Q3_TelemetryEngine\TelemetryThread.java Q3_TelemetryEngine\AsyncWebEngineApp.java
if errorlevel 1 goto failed
java -cp out AsyncWebEngineApp
if errorlevel 1 goto failed

echo.
echo [Q4] Risk Calculator
javac -d out Q4_RiskCalculator\ParallelRiskCalculator.java Q4_RiskCalculator\PortfolioThresholdTask.java Q4_RiskCalculator\ForkJoinPortfolioApp.java
if errorlevel 1 goto failed
java -cp out ForkJoinPortfolioApp
if errorlevel 1 goto failed

echo.
echo [Q5] Sensor Search
javac -d out Q5_SensorSearch\SensorSearchEngine.java Q5_SensorSearch\ParallelMinSearch.java Q5_SensorSearch\ParallelMaxSearch.java Q5_SensorSearch\SensorGridMonitorApp.java
if errorlevel 1 goto failed
java -cp out SensorGridMonitorApp
if errorlevel 1 goto failed

echo.
echo [Q6] Inventory Benchmark
javac -d out Q6_InventoryBenchmark\InventoryCatalogManager.java Q6_InventoryBenchmark\InventoryBenchmarkApp.java
if errorlevel 1 goto failed
java -cp out InventoryBenchmarkApp
if errorlevel 1 goto failed

echo.
echo [Q7] Cache Locality
javac -d out Q7_CacheLocality\CacheLocalityBenchmark.java
if errorlevel 1 goto failed
java -cp out CacheLocalityBenchmark
if errorlevel 1 goto failed

echo.
echo ========================================
echo ALL SEVEN PROGRAMS PASSED
echo ========================================
exit /b 0

:failed
echo.
echo VERIFICATION FAILED. CHECK THE ERROR ABOVE.
exit /b 1
