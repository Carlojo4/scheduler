import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-task',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.css'
})
export class AddTaskComponent {

  newTask = {
    url: '',
    cron: ''
  };

  constructor(private http: HttpClient) {}

  onSubmit() {
    // Call the REST API using HttpClient
    this.http.post<any>('http://localhost:8080/api/scheduler', this.newTask)  // Replace with your API endpoint
      .subscribe(response => {
        console.log('Task added successfully:', response);
        // Handle successful response (e.g., clear form, notify user)
      }, error => {
        console.error('Error adding task:', error);
        // Handle error (e.g., display error message to user)
      });
  }

}
