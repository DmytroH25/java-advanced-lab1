# Stream API

## Author
Dmytro Hordun IA-22
https://t.me/hordunchikkk

## Project Description
This project is a Java-based application that models a collection of cars with specific properties such as brand, months since manufacture, class, and price in UAH. It includes features for:
- Generating a large collection of random `Car` objects
- Filtering and grouping cars by their class
- Analyzing car prices with statistical measures, including average, min, max, and standard deviation
- Detecting outliers in car prices using interquartile range (IQR) calculations

The program demonstrates the use of Java Streams, custom collectors, and basic statistical analysis.

## Requirements
- **Java JDK** version 22 or higher

## Project Structure
- `Car.java`: Defines the `Car` class, which includes attributes and a method for generating random car objects.
- `Main.java`: The main application logic, which generates, filters, groups, and analyzes the car data.
- `Statistics.java`: A helper class that holds statistical information about car prices.

## Build and Run Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/DmytroH25/java-advanced-lab1.git
