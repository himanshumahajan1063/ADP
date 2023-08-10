# Coin Change Service

The Coin Change Service is a Spring Boot application that exposes a REST API for calculating the minimum number of coins needed to make change for a given amount. 

## Requirements

- Java 8 or later
- Spring Boot 2.x
- Maven

## Features

- Calculates the minimum number of coins needed for a given amount.
- Handles bad input gracefully.
- Returns change in the form of a JSON response.
- Includes logging for service and controller layers.

## Important points

- I was not able to implement the code with an inventory, I tried but the logic kept failing
- This problem is to be done by Dynamic programming and unfortunately I am not well versed with that paradigm of programming to be honest
- I have tried to make springBoot app as clean as possible

## Improvements

- Consider inventory of coins
- The inventory can be easily manged through REST API but we need to be assured that we add security there so that only authorized users can manipulate it
- Current implementation ignore inventory and gives minimum coins for the amount
