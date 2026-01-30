GATEKEEPER ADMIN APPLICATION - 

Gatekeeper Admin Application is part of the Gatekeeper Project which is a API Gateway + Monitoring Tool.

The Admin Console is used for the follwing things - 

1. It hosts the Embedded Database which is used to hold the routes for the gateway. 

2. It's UI panel can be used for functions like LISTING / ADDING / DELETEING / MODIFYING the routes.


EMBEDDED DATABASE - 

The DB used here is "SQLite" due to its lightweight nature. It contains all the route info in the follwing manner - 

The Table Structure is as follows - 

| Column Name| Data Type | Constraints            | Description                            |
| -----------| --------- | ---------------------- | -------------------------------------- |
| id         | INTEGER   | **PK**, AUTO-INCREMENT | Unique route identifier                |
| name       | VARCHAR   | NOT NULL               | Human-readable route name              |
| version    | INTEGER   | NOT NULL               | Route version (for evolution/rollouts) |
| endpoint   | VARCHAR   | NOT NULL               | Incoming request path (e.g. /notify) |
| active     | BOOLEAN   | NOT NULL               | Whether the route is enabled           |
| target_url | VARCHAR   | NOT NULL               | Downstream service URL                 |




