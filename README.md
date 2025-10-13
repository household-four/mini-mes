# Simplified Manufacturing Execution System (MES)
Author: Christen Barringer

This project implements a simplified Manufacturing Execution System (MES) using a microservice architecture.

The system manages manufacturing work orders and tracks who completes each one. It simulates a small but realistic factory environment divided into three services: `Product`, `Planning`, and `Execution`.

## System Overview
Each service owns its own data and exposes REST APIs for communication.

| Service| Description |
| ----- | ----- |
| **Product Service** | Manages part, BOM, and operation reference data. |
| **Planning Service** | Manages the queue of work orders and generates sub-orders for assemblies. |
| **Execution Service** | Allows workers to complete work orders at workstations, creating completion records. |

## Prerequisites
- Java 17
- Docker desktop installed 

## Running the System
 
From the `platform` directory: 

```sh
docker compose up -d --build
```

Service URLs:

| Service| URL |
| ----- | ----- |
| Product Service | `http://localhost:8081` |
| Planning Service | `http://localhost:8082` |
| Execution Service | `http://localhost:8083` |

## Testing
Use the included `checkpoint-1-postman.json` to test the available endpoints.

## Assumptions 
The following simplifying assumptions were made for this project. These could be expanded in future iterations to make the system more realistic and representative of a real manufacturing environment.

- **Work orders are created manually.**  
There is no upstream system that tracks customer demand or automatically generates work orders based on production planning. Work orders simply exist and can be created directly through the Planning service.
- **Assemblies require one of each component.**  
The BOM does not include quantity information. Each parent part requires exactly one of each of its child parts to be built.
- **Instantaneous work order completion.**  
Work orders are completed immediately when processed. No simulation of elapsed time, workstation occupancy, or partial progress is modeled.
- **Simplified work order lifecycle**
Work orders can only exist in two states: `OPEN` and `CLOSED`. Intermediate statuses like `PLANNED`, `RELEASED`, or `IN_PROGRESS` are not implemented.
- **Infinite purchased inventory.**
Raw materials, fasteners, and purchased parts are assumed to always be on hand and never short. 
