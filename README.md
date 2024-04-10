# Scheduler application

This application is composed from Backend and Frontend components, the complete application is designed to execute a scheduled job based in a cron expression, the job that is executed has two tasks:
- Ping to the url that the user set in the fronend
- Scrape the headers from the same url
The result of the job can be visualized in backend logs

# Installation

## Backend

After clone the repo, you need to open the folder where the Backend is (/scheduler) using eclipse or IntelliJ IDEA. 

Then, run the 'gradle build' task in order to install all the dependencies used.

Then, run the 'gradle bootRun' task to run the application.

Now the application is running and exposed the REST endpoint on this url [http://localhost:8080/api/scheduler](http://localhost:8080/api/scheduler) and is ready to work!

## Frontend

After clone the repo, you need to open the folder where the Frontend is (/scheduler-front/task-scheduler) and then, run the following command to install all the dependencies:

```bash
npm install
```

Then, to run the application, run the following command:

```bash
ng serve
```

Now your Frontend is running in the following url [http://localhost:4200/add-task](http://localhost:4200/add-task)

To add the task, enter the url and the cron expression, then, submit the information.
