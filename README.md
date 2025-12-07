# Simplified Manufacturing Execution System (MES)
Author: Christen Barringer

This project implements a simplified Manufacturing Execution System (MES) using a microservice architecture.

The system manages manufacturing work orders and tracks who completes each one. It simulates a small but realistic factory environment divided into three services: `Product`, `Planning`, and `Execution`.

## System Overview
Each service owns its own data and exposes REST APIs for communication. 
A gateway server on port `8072` acts as a single entry point for the other services, routing requests to the appropriate backend service.


| Service| Description |
| ----- | ----- |
| **Product Service** | Manages part, BOM, and operation reference data. |
| **Planning Service** | Manages the queue of work orders and generates sub-orders for assemblies. |
| **Execution Service** | Allows workers to complete work orders at workstations, creating completion records. |
| **Gateway Server** | Acts as a single entry point for the other services. |
| **Kafka Server** | Used to transport messages between services. | 

### Authentication
Keycloak may be used for authentication on port `9080`. 
This was removed after checkpoint 2, but may be added back and tested using endpoints in `postman/checkpoint-2-postman.json`.
The realm export is located at `platform/realm-export.json`. 

## Prerequisites
- Java 17
- Docker desktop installed 

## Running the System
 
From the `platform` directory: 

```pwsh
docker compose up -d --build
```

Navigate to `localhost:9080` and import the realm export described [above](#authentication).

## Testing
Use the included files in the `postman` folder to test available endpoints. 

### Service URLs:

| Service| URL |
| ----- | ----- |
| Product Service | `http://localhost:8081` |
| Planning Service | `http://localhost:8082` |
| Execution Service | `http://localhost:8083` |
| Gateway Server | `http://localhost:8072` |
| Kafka Server | `http://localhost:9092` | 

A gateway server runs on port `8072`, allowing you to access each service from `http://localhost:8072`. For example, the following
request gets routed by the gateway server to the product service on port `8081`.

```http
GET http://localhost:8072/product-service/parts/11111111-1111-1111-1111-111111111111
```

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
